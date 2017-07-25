package commands;

import main.Msg;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Message;
import structure.Command;

public class Ping extends Command {

  public Ping(JDA bot) {
    super(bot);
  }

  public void run(Msg msg) {
    
    Message m = msg.getTextChannel().sendMessage("Ping!").complete();
    m.editMessage(m.getContent() + " " + String.valueOf(m.getCreationTime().toInstant().toEpochMilli() - msg.getMessage().getCreationTime().toInstant().toEpochMilli()) + "ms").queue();
    
  }

}
