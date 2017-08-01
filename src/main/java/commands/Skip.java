package commands;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import main.AudioPlayerSendHandler;
import main.Bot;
import main.Constants;
import main.Msg;
import net.dv8tion.jda.core.JDA;
import structure.Command;

public class Skip extends Command {

  public Skip(JDA bot) {
    super(bot);
  }

  public void run(Msg msg) {
    
    if (msg.getInfo() == null || msg.getInfo().getQueue().size() == 0) {
      msg.getTextChannel().sendMessage("There are no songs playing!").queue();
      return;
    }
    
    long skipsRequired = Math.round(msg.getGuild().getAudioManager().getConnectedChannel().getMembers().size() * Constants.SKIPPERCENTAGE);
    msg.getInfo().setSkips(msg.getInfo().getSkips() + 1);
    
    if (skipsRequired > msg.getInfo().getSkips()) {
      msg.getTextChannel().sendMessage("Your skip has been counted! " + String.valueOf(skipsRequired - msg.getInfo().getSkips()) + " more needed!").queue();
      return;
    }
    
    msg.getInfo().setSkips(0);
    
    AudioPlayerSendHandler player = Bot.getPlayers().get(msg.getGuild().getId());
    AudioTrack song = msg.getInfo().getQueue().get(0);
    
    msg.getTextChannel().sendMessage("The song **" + song.getInfo().title + "** was skipped!").queue();
    player.getPlayer().stopTrack();
  }

}
