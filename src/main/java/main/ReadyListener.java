package main;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter {
  
  public void onReady (ReadyEvent e) {
    
    System.out.println("Generic Music Bot is ready.");
    
  }
}
