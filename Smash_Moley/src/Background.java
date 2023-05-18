
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Background extends JPanel{
    Image bg;
    public Background(Screen_Size screen){
        bg = new ImageIcon("src/Asset/bg_tmp.png").getImage();
        bg = bg.getScaledInstance(screen.getWidth(), screen.getGame_height(), Image.SCALE_SMOOTH);
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(screen.getWidth(), screen.getHeight()));
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, this);
    }
    
}
