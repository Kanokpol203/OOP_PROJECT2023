
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Bomb extends Entity implements Screen_Size, Hitable, Runnable{
    private int life_time = 3000;
    private Thread thread;
    private boolean alive = true;
    private long spawntime;
    private GameBoard game;
    private Image image;
    
    public Bomb(int x, int y, GameBoard game){
        super(x, y, -100);
        this.game = game;
        image = new ImageIcon("src/Asset/bomb_tmp.png").getImage();
        image = image.getScaledInstance(Screen_Size.TILESIZE, Screen_Size.TILESIZE, Image.SCALE_SMOOTH);
        thread = new Thread(this);
    }
    public boolean isHit(int x, int y) {
        int hitBoxX = this.getX();
        int hitBoxY = this.getY();
        int hitBoxWidth = GameBoard.TILESIZE;
        int hitBoxHeight = GameBoard.TILESIZE;
        
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
        while(alive) {
            this.setVisible(alive);
            long elapsedTime = System.currentTimeMillis() - spawntime;
            if(elapsedTime >= life_time) {
                alive = false;
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
        if(isVisible()){
            g2d.drawImage(image, this.getX(), this.getY(), game);
        }
    }
}
