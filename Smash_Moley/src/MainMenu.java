import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame implements MouseListener{
    private Screen_Size screen = new Screen_Size();
    private JFrame frame;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;
    private JPanel p5;
    private JPanel p6;
    private JButton start_bt;
    private JButton exit_bt;
    private JLabel txt;
    public static Sound sound;
    private Background bg;
    
    public MainMenu(){
       frame = new JFrame("MainMenu");
       sound = new Sound();
        bg = new Background(screen);
       this.playTheme(2);
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        p6 = new JPanel();
        p6.setOpaque(false);
        start_bt = new JButton("Start");
        start_bt.setFont(new Font("arial", Font.BOLD, 18));
        start_bt.setBackground(Color.white);
        start_bt.setPreferredSize(new Dimension(100, 50));
        start_bt.addMouseListener(this);
        exit_bt = new JButton("Exit");
        exit_bt.setFont(new Font("arial", Font.BOLD, 18));
        exit_bt.setBackground(Color.white);
        exit_bt.setPreferredSize(new Dimension(100, 50));
        exit_bt.addMouseListener(this);
        txt = new JLabel("", JLabel.CENTER);
        txt.setForeground(Color.white);
        txt.setFont(new Font("arial", Font.BOLD, 64));

        p1.setLayout(new GridLayout(2, 1));
        p2.setLayout(new BorderLayout());
        p3.setLayout(new GridLayout(4, 1));
        p4.setLayout(new FlowLayout());
        p5.setLayout(new FlowLayout());

        p4.add(start_bt); p5.add(exit_bt); p3.add(p6); p3.add(p4); p3.add(p5);
        p2.add(txt); p1.add(p2); p1.add(p3);
        p1.setOpaque(false); p2.setOpaque(false);
        p3.setOpaque(false); p4.setOpaque(false);
        p5.setOpaque(false);
        bg.add(p1);
        this.add(bg);
        bg.setOpaque(false);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screen.getWidth(), screen.getHeight());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(start_bt)){
            new Difficulty_Menu();
            this.dispose();
        }
        else if(e.getSource().equals(exit_bt)){
            System.exit(0);
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
        if(e.getSource().equals(start_bt)){
            start_bt.setBackground(Color.gray);
        }
        else if(e.getSource().equals(exit_bt)){
            exit_bt.setBackground(Color.gray);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        start_bt.setBackground(Color.white);
        exit_bt.setBackground(Color.white);
    }
    
    public static void main(String[] args){
        new MainMenu();
    }
    public static void playTheme(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    
    synchronized public static void stopTheme(){
        sound.stop();
    }
}
