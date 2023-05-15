import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Mole extends Entity implements Hitable, Runnable {
    private GameBoard game;
    private boolean running = true;
    private Random rand = new Random();
    private Image image;
    
    public Mole(int x, int y,GameBoard game) {
        super(x, y, 100);
        this.game = game;
        this.setVisible(false);
        image = new ImageIcon("src/Asset/Mole1.png").getImage();
        image = image.getScaledInstance(this.game.getScreen().getTilesize(), this.game.getScreen().getTilesize(), Image.SCALE_SMOOTH);
    }
    public boolean isHit(int x, int y) {
        int hitBoxX = this.getX();
        int hitBoxY = this.getY();
        int hitBoxWidth = getWidth();
        int hitBoxHeight = getHeight();
        
        return (x >= hitBoxX && x < hitBoxX + hitBoxWidth &&
            y >= hitBoxY && y < hitBoxY + hitBoxHeight);
    }
    public int getWidth(){
        return game.getScreen().getTilesize();
    }
    public int getHeight(){
        return game.getScreen().getTilesize();
    }
    
    
    @Override
    synchronized public void whack() {
        image = new ImageIcon("src/Asset/Mole2.png").getImage();
        running = false; // stop the thread
        game.removeMole(this);
        game.getAsset().changeScore(this.getScore());
    }
    
    
    @Override
    public void update() {
        this.setVisible(running);
        while (running) {
            try {
                Thread.sleep(1000 + rand.nextInt(4000)); // Change visibility randomly
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setVisible(!this.isVisible());
            game.removeMole(this);
            running = false;
        }
    }
    
    synchronized public void redraw(Graphics2D g2d){
        if(this.isVisible()){
            g2d.drawImage(image, this.getX(), this.getY(), game);
        }
    }
    
    @Override
    public void run() {
        update();
    }
}
