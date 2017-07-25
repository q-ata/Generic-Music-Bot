package main;
import net.dv8tion.jda.core.JDA;

import java.util.HashMap;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;

import commands.Pause;
import commands.Play;
import commands.Queue;
import commands.Resume;
import commands.Skip;
import commands.Summon;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import structure.Command;

public class Bot {
 

  private static HashMap<String, Command> commands = new HashMap<String, Command>();
  private static HashMap<String, AudioPlayerSendHandler> players = new HashMap<String, AudioPlayerSendHandler>();
  private static final AudioPlayerManager playerManager = new DefaultAudioPlayerManager();

	public static void main(String[] arguments) throws Exception {
	  
	  JDA bot = new JDABuilder(AccountType.BOT).setToken(Constants.TOKEN).buildBlocking();
	  
	  commands.put("play", new Play(bot));
	  commands.put("queue", new Queue(bot));
	  commands.put("skip", new Skip(bot));
	  commands.put("pause", new Pause(bot));
	  commands.put("resume", new Resume(bot));
	  commands.put("summon", new Summon(bot));
	  
	  bot.addEventListener(new MessageListener(bot));
	    
	  AudioSourceManagers.registerRemoteSources(playerManager);
	}
	
  public static HashMap<String, Command> getCommands() {
    return commands;
  }
	
	public static final HashMap<String, AudioPlayerSendHandler> getPlayers() {
		return players;
	}
	
	public static final AudioPlayerManager getPlayerManager() {
		return playerManager;
	}

}
