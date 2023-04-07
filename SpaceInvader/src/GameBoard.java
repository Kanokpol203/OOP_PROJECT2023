import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class GameBoard extends JPanel implements Runnable {
    final int WIDTH = 500;
    final int HEIGHT = 650;
    final int FPS = 60;
    Thread gameThread;
    List<Mole> moles;
    Player player;

    public GameBoard() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.moles = new ArrayList<Mole>();
        this.player = new Player();
        this.initBoard();
    }

    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
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
        // update the state of each mole
        for (Mole mole : moles) {
            mole.update();
        }
    }

    private void initBoard() {
        // create the moles and add them to the list
        for (int i = 0; i < 6; i++) {
            int x = 100 + i * 75;
            int y = 100;
            Mole mole = new Mole(x, y);
            moles.add(mole);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // draw the moles on the board
        for (Mole mole : moles) {
            int x = mole.getX();
            int y = mole.getY();
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillOval(x, y, 50, 50);
        }

        // draw the player on the board
        int x = player.getX();
        int y = player.getY();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, 50, 50);

        g2d.dispose();
    }
}




