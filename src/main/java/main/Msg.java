package main;
import java.util.Arrays;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Msg extends MessageReceivedEvent {

  private String[] params;
  private String command;

  public Msg(JDA bot, MessageReceivedEvent msg) {
    
    super(bot, msg.getResponseNumber(), msg.getMessage());
    
    String content = msg.getMessage().getRawContent();
    this.setCommand(content.split(" ")[0].substring(1));
    final String[] WORDS = content.split(" ");
    this.setParams(Arrays.copyOfRange(WORDS, 1, WORDS.length));
  }

  public String[] getParams() {
    return params;
  }

  public void setParams(String[] params) {
    this.params = params;
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

}
