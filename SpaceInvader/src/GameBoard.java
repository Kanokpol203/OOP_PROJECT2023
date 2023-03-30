
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;


public class GameBoard extends JPanel implements Runnable{
    final int WIDHT = 500;
    final int HEIGHT = 650;
    final int SHOT_COOLDOWN = 250;
    final int FPS = 60;
    private long lastShotTime = 0;
    Movement move = new Movement();
    Thread gamethread;
    Player p;
    List<Projectile> projectiles = new ArrayList();
    public GameBoard(){
        this.setPreferredSize(new Dimension(WIDHT, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(move);
        this.setFocusable(true);
        p = new Player(WIDTH/2, HEIGHT-60, 5);
    }
    public void startThread(){
        gamethread = new Thread(this);
        gamethread.start();
    }
    @Override
    public void run() {
        double draw = 1000/FPS;
        double drawnext = System.currentTimeMillis() + draw;
        
        while(gamethread != null){
            update();
            repaint();
            
            try{
                double remaintime = drawnext - System.currentTimeMillis();
                Thread.sleep((long) remaintime);
                drawnext += draw;
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void update(){
        //Gameinput
        if (move.left) {
            p.setX(p.getX() - p.getSpeed());
        }if (move.right) {
            p.setX(p.getX() + p.getSpeed());
        }if (move.up) {
            p.setY(p.getY() - p.getSpeed());
        }if (move.down) {
            p.setY(p.getY() + p.getSpeed());
        }if (move.fire && System.currentTimeMillis()-lastShotTime 
                > SHOT_COOLDOWN) {
            lastShotTime = System.currentTimeMillis();
            projectiles.add(new Projectile(10, p.getX()+20, p.getY(), -8));
        }
        //Projectile
        for(int i = 0; i < projectiles.size(); i++){
            Projectile pew = projectiles.get(i);
            pew.update();
            if(pew.getY()<0 || pew.getY() > HEIGHT){
                projectiles.remove(i);
                i--;
            }
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D Player = (Graphics2D)g;
        Player.setColor(Color.red);
        Player.fillRect(p.getX(), p.getY(), 50, 50);
        for(Projectile bullet : projectiles){
            Player.setColor(Color.white);
            Player.fillOval(bullet.getX(), bullet.getY(), 10, 10);
        }
        Player.dispose();
    }
}
