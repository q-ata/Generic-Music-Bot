package commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

import main.Bot;
import main.Msg;
import net.dv8tion.jda.core.JDA;
import structure.Command;

public class Pause extends Command {

  public Pause(JDA bot) {
    super(bot);
  }

  public void run(Msg msg) {
    
    if (msg.getInfo() == null || msg.getInfo().getQueue().size() == 0) {
      msg.getTextChannel().sendMessage("There is no song to pause!").queue();
      return;
    }
    
    AudioPlayer player = Bot.getPlayers().get(msg.getGuild().getId()).getPlayer();
    
    if (player.isPaused()) {
      msg.getTextChannel().sendMessage("The player is already paused!").queue();
      return;
    }
    
    player.setPaused(true);
    msg.getTextChannel().sendMessage("The song **" + msg.getInfo().getQueue().get(0).getInfo().title + "** has been paused.").queue();
    
  }

}
