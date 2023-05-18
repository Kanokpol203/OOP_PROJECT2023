
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    public Sound(){
        soundURL[0] = getClass().getResource("/Sound/Bonk_SFX.wav");
        soundURL[1] = getClass().getResource("/Sound/Sound_Game_02.wav");
        soundURL[2] = getClass().getResource("/Sound/Sound_Menu.wav");
    }
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e){
            System.out.println("NOPE");
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    synchronized public void stop(){
        clip.loop(1);
        clip.stop();
        clip.close();
    }
    public boolean isActive(){
        return clip.isActive();
    }
}
