package main;
import java.util.HashMap;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import structure.Command;

public class MessageListener extends ListenerAdapter {
  
  private JDA bot;
  
  public MessageListener(JDA bot) {
    this.bot = bot;
  }
	
	public void onMessageReceived (MessageReceivedEvent msg) {
	  
	  Msg message = new Msg(bot, msg);
	  if (!message.getMessage().getRawContent().startsWith(Constants.PREFIX) || !Bot.getCommands().containsKey(message.getCommand())) return;
	  
	  HashMap<String, Command> commands = Bot.getCommands();
	  
	  commands.get(message.getCommand()).run(message);
	  
	}

  public JDA getBot() {
    return bot;
  }

  public void setBot(JDA bot) {
    this.bot = bot;
  }
}
