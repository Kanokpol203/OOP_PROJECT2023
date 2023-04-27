import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameBoard extends JPanel implements Runnable{
    final int OG_TILE_SIZE = 30;
    final int SCALE = 5;
    final int TILESIZE = OG_TILE_SIZE * SCALE;
    final int GUI_SIZE = OG_TILE_SIZE * SCALE;
    final int SCREEN_COL = 5;
    final int SCREEN_ROW = 3;
    final int WIDTH = TILESIZE * SCREEN_COL;
    final int GAME_HEIGHT = TILESIZE * SCREEN_ROW;
    final int HEIGHT = TILESIZE * SCREEN_ROW + GUI_SIZE;
    final int FPS = 60;
    
    private Image mole_hole;
    private Timers timer;
    private GameAsset asset;
    Thread gameThread;
    List<Mole> moles;
    List<Thread> threads;
    private Player player;
    
    public GameBoard() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.GREEN);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        player = new Player(this);
        timer = new Timers(this);
        mole_hole = new ImageIcon("src/Asset/Mole_hole2_test.png").getImage();
        mole_hole = mole_hole.getScaledInstance(TILESIZE, TILESIZE, Image.SCALE_SMOOTH);
        this.addMouseMotionListener(player);
        this.addMouseListener(player);
        moles = new ArrayList<>();
        threads = new ArrayList<>();
    } 
    
    synchronized public void removeMole(Mole mole) {
        int index = moles.indexOf(mole);
        if (index >= 0) {
            moles.remove(index);
            System.out.println("Mole "+ index + " Removed");
//            threads.remove(index);
        }
    }

    public GameAsset getAsset() {
        return asset;
    }
    
    
    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
        System.out.println(moles.size() +" "+ threads.size());
    }

    @Override
    public void run() {
        long lastFrameTime = System.currentTimeMillis();
    
        while (gameThread != null) {

            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;
            
            update();
            repaint();
    
            long remainingTime = Math.max(0, (long) (1000.0 / FPS - elapsedTime));
            try {
                Thread.sleep(remainingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized public void update() {
        if (moles.size() < 3) {
            Random random = new Random();
            int x, y;
            boolean overlap;
            do {
                overlap = false;
                x = random.nextInt(SCREEN_COL) * TILESIZE;
                y = (random.nextInt(SCREEN_ROW) + 1) * TILESIZE;
                for (Mole mole : moles) {
                    if (mole.getX() == x && mole.getY() == y) {
                        overlap = true;
                        break;
                    }
                }
            } while (overlap);
            Mole mole = new Mole(x, y, this);
            Thread thread = new Thread(mole);
            moles.add(mole);
            thread.start();
//            threads.add(thread);
            System.out.println(moles.size() + " " + threads.size());
        }
    }


    @Override
    synchronized public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (int row = 1; row < SCREEN_ROW+1; row++) {
            for (int col = 0; col < SCREEN_COL; col++) {
                int x = col * TILESIZE;
                int y = row * TILESIZE;
                // draw a square on top of each block
                g2d.drawImage(mole_hole, x, y, this);
            }
        }
        
        for(Mole that_moles: moles){
            that_moles.redraw(g2d);
        }
        timer.redraw(g2d);
        player.redraw(g2d);
        g2d.dispose();
    }
}




