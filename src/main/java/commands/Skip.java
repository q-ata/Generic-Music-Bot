package commands;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import main.AudioPlayerSendHandler;
import main.Bot;
import main.GuildPlayerInfo;
import main.Msg;
import net.dv8tion.jda.core.JDA;
import structure.Command;

public class Skip extends Command {

  public Skip(JDA bot) {
    super(bot);
  }

  public void run(Msg msg) {
    
    GuildPlayerInfo info;
    
    if (!Bot.getPlayers().containsKey(msg.getGuild().getId()) || (info = Bot.getPlayers().get(msg.getGuild().getId()).getTrackScheduler().getInfo()).getQueue().size() == 0) {
      msg.getTextChannel().sendMessage("There are no songs playing!").queue();
      return;
    }
    
    AudioPlayerSendHandler player = Bot.getPlayers().get(msg.getGuild().getId());
    AudioTrack song = info.getQueue().get(0);
    
    msg.getTextChannel().sendMessage("The song **" + song.getInfo().title + "** was skipped!").queue();
    player.getPlayer().stopTrack();
    // song.stop();
  }

}
