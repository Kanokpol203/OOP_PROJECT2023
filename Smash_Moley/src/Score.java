/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;
import javax.swing.event.*;
/**
 *
 * @author USER
 */
public class Score extends JPanel{
    
    private JLabel ScoreLabel;
    private Font Font1 = new Font("Arial", Font.PLAIN, 70);
    private int S = 0;
    private GameBoard game;
    
    public Score()
    {
        
    }
    
    public Score(GameBoard game)
    {
        ScoreLabel = new JLabel("");
        ScoreLabel.setBounds(300, 230, 200, 100);
        ScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        ScoreLabel.setFont(Font1);
        
        this.game = game;
        
        ScoreLabel.setText("00000");
    }
    
    public void ScoreCount()
    {
        ScoreLabel.setText(S + "");
    }
    
    public void BombHit()
    {
        //S += -200;
        this.setScore(this.getScore() - 200);
        System.out.println(this.S);
    }
    
    public void MoleHit()
    {
        this.setScore(this.getScore() + 100);
        //S += 100;
        System.out.println(this.S);
    }
    
    public void redraw(Graphics2D g2d){
        g2d.setFont(Font1);
        FontMetrics metrics = g2d.getFontMetrics(Font1);
        Rectangle2D bounds = metrics.getStringBounds((S + ""), g2d);
        g2d.drawString((S + ""), game.getScreen().getWidth()/2 - (int)bounds.getWidth()/2, game.getScreen().getTilesize()/2);
    }
    
    public int getScore()
    {
        return S;
    }
    
    public void setScore(int score)
    {
        S = score;
    }
}
