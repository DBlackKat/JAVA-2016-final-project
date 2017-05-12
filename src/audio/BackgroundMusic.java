package audio;

import os.CheckOs;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by theblackcat on 27/4/17.
 */
public class BackgroundMusic {

    public static String windowMusicPath = "";
    AudioStream BGM;
    ContinuousAudioDataStream loop = null;
    public BackgroundMusic(String soundName){
        try {
            BGM = new AudioStream(new FileInputStream(CheckOs.getMusicPath()+soundName));
        }
        catch (IOException e) {
            System.out.print("file not found " + e);
        }
    }

    public void start(){
        AudioPlayer.player.start(BGM);
    }

    public void stop(){
        AudioPlayer.player.stop(BGM);
    }
}
