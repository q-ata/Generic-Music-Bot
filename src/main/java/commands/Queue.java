package commands;

import java.util.concurrent.TimeUnit;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import main.Msg;
import net.dv8tion.jda.core.JDA;
import structure.Command;

public class Queue extends Command {

  public Queue(JDA bot) {
    super(bot);
  }

  public void run(Msg msg) {
    
    if (msg.getInfo() == null || msg.getInfo().getQueue().size() == 0) {
      msg.getTextChannel().sendMessage("There are currently no songs queued.").queue();
      return;
    }
    
    AudioTrack currentSong = msg.getInfo().getQueue().get(0);
    String progress = convertMilliseconds(currentSong.getPosition()) + "/" + convertMilliseconds(currentSong.getDuration());
    String[] songs = new String[msg.getInfo().getQueue().size()];
    songs[0] = "Now playing: **" + currentSong.getInfo().title + "** (" + progress + ")\n";
    
    for (int i = 1; i < msg.getInfo().getQueue().size(); i++) {
      AudioTrack song = msg.getInfo().getQueue().get(i);
      songs[i] = String.valueOf(i) + ". **" + song.getInfo().title + "** (" + convertMilliseconds(song.getDuration()) + ")";
    }
    
    msg.getTextChannel().sendMessage(String.join("\n", songs)).queue();
  }
  
  private static String convertMilliseconds(Long millis) {
    return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
        TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));   
  }

}
