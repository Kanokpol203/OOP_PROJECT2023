/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Mr.FriendZone SAD
 */
public class Timer implements java.lang.Runnable {
    
    @Override
    public void run() {
        this.runTimer();
    }
    
    public void runTimer(){
        int i = 30;
        while (i>=0){
            System.out.println("Remaining: "+ i +" seconds");
            try {
                i--;
                Thread.sleep(1000L);    // 1000L = 1000ms = 1 second
            }
            catch (InterruptedException e) {
                //I don't think you need to do anything for your particular problem
            }
            if(i == 0)
            {
                System.out.println("Time Out");
            }
        }
    }
}
