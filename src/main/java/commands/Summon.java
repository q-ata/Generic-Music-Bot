package commands;

import main.Msg;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.VoiceChannel;
import structure.Command;

public class Summon extends Command {

  public Summon(JDA bot) {
    super(bot);
  }

  public void run(Msg msg) {
    
    VoiceChannel channel;
    
    if ((channel = msg.getMember().getVoiceState().getChannel()) == null) {
      msg.getTextChannel().sendMessage("You are not in a voice channel!").queue();
    }
    
    msg.getGuild().getAudioManager().openAudioConnection(channel);
    msg.getTextChannel().sendMessage("Joined your voice channel!").queue();
    
  }

}
