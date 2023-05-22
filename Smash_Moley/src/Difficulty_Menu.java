
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Difficulty_Menu implements MouseListener{
    private Screen_Size screen = new Screen_Size();
    private JFrame frame;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;
    private JPanel p5;
    private JPanel p6;
    private JButton easy_bt;
    private JButton normal_bt;
    private JButton hard_bt;
    private JLabel txt;
    private Background bg;

    public Difficulty_Menu() {
      frame = new JFrame("DifficultSelect");
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        p6 = new JPanel();
        bg = new Background(screen);

        easy_bt = new JButton("Easy");
        easy_bt.setFont(new Font("arial", Font.BOLD, 18));
        easy_bt.setBackground(Color.white);
        easy_bt.setPreferredSize(new Dimension(100, 50));
        easy_bt.addMouseListener(this);
        normal_bt = new JButton("Normal");
        normal_bt.setFont(new Font("arial", Font.BOLD, 18));
        normal_bt.setBackground(Color.white);
        normal_bt.setPreferredSize(new Dimension(100, 50));
        normal_bt.addMouseListener(this);
        hard_bt = new JButton("Hard");
        hard_bt.setFont(new Font("arial", Font.BOLD, 18));
        hard_bt.setBackground(Color.white);
        hard_bt.setPreferredSize(new Dimension(100, 50));
        hard_bt.addMouseListener(this);
        
        txt = new JLabel("Difficult Select", JLabel.CENTER);
        txt.setFont(new Font("arial", Font.BOLD, 64));
        txt.setForeground(Color.white);

        p1.setLayout(new GridLayout(2, 1));
        p2.setLayout(new BorderLayout());
        p3.setLayout(new GridLayout(4, 1));
        p4.setLayout(new FlowLayout());
        p5.setLayout(new FlowLayout());

        p4.add(easy_bt);
        p5.add(normal_bt);
        p6.add(hard_bt);
        p3.add(p4);
        p3.add(p5);
        p3.add(p6);
        p2.add(txt);
        p1.add(p2);
        p1.add(p3);
        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        p4.setOpaque(false);
        p5.setOpaque(false);
        p6.setOpaque(false);
        bg.add(p1);
        frame.add(bg);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screen.getWidth(), screen.getHeight());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(easy_bt)){
            new Starter("ez");
            frame.dispose();
            MainMenu.stopTheme();
        }
        else if(e.getSource().equals(normal_bt)){
            new Starter("nr");
            frame.dispose();
            MainMenu.stopTheme();
        }
        else if(e.getSource().equals(hard_bt)){
            new Starter("hr");
            frame.dispose();
            MainMenu.stopTheme();
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
        if(e.getSource().equals(easy_bt)){
            easy_bt.setBackground(Color.gray);
        }
        else if(e.getSource().equals(normal_bt)){
            normal_bt.setBackground(Color.gray);
        }
        else if(e.getSource().equals(hard_bt)){
            hard_bt.setBackground(Color.gray);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        easy_bt.setBackground(Color.white);
        normal_bt.setBackground(Color.white);
        hard_bt.setBackground(Color.white);
    }
    public static void main(String[] args){
        new Difficulty_Menu();
    }
    
}
