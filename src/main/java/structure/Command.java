package structure;
import main.Msg;
import net.dv8tion.jda.core.JDA;

public abstract class Command {

  private JDA bot;

  public Command(JDA bot) {
    this.setBot(bot);
  }

  public JDA getBot() {
    return bot;
  }

  public void setBot(JDA bot) {
    this.bot = bot;
  }
  
  public abstract void run(Msg msg);
  
}
