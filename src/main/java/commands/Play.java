package commands;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import main.AudioPlayerSendHandler;
import main.Bot;
import main.GuildPlayerInfo;
import main.Msg;
import main.TrackScheduler;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.VoiceChannel;
import structure.Command;

public class Play extends Command {

  public Play(JDA bot) {
    super(bot);
  }
  
  public void run(Msg msg) {
    final VoiceChannel memberChannel = msg.getMember().getVoiceState().getChannel();
    if (memberChannel == null) {
      msg.getTextChannel().sendMessage("🎵 You must be in a voice channel to use this command!").queue();
      return;
    }
    
    AudioPlayer player;
    AudioPlayerSendHandler sendHandler;
    TrackScheduler trackScheduler;
    
    if (Bot.getPlayers().containsKey(msg.getGuild().getId())) {
      sendHandler = Bot.getPlayers().get(msg.getGuild().getId());
      player = sendHandler.getPlayer();
      trackScheduler = sendHandler.getTrackScheduler();
    }
    else {
      player = Bot.getPlayerManager().createPlayer();
      trackScheduler = new TrackScheduler(player, new GuildPlayerInfo(msg));
      player.addListener(trackScheduler);
      sendHandler = new AudioPlayerSendHandler(player, trackScheduler);
      Bot.getPlayers().put(msg.getGuild().getId(), sendHandler);
    }
    
    msg.getGuild().getAudioManager().openAudioConnection(memberChannel);
    msg.getGuild().getAudioManager().setSendingHandler(sendHandler);
    Bot.getPlayerManager().loadItem(msg.getParams()[0], new AudioLoadResultHandler() {

      @Override
      public void trackLoaded(AudioTrack track) {
        trackScheduler.play(track);
      }

      @Override
      public void playlistLoaded(AudioPlaylist playlist) {
        msg.getTextChannel().sendMessage("🎵 Playlists are not supported yet.").queue();
        
      }

      @Override
      public void noMatches() {
        msg.getTextChannel().sendMessage("🎵 No matches for the request song were found.").queue();
        
      }

      @Override
      public void loadFailed(FriendlyException exception) {
        System.out.println(exception);
        
      }
      
    });
  }

}
