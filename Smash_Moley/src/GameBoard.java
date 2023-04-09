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
    final int OG_TILE_SIZE = 80;
    final int SCALE = 3;
    final int TILESIZE = OG_TILE_SIZE * SCALE;
    final int SCREEN_COL = 4;
    final int SCREEN_ROW = 3;
    final int WIDTH = TILESIZE * SCREEN_COL;
    final int HEIGHT = TILESIZE * SCREEN_ROW;
    final int FPS = 60;
    Thread gameThread, playerThread;
    List<Mole> moles;
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
        playerThread = new Thread(player);
        gameThread.start();
        playerThread.start();
    }

    @Override
    public void run() {
        double draw = 1000.0 / FPS;
        double drawNext = System.currentTimeMillis() + draw;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainTime = drawNext - System.currentTimeMillis();
                Thread.sleep((long) remainTime);
                drawNext += draw;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (int row = 0; row < SCREEN_ROW; row++) {
            for (int col = 0; col < SCREEN_COL; col++) {
                int x = col * TILESIZE;
                int y = row * TILESIZE;
                // draw a square on top of each block
                g2d.setColor(Color.WHITE);
                g2d.drawRect(x, y, TILESIZE, TILESIZE);
                }
            }
        player.redraw(g2d);
        g2d.dispose();
    }
}




