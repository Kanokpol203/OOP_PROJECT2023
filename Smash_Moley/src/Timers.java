import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

//import javax.swing.JFrame;
import javax.swing.Timer;

public final class Timers {
    
    Font font1 = new Font("Arial", Font.PLAIN, 70);	
    Timer timer;	
    int second, minute;
    String ddSecond, ddMinute, strtime;	
    DecimalFormat dFormat = new DecimalFormat("00");
    GameBoard game;
    public Timers(GameBoard game)
    {
        //window = new JFrame();
	//window.setSize(800,600);
	//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//window.setLayout(null);
		
	//window.add(counterLabel);
        this.game = game;
	second = 0;
	minute = 3;
	countdownTimer();
	timer.start();
        strtime = "03" + ":" + "00";
    }
    
    public void countdownTimer() {
		
	timer = new Timer(1000, new ActionListener() {
			
            @Override
            public void actionPerformed(ActionEvent e) {
				
                second--;
		ddSecond = dFormat.format(second);
		ddMinute = dFormat.format(minute);	
		strtime	= ddMinute + ":" + ddSecond;
		if(second==-1) {
                    second = 59;
                    minute--;
                    ddSecond = dFormat.format(second);
                    ddMinute = dFormat.format(minute);	
                    strtime = ddMinute + ":" + ddSecond;
		}
				
                if(minute==0 && second==0) {
                    timer.stop();
                    game.gameThread = null;
		}
            }
        });
    }
    public void redraw(Graphics2D g2d){
        g2d.setFont(font1);
        g2d.drawString(strtime, game.TILESIZE+(game.TILESIZE/2), game.TILESIZE/2);
    }
}
