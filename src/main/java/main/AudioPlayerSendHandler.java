package main;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.core.audio.AudioSendHandler;

public class AudioPlayerSendHandler implements AudioSendHandler {
  private final AudioPlayer audioPlayer;
  private final TrackScheduler trackScheduler;
  private AudioFrame lastFrame;
  private int skips;
  private double reqSkips;
  private int searchType;

  public AudioPlayerSendHandler(AudioPlayer audioPlayer, TrackScheduler trackScheduler) {
    this.audioPlayer = audioPlayer;
    this.trackScheduler = trackScheduler;
    this.setSkips(0);
    this.setReqSkips(0.5);
    this.setSearchType(0);
  }

  @Override
  public boolean canProvide() {
    if (lastFrame == null) {
      lastFrame = audioPlayer.provide();
    }

    return lastFrame != null;
  }

  @Override
  public byte[] provide20MsAudio() {
    if (lastFrame == null) {
      lastFrame = audioPlayer.provide();
    }

    byte[] data = lastFrame != null ? lastFrame.data : null;
    lastFrame = null;

    return data;
  }

  @Override
  public boolean isOpus() {
    return true;
  }
  
  public AudioPlayer getPlayer() {
    return audioPlayer;
    
  }

  public TrackScheduler getTrackScheduler() {
    return trackScheduler;
  }

  public int getSkips() {
    return skips;
  }

  public void setSkips(int skips) {
    this.skips = skips;
  }

  public double getReqSkips() {
    return reqSkips;
  }

  public void setReqSkips(double reqSkips) {
    this.reqSkips = reqSkips;
  }

  public int getSearchType() {
    return searchType;
  }

  public void setSearchType(int searchType) {
    this.searchType = searchType;
  }
}