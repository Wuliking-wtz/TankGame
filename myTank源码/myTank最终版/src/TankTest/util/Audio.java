package TankTest.util;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Audio {
    Clip clip;
    String filename;

    public Clip getClip() {
        return clip;
    }

    public Audio(String filename) {
        this.filename = filename;
    }

    public void play() {
        BufferedInputStream bis = null;
        AudioInputStream audioInput = null;
        try {
            bis = new BufferedInputStream(Audio.class.getClassLoader().getResourceAsStream(filename));
            audioInput = AudioSystem.getAudioInputStream(bis);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (audioInput != null) {
                try {
                    audioInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loopPlay() {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        AudioInputStream audioInput = null;
        try {
            bis = new BufferedInputStream(Audio.class.getClassLoader().getResourceAsStream(filename));
            audioInput = AudioSystem.getAudioInputStream(bis);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            while (true) {
                if (clip.getFramePosition() == 0) {
                    clip.start();
                }
                if (clip.getFramePosition() == clip.getFrameLength()) {
                    clip.setFramePosition(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (audioInput != null) {
                try {
                    audioInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
