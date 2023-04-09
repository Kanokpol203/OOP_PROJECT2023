import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class GameBoard extends JPanel implements Runnable{
    final int OG_TILE_SIZE = 40;
    final int SCALE = 3;
    final int OG_GUI_SIZE = 40;
    final int TILESIZE = OG_TILE_SIZE * SCALE;
    final int GUI_SIZE = 30 * SCALE;
    final int SCREEN_COL = 4;
    final int SCREEN_ROW = 3;
    final int WIDTH = TILESIZE * SCREEN_COL;
    final int HEIGHT = TILESIZE * SCREEN_ROW + OG_GUI_SIZE * SCREEN_ROW;
    final int FPS = 60;
    Thread gameThread, playerThread;
    List<Mole> moles;
    private long time = System.currentTimeMillis();
    private Player player = new Player(this);
    
    public GameBoard() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.GREEN);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addMouseMotionListener(player);
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null));
    }
    
    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long lastFrameTime = System.currentTimeMillis();
    
        while (gameThread != null) {

            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;
    
            update(elapsedTime);
            repaint();
    
            long remainingTime = Math.max(0, (long) (1000.0 / FPS - elapsedTime));
            try {
                Thread.sleep(remainingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(long time) {
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (int row = 1; row < SCREEN_ROW+1; row++) {
            for (int col = 0; col < SCREEN_COL; col++) {
                int x = col * TILESIZE;
                int y = row * TILESIZE;
                // draw a square on top of each block
                g2d.setColor(Color.BLACK);
                g2d.drawOval(x, y, TILESIZE, TILESIZE);
            }
        }
        for (int row = 0; row < 1; row++) {
            for (int col = 0; col < SCREEN_COL; col++) {
                int x = col * TILESIZE;
                int y = row * TILESIZE;
                // draw a square on top of each block
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y, TILESIZE, TILESIZE-10);
            }
        }
        player.redraw(g2d);
        g2d.dispose();
    }
}




