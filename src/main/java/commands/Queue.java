package commands;

import java.util.concurrent.TimeUnit;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import main.Bot;
import main.GuildPlayerInfo;
import main.Msg;
import net.dv8tion.jda.core.JDA;
import structure.Command;

public class Queue extends Command {

  public Queue(JDA bot) {
    super(bot);
  }

  public void run(Msg msg) {
    
    GuildPlayerInfo info = Bot.getPlayers().get(msg.getGuild().getId()).getTrackScheduler().getInfo();
    AudioTrack currentSong = info.getQueue().get(0);
    String progress = convertMilliseconds(currentSong.getPosition()) + "/" + convertMilliseconds(currentSong.getDuration());
    String[] songs = new String[info.getQueue().size()];
    songs[0] = "🎵 Now playing: **" + currentSong.getInfo().title + "** (" + progress + ")\n";
    
    for (int i = 1; i < info.getQueue().size(); i++) {
      AudioTrack song = info.getQueue().get(i);
      songs[i] = String.valueOf(i) + ". **" + song.getInfo().title + "** (" + convertMilliseconds(song.getDuration()) + ")";
    }
    
    msg.getTextChannel().sendMessage(String.join("\n", songs)).queue();
  }
  
  private static String convertMilliseconds(Long millis) {
    return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
        TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));   
  }

}
