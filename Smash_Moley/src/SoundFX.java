
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundFX {
    private Clip clip;
    private URL soundURL[] = new URL[30];
    public SoundFX(){
        soundURL[0] = getClass().getResource("/Sound/Bonk_SFX.wav");
        soundURL[2] = getClass().getResource("/Sound/Sound_Menu.wav");
        soundURL[3] = getClass().getResource("/Sound/04.wav");
    }
    public void setFile(int i){
        try(AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);){
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
       
        int i = 0;
        while(i < 20 && clip.isRunning() && clip.isActive()){
            clip.flush();
            clip.close();
            clip.stop();
            i++;
        } 
        clip.flush();
        clip.close();
    }

    public Clip getClip() {
        return clip;
    }

}
