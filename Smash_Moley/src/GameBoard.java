import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameBoard extends JPanel implements Runnable{
    public final int FPS = 60;
    private Screen_Size screen = new Screen_Size();
    ExecutorService pool = Executors.newFixedThreadPool(15);
    private Image mole_hole, bg, gui;
    private Timers timer;
    private GameAsset asset;
    boolean gamestart;
    private long lastspawntime;
    private long spawndelay = 250;
    
    Spawner spawner;
    private String mode;
    Thread gameThread;
    List<Mole> moles;
    List<Bomb> bombs;
    List<Nuke> nukes;
    private Player player;
    Sound theme;
    SoundFX sfx;
    Starter frame;
    
    public GameBoard(Starter start) {
        this.setPreferredSize(new Dimension(screen.getWidth(), screen.getHeight()));
        this.setBackground(Color.CYAN);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        player = new Player(this);
        timer = new Timers(this);
        asset = new GameAsset(this);
        theme = new Sound();
        frame = start;
        lastspawntime = System.currentTimeMillis();
        sfx = new SoundFX();
        gamestart = true;
        bg = new ImageIcon("src/Asset/bg_tmp.png").getImage();
        bg = bg.getScaledInstance(screen.getWidth(), screen.getGame_height(), Image.SCALE_SMOOTH);
        mole_hole = new ImageIcon("src/Asset/Mole_hole1_test.png").getImage();
        mole_hole = mole_hole.getScaledInstance(screen.getTilesize(), screen.getTilesize(), Image.SCALE_SMOOTH);
        gui = new ImageIcon("src/Asset/Wood_Board1.png").getImage();
        gui = gui.getScaledInstance(screen.getWidth(), screen.getGUI_SIZE(), Image.SCALE_SMOOTH);
        this.addMouseMotionListener(player);
        this.addMouseListener(player);
        moles = new ArrayList<>();
        bombs = new ArrayList<>();
        nukes = new ArrayList<>();
    }
    
    public GameBoard(String mode, Starter frame) {
        this(frame);
        if(mode == "ez")
        {
            this.mode = mode;
            System.out.println(this.mode);
        }
        else if(mode == "nm")
        {
            this.mode = mode;
            System.out.println(this.mode);
        }
        else if(mode == "nr")
        {
            this.mode = mode;
            System.out.println(this.mode);
        }
        spawner = new Spawner(this);
        spawner.execute();
    }
    public void whackAll() {
        List<Bomb> bombsToWhack = new ArrayList<>();
        List<Mole> molesToWhack = new ArrayList<>();

        for (Bomb bomb : bombs) {
            bombsToWhack.add(bomb);
        }
        for (Mole mole : moles) {
            molesToWhack.add(mole);
        }

        for (Bomb bomb : bombsToWhack) {
            bomb.whack();
        }


        for (Mole mole : molesToWhack) {
            mole.whack();
        }

        repaint();
    }


    public Player getPlayer() {
        return player;
    }

    public Screen_Size getScreen() {
        return screen;
    }
    
    public void removeMole(Mole mole) {
        Iterator<Mole> iterator = moles.iterator();
        while (iterator.hasNext()) {
            Mole currentMole = iterator.next();
            if (currentMole == mole) {
                iterator.remove();
                break;
            }
        }
    }
    public void removeBomb(Bomb bomb) {
        Iterator<Bomb> iterator = bombs.iterator();
        while (iterator.hasNext()) {
            Bomb currentbomb = iterator.next();
            if (currentbomb == bomb) {
                iterator.remove();
                break;
            }
        }
    }
    public void removeNuke(Nuke nuke){
        Iterator<Nuke> iterator = nukes.iterator();
        while (iterator.hasNext()) {
            Nuke currentnuke = iterator.next();
            if (currentnuke == nuke) {
                iterator.remove();
                break;
            }
        }
    }
    public String getMode(){
        return mode;
    }
    public ExecutorService getPool(){
        return pool;
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
        this.playTheme(1);
        while (gamestart) {

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
        spawner.stopSpawner();
        repaint();
        
        theme.stop();

        pool.shutdown();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        theme = null;
        frame.close();
    }

    public void update() {
        player.update();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(bg, 0, screen.getTilesize(), this);
        g2d.drawImage(gui, 0, 0, this);
        
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
        for(Nuke that_nuke : nukes){
            that_nuke.redraw(g2d);
        }
        asset.redraw(g2d);
        timer.redraw(g2d);
        player.redraw(g2d);
        g.dispose();
    }
    
    public void playTheme(int i){
        theme.setFile(i);
        theme.play();
        theme.loop();
    }
    
    public void playSE(int i){
        sfx.setFile(i);
        sfx.play();
    }
}




