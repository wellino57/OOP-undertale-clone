import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    private static Clip currentMusic;

    public static void playSound(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("src/sfx/" + path);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.start();
    }

    public static void loopSound(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (currentMusic != null && currentMusic.isRunning()) {
            currentMusic.stop();
        }

        File file = new File("src/sfx/" + path);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        currentMusic = AudioSystem.getClip();
        currentMusic.open(audioStream);
        currentMusic.loop(Clip.LOOP_CONTINUOUSLY);

        currentMusic.start();
    }

    public static void stopSound() throws LineUnavailableException {
        if (currentMusic != null && currentMusic.isRunning()) {
            currentMusic.stop();
            currentMusic.close();
            currentMusic = null; // Reset the reference
        }
    }
}
