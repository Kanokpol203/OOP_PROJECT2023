
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Bomb extends Entity implements Hitable, Runnable{
    private int life_time = 1000;
    private boolean alive = true;
    private long spawntime;
    private GameBoard game;
    private Image image;
    
    public Bomb(int x, int y, GameBoard game){
        super(x, y, -100);
        this.game = game;
        spawntime = System.currentTimeMillis();
        image = new ImageIcon("src/Asset/bomb_tmp.png").getImage();
        image = image.getScaledInstance(game.getScreen().getTilesize(), game.getScreen().getTilesize(), Image.SCALE_SMOOTH);
    }
    public boolean isHit(int x, int y) {
        int hitBoxX = this.getX();
        int hitBoxY = this.getY();
        int hitBoxWidth = game.getScreen().getTilesize();
        int hitBoxHeight = game.getScreen().getTilesize();
        
        return (x >= hitBoxX && x < hitBoxX + hitBoxWidth &&
            y >= hitBoxY && y < hitBoxY + hitBoxHeight);
    }
    
    
    
    @Override
    public void update() {
        
    }

    @Override
    public void whack() {
        this.setVisible(false);
        alive = false; // stop the thread
        game.removeBomb(this);
        stunPlayer();
    }

    @Override
    public void run() {
        this.setVisible(true);
        while(alive) {
            long currenttime = System.currentTimeMillis();
            long elapsedTime = currenttime - spawntime;
            if(elapsedTime >= life_time) {
                this.setVisible(false);
                game.removeBomb(this);
                alive = false;
                System.out.println("I am dead");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void stunPlayer() {
        game.getPlayer().stun();
    }
    
    public void redraw(Graphics2D g2d) {
        if(this.isVisible()){
            g2d.drawImage(image, this.getX(), this.getY(), game);
        }
    }
}
