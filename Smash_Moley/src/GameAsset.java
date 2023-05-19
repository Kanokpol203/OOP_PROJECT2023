
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class GameAsset {
    private int score = 0;
    private String difficulty;
    private boolean running;
    private Font font1 = new Font("Arial", Font.PLAIN, 35);
    private GameBoard game;
    
    public GameAsset(GameBoard game){
        this.game = game;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }
    
    public void changeScore(int score){
        if(this.score + score >= 0 ){
            this.score += score;
        }
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getScore() {
        return score;
    }

    public String getDifficulty() {
        return difficulty;
    }
    public void redraw(Graphics g2d){
        g2d.setFont(font1);
        FontMetrics metrics = g2d.getFontMetrics(font1);
        Rectangle2D bounds = metrics.getStringBounds("SCORE", g2d);
        g2d.drawString("SCORE", 0, (int)bounds.getHeight());
        g2d.drawString(score+"", 0, (int)bounds.getHeight()*2);
        if(!game.gamestart){
            bounds = metrics.getStringBounds("GAME SET", g2d);
            g2d.drawString("GAME SET", (game.getWidth()/2) - ((int)bounds.getWidth()/2), (game.getHeight()/2)-((int)bounds.getHeight()));
            bounds = metrics.getStringBounds("Score : " + score, g2d);
            g2d.drawString("Score : " + score, (game.getWidth()/2) - ((int)bounds.getWidth()/2), (game.getHeight()/2)+((int)bounds.getHeight()/2));
        }
    }
    
}
