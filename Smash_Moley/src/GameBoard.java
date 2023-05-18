import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameBoard extends JPanel implements Runnable{
    public final int FPS = 60;
    private Screen_Size screen = new Screen_Size();
    ExecutorService pool = Executors.newFixedThreadPool(5);
    private Image mole_hole, bg;
    private Timers timer;
    private GameAsset asset;
    private String mode;
    Thread gameThread;
    List<Mole> moles;
    List<Bomb> bombs;
    private Player player;
    
    public GameBoard() {
        this.setPreferredSize(new Dimension(screen.getWidth(), screen.getHeight()));
        this.setBackground(Color.CYAN);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        player = new Player(this);
        timer = new Timers(this);
        asset = new GameAsset(this);
        bg = new ImageIcon("src/Asset/bg_tmp.png").getImage();
        bg = bg.getScaledInstance(screen.getWidth(), screen.getGame_height(), Image.SCALE_SMOOTH);
        mole_hole = new ImageIcon("src/Asset/Mole_hole1_test.png").getImage();
        mole_hole = mole_hole.getScaledInstance(screen.getTilesize(), screen.getTilesize(), Image.SCALE_SMOOTH);
        this.addMouseMotionListener(player);
        this.addMouseListener(player);
        moles = new ArrayList<>();
        bombs = new ArrayList<>();
    }
    
    public GameBoard(String mode) {
        if(mode == "ez")
        {
            this.mode = mode;
            System.out.println(this.mode);
            this.setPreferredSize(new Dimension(screen.getWidth(), screen.getHeight()));
            this.setBackground(Color.CYAN);
            this.setDoubleBuffered(true);
            this.setFocusable(true);
            player = new Player(this);
            timer = new Timers(this);
            asset = new GameAsset(this);
            bg = new ImageIcon("src/Asset/bg_tmp.png").getImage();
            bg = bg.getScaledInstance(screen.getWidth(), screen.getGame_height(), Image.SCALE_SMOOTH);
            mole_hole = new ImageIcon("src/Asset/Mole_hole1_test.png").getImage();
            mole_hole = mole_hole.getScaledInstance(screen.getTilesize(), screen.getTilesize(), Image.SCALE_SMOOTH);
            this.addMouseMotionListener(player);
            this.addMouseListener(player);
            moles = new ArrayList<>();
            bombs = new ArrayList<>();
        }
        else if(mode == "nm")
        {
            this.mode = mode;
            System.out.println(this.mode);
            this.setPreferredSize(new Dimension(screen.getWidth(), screen.getHeight()));
            this.setBackground(Color.CYAN);
            this.setDoubleBuffered(true);
            this.setFocusable(true);
            player = new Player(this);
            timer = new Timers(this);
            asset = new GameAsset(this);
            bg = new ImageIcon("src/Asset/bg_tmp.png").getImage();
            bg = bg.getScaledInstance(screen.getWidth(), screen.getGame_height(), Image.SCALE_SMOOTH);
            mole_hole = new ImageIcon("src/Asset/Mole_hole1_test.png").getImage();
            mole_hole = mole_hole.getScaledInstance(screen.getTilesize(), screen.getTilesize(), Image.SCALE_SMOOTH);
            this.addMouseMotionListener(player);
            this.addMouseListener(player);
            moles = new ArrayList<>();
            bombs = new ArrayList<>();
        }
        else if(mode == "hr")
        {
            this.mode = mode;
            System.out.println(this.mode);
            this.setPreferredSize(new Dimension(screen.getWidth(), screen.getHeight()));
            this.setBackground(Color.CYAN);
            this.setDoubleBuffered(true);
            this.setFocusable(true);
            player = new Player(this);
            timer = new Timers(this);
            asset = new GameAsset(this);
            bg = new ImageIcon("src/Asset/bg_tmp.png").getImage();
            bg = bg.getScaledInstance(screen.getWidth(), screen.getGame_height(), Image.SCALE_SMOOTH);
            mole_hole = new ImageIcon("src/Asset/Mole_hole1_test.png").getImage();
            mole_hole = mole_hole.getScaledInstance(screen.getTilesize(), screen.getTilesize(), Image.SCALE_SMOOTH);
            this.addMouseMotionListener(player);
            this.addMouseListener(player);
            moles = new ArrayList<>();
            bombs = new ArrayList<>();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Screen_Size getScreen() {
        return screen;
    }
    
    synchronized public void removeMole(Mole mole) {
        moles.remove(mole);
    }
    synchronized public void removeBomb(Bomb bomb) {
        bombs.remove(bomb);
    }

    public GameAsset getAsset() {
        return asset;
    }
    
    
    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
        System.out.println(moles.size() +" "+ bombs.size());
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
        pool.shutdown();
    }

    public void update() {            
        player.update();
        if(mode == "ez" || mode == "nm")
        {
            if (moles.size() < 3 && bombs.size() < 2) {
                Random random = new Random();
                int x, y;
                boolean overlap;
                do {
                    overlap = false;
                    x = random.nextInt(screen.getScreen_col()) * screen.getTilesize();
                    y = (random.nextInt(screen.getScreen_row()) + 1) * screen.getTilesize();
                    for (Mole mole : moles) {
                        if (mole.getX() == x && mole.getY() == y) {
                            overlap = true;
                            break;
                        }
                    }
                    if(!overlap){
                        for (Bomb bomb : bombs){
                            if (bomb.getX() == x && bomb.getY() == y){
                                overlap = true;
                                break;
                            }  
                        }
                    }
                } while (overlap);
                if (random.nextInt(3) == 0) {
                    if (mode == "nm" || mode == "hr")
                    {
                        System.out.println(this.mode);
                        Bomb bomb = new Bomb(x, y, this);
                        bombs.add(bomb);
                        pool.submit(bomb);
                    }
                } else {
                    Mole mole = new Mole(x, y, this);
                    moles.add(mole);
                    pool.submit(mole);
                }
                System.out.println(moles.size()+ " " + bombs.size());
            }
        }
        else if (mode == "hr")
        {
            if (moles.size() < 2 && bombs.size() < 4) {
                Random random = new Random();
                int x, y;
                boolean overlap;
                do {
                    overlap = false;
                    x = random.nextInt(screen.getScreen_col()) * screen.getTilesize();
                    y = (random.nextInt(screen.getScreen_row()) + 1) * screen.getTilesize();
                    for (Mole mole : moles) {
                        if (mole.getX() == x && mole.getY() == y) {
                            overlap = true;
                            break;
                        }
                    }
                    if(!overlap){
                        for (Bomb bomb : bombs){
                            if (bomb.getX() == x && bomb.getY() == y){
                                overlap = true;
                                break;
                            }  
                        }
                    }
                } while (overlap);
                if (random.nextInt(2) == 0) {
                    Bomb bomb = new Bomb(x, y, this);
                    bombs.add(bomb);
                    pool.submit(bomb);
                } else {
                    Mole mole = new Mole(x, y, this);
                    moles.add(mole);
                    pool.submit(mole);
                }
                System.out.println(moles.size()+ " " + bombs.size());
            }
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(bg, 0, screen.getTilesize(), this);
        g2d.drawRect(0, 0, screen.getWidth(), screen.getGUI_SIZE());
        
        for (int row = 1; row < screen.getScreen_row() + 1; row++) {
            for (int col = 0; col < screen.getScreen_col(); col++) {
                int x = col * screen.getTilesize();
                int y = row * screen.getTilesize();
                // draw a square on top of each block
                g2d.drawImage(mole_hole, x, y, this);
            }
        }
        for(Mole that_moles: moles){
            that_moles.redraw(g2d);
        }
        for(Bomb that_bomb : bombs){
            that_bomb.redraw(g2d);
        }
        asset.redraw(g2d);
        timer.redraw(g2d);
        player.redraw(g2d);
        g.dispose();
    }
}




