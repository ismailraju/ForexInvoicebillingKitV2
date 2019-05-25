
import com.istl.util.Utils;
import java.util.Timer;
import java.util.TimerTask;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class test {

    static int i = 0;
    static boolean flg = false;

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask timerTask;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                i++;
                System.out.println("running...." + i);
                if (i>5) {
                    timer.cancel();
                    timer.purge();
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);
        

    }
}
