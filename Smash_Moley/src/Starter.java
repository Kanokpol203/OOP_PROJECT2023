import javax.swing.JFrame;
public class Starter extends JFrame{
    public Starter(){
        GameBoard game = new GameBoard("nm", this);
        this.add(game);
        this.setTitle("Smash Moley!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
        this.setResizable(false);
        game.startThread();
    }
    
    public Starter(String ez){
        if(ez == "ez"){
            System.out.println("ez");
            GameBoard game = new GameBoard("ez", this);
            this.add(game);
            this.setTitle("Smash Moley!");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.pack();
            this.setResizable(false);
            game.startThread();
        }
        else if(ez == "nm"){
            System.out.println("nm");
            GameBoard game = new GameBoard("nm", this);
            this.add(game);
            this.setTitle("Smash Moley!");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.pack();
            this.setResizable(false);
            game.startThread();
        }
        else if(ez == "hr"){
            System.out.println("hr");
            GameBoard game = new GameBoard("hr", this);
            this.add(game);
            this.setTitle("Smash Moley!");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.pack();
            this.setResizable(false);
            game.startThread();
        }
    }
    public void close(){
        this.dispose();
    }
    
    public static void main(String[] args){
        new Starter();
    }
}
