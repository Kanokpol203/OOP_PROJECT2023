import javax.swing.JFrame;
public class Starter extends JFrame{
    public Starter(){
        GameBoard game = new GameBoard();
        this.add(game);
        this.setTitle("Smash Moley!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
        this.setResizable(false);
        game.startThread();
    }
    public static void main(String[] args){
        new Starter();
    }
}
