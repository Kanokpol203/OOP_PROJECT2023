import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Random;
public class Mole extends Entity implements Hitable, Runnable {
    private Thread thread;
    private GameBoard game;
    private boolean running = true;
    private Random rand = new Random();
    private Image image;
    private Thread mole_thread = new Thread();

    public Mole(int x, int y,GameBoard game) {
        super(x, y, 100);
        this.game = game;
        this.setVisible(false);
        image = new ImageIcon("src/Asset/Mole1.png").getImage();
        image = image.getScaledInstance(game.TILESIZE, game.TILESIZE, Image.SCALE_SMOOTH);
        thread = new Thread(this);
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
        return game.TILESIZE;
    }
    public int getHeight(){
        return game.TILESIZE;
    }
    
    
    @Override
    synchronized public void whack() {
        this.setVisible(false);
        running = false; // stop the thread
        game.removeMole(this);
        System.out.println("Mole " + " Whacked");
    }
    
    
    @Override
    public void update() {
        while (running) {
            try {
                Thread.sleep(1000 + rand.nextInt(4000)); // Change visibility randomly
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setVisible(!this.isVisible());
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
