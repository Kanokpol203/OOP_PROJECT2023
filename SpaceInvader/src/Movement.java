
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement implements KeyListener{
    public boolean up, down, left, right, click, fire;
    @Override
    public void keyTyped(KeyEvent e) {
         
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int input = e.getKeyCode();
        if(input == KeyEvent.VK_W){
             up = true;
        }
        if(input == KeyEvent.VK_S){
             down = true;
        }
        if(input == KeyEvent.VK_A){
             left = true;
        }
        if(input == KeyEvent.VK_D){
             right = true;
        }
        if(input == KeyEvent.VK_J){
            fire = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int input = e.getKeyCode();
        if(input == KeyEvent.VK_W){
             up = false;
        }
        if(input == KeyEvent.VK_S){
             down = false;
        }
        if(input == KeyEvent.VK_A){
             left = false;
        }
        if(input == KeyEvent.VK_D){
             right = false;
        }
        if(input == KeyEvent.VK_J){
            fire = false;
        }
    }
}


    

