import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu implements MouseListener, Screen_Size{
    private JFrame f;
    private JPanel p1, p2, p3, p4, p5;
    private JButton b1, b2;
    private JLabel l1;
    
    public MainMenu(){
        f = new JFrame("NoobMenu");
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        b1 = new JButton("Start");
        b2 = new JButton("Exit");
        l1 = new JLabel("SMASH MOLEY!", JLabel.CENTER);
        
        b1.addMouseListener(this);
        b2.addMouseListener(this);
        
        l1.setFont(new Font("arial", Font.BOLD, 26));
        p5.setBackground(Color.cyan);
        p2.setBackground(Color.cyan);
        p3.setBackground(Color.cyan);
        p4.setBackground(Color.green);
        p1.setBackground(Color.green);
        
        p1.setLayout(new GridLayout(5, 1));
        p5.add(l1);
        p1.add(p5);
        p3.add(b1);
        p4.add(b2);
        p1.add(p2);
        p1.add(p3);
        p1.add(p4);
        f.add(p1);
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(Screen_Size.WIDTH, Screen_Size.HEIGHT);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(b1)){
            new Starter();
            f.dispose();
            
        }
        else if(e.getSource().equals(b2)){
            f.dispose();
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
        if(e.getSource().equals(b1)){
            b1.setBackground(Color.gray);
        }
        else if(e.getSource().equals(b2)){
            b2.setBackground(Color.gray);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        b1.setBackground(Color.white);
        b2.setBackground(Color.white);
    }
    
    public static void main(String[] args){
        new MainMenu();
    }
}
