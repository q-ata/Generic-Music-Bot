package main;
import java.util.ArrayList;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;

public class GuildPlayerInfo {

  private TextChannel musicTextChannel;
  private VoiceChannel musicVoiceChannel;
  private ArrayList<AudioTrack> queue = new ArrayList<AudioTrack>();
  
  public GuildPlayerInfo(Msg msg) {
    this.musicTextChannel = msg.getMessage().getTextChannel();
    this.musicVoiceChannel = msg.getMessage().getMember().getVoiceState().getChannel();
  }

  public TextChannel getMusicTextChannel() {
    return musicTextChannel;
  }

  public void setMusicChannel(TextChannel musicTextChannel) {
    this.musicTextChannel = musicTextChannel;
  }

  public VoiceChannel getMusicVoiceChannel() {
    return musicVoiceChannel;
  }

  public void setMusicVoiceChannel(VoiceChannel musicVoiceChannel) {
    this.musicVoiceChannel = musicVoiceChannel;
  }

  public ArrayList<AudioTrack> getQueue() {
    return queue;
  }

  public void setQueue(ArrayList<AudioTrack> queue) {
    this.queue = queue;
  }
  
}
