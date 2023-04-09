
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Player extends Entity implements Runnable, MouseMotionListener{
    Thread player_thread = new Thread();
    final int FPS = 10;
    Image image;
    public Player(GameBoard game){
        super(game.WIDTH/2, game.HEIGHT/2);
        image = Toolkit.getDefaultToolkit().getImage("Smash_Moley/src/Asset/Cursor.png");
        image = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    }

    public void redraw(Graphics2D g2d){
        g2d.drawImage(image, this.getX(), this.getY(), null);
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.setX(e.getX());
        this.setY(e.getY());
    }
    
}
