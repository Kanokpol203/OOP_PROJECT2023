
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Player extends Entity implements MouseMotionListener, MouseListener, Screen_Size{
    
    private final long TIME_STUNNED = 2000;
    
    private boolean stunned = false;
    private long stunnedstart;
    private int cursor_size;
    Thread player_thread = new Thread();
    final int FPS = 60;
    Image image;
    GameBoard game;
    
    public Player(GameBoard game){
        super(Screen_Size.WIDTH/2, Screen_Size.HEIGHT/2);
        image = new ImageIcon("src/Asset/Cursor.png").getImage();
        this.game = game;
        cursor_size = Screen_Size.TILESIZE/2;
        image = image.getScaledInstance(cursor_size, cursor_size, Image.SCALE_SMOOTH);
        this.game.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null));
    }

    public boolean isStunned() {
        return stunned;
    }
    
    public void stun(){
        stunned = true;
        stunnedstart = System.currentTimeMillis();
    }

    public void redraw(Graphics2D g2d){
        g2d.drawImage(image, this.getX(), this.getY(), null);
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.setX(e.getX()-25);
        this.setY(e.getY()-25);
    }

    @Override
    synchronized public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked at (" + e.getX() + ", " + e.getY() + ")");

        for(Mole mole : game.moles) {
            if(mole.isVisible() && mole.isHit(e.getX(), e.getY()) && !isStunned()) {
                mole.whack();
                break;
            }
        }
        for(Bomb bomb : game.bombs) {
            if(bomb.isVisible() && bomb.isHit(e.getX(), e.getY()) && !isStunned()) {
                bomb.whack();
                break;
            }
        }
    }
    
    @Override
    public void update() {
        if(stunned && System.currentTimeMillis() - stunnedstart >= TIME_STUNNED){
            stunned = false;
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


    
}
