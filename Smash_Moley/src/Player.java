
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class Player extends Entity implements MouseMotionListener, MouseListener{
    private int cursor_size;
    Thread player_thread = new Thread();
    final int FPS = 60;
    Image image;
    GameBoard game;
    public Player(GameBoard game){
        super(game.WIDTH/2, game.HEIGHT/2);
        //This is the part where it's bug alot
        image = Toolkit.getDefaultToolkit().getImage("src/Asset/Cursor.png");
        this.game = game;
        cursor_size = game.TILESIZE/2;
        image = image.getScaledInstance(cursor_size, cursor_size, Image.SCALE_SMOOTH);
        this.game.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null));
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
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked at (" + e.getX() + ", " + e.getY() + ")");

        for(Mole mole : game.moles) {
            if(mole.isVisible() && mole.isHit(e.getX(), e.getY())) {
                mole.whack();
                break;
            }
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

    @Override
    public void update() {
    }
    
}
