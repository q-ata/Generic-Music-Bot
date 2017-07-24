package main;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.core.audio.AudioSendHandler;

public class AudioPlayerSendHandler implements AudioSendHandler {
  private final AudioPlayer audioPlayer;
  private final TrackScheduler trackScheduler;
  private AudioFrame lastFrame;

  public AudioPlayerSendHandler(AudioPlayer audioPlayer, TrackScheduler trackScheduler) {
    this.audioPlayer = audioPlayer;
    this.trackScheduler = trackScheduler;
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
}