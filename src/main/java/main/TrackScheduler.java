package main;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

public class TrackScheduler extends AudioEventAdapter {
	private final AudioPlayer player;
  private GuildPlayerInfo info;

	public TrackScheduler(AudioPlayer player, GuildPlayerInfo info) {
		this.player = player;
		this.info = info;
	}
	
	public void play(AudioTrack track) {
	  if (info.getQueue().size() == 0) {
	    this.player.playTrack(track);
	  }
	  else {
	    info.getMusicTextChannel().sendMessage("Added **" + track.getInfo().title + "** to the queue! Position: " + (info.getQueue().size() == 1 ? "Up next!" : String.valueOf(info.getQueue().size()))).queue();
	  }
	  info.getQueue().add(track);
  }
	
  @Override
  public void onPlayerPause(AudioPlayer player) {
    // Player was paused
  }

  @Override
  public void onPlayerResume(AudioPlayer player) {
    // Player was resumed
  }

  @Override
  public void onTrackStart(AudioPlayer player, AudioTrack track) {
    info.getMusicTextChannel().sendMessage("Now playing **" + track.getInfo().title + "** in **" + info.getMusicVoiceChannel().getName() + "**").queue();
  }

  @Override
  public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
    if (endReason.mayStartNext) {
      info.getQueue().remove(0);
      if (info.getQueue().size() > 0) {
        this.player.playTrack(info.getQueue().get(0));
      }
      else {
        info.getMusicVoiceChannel().getGuild().getAudioManager().closeAudioConnection();
      }
    }

    // endReason == FINISHED: A track finished or died by an exception (mayStartNext = true).
    // endReason == LOAD_FAILED: Loading of a track failed (mayStartNext = true).
    // endReason == STOPPED: The player was stopped.
    // endReason == REPLACED: Another track started playing while this had not finished
    // endReason == CLEANUP: Player hasn't been queried for a while, if you want you can put a
    //                       clone of this back to your queue
  }

  @Override
  public void onTrackException(AudioPlayer player, AudioTrack track, FriendlyException exception) {
    info.getMusicTextChannel().sendMessage("Something went wrong with the current song, so it was skipped.").queue();
  }

  @Override
  public void onTrackStuck(AudioPlayer player, AudioTrack track, long thresholdMs) {
    info.getQueue().remove(0);
    this.player.playTrack(info.getQueue().get(0));
  }
  
  public GuildPlayerInfo getInfo() {
    return this.info;
  }
}
