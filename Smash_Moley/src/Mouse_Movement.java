
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Mouse_Movement implements MouseMotionListener{
    private Player player;
    public Mouse_Movement(Player player){
        this.player = player;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        player.setX(e.getX());
        player.setY(e.getY());
    }
    
}
