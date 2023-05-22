
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Nuke extends Entity implements Hitable, Runnable{
    private int life_time = 3000;
    private boolean alive = true;
    private long spawntime;
    private GameBoard game;
    private Image image;
    
    public Nuke(int x, int y, GameBoard game){
        super(x, y, -100);
        this.game = game;
        spawntime = System.currentTimeMillis();
        image = new ImageIcon("src/Asset/Nuke_S4.png").getImage();
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
    synchronized public void whack() {
        game.playSE(3);
        this.setVisible(false);
        alive = false; // stop the thread
        game.removeNuke(this);
        game.whackAll();
        game.getAsset().changeScore(this.getScore());
    }

    @Override
    public void run() {
        this.setVisible(true);
        while(alive) {
            long currenttime = System.currentTimeMillis();
            long elapsedTime = currenttime - spawntime;
            if(elapsedTime >= life_time) {
                this.setVisible(false);
                game.removeNuke(this);
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
        if(this.isVisible()){
            g2d.drawImage(image, this.getX(), this.getY(), game);
        }
    }
    
    public long getSpawnTime()
    {
        return 0;
    }
    
    public void setSpawnTime(long SpawnTime)
    {
        this.spawntime = SpawnTime;
    }
}
