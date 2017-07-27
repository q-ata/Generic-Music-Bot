package commands;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import main.AudioPlayerSendHandler;
import main.Bot;
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
    
    int skips = Bot.getPlayers().get(msg.getGuild().getId()).getSkips();
    skips++;
    AudioPlayerSendHandler player = Bot.getPlayers().get(msg.getGuild().getId());
    
    if (msg.getGuild().getAudioManager().getConnectedChannel().getMembers().size() * player.getReqSkips() > skips) {
      msg.getTextChannel().sendMessage("Your vote to skip has been counted.");
      return;
    }
    
    AudioTrack song = msg.getInfo().getQueue().get(0);
    
    msg.getTextChannel().sendMessage("The song **" + song.getInfo().title + "** was skipped!").queue();
    player.getPlayer().stopTrack();
  }

}
