package my_timer;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author 86187
 */
public class TestTimer {
    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() + "嘻嘻嘻嘻嘻");
            }
        };

        System.out.println(new Date());
        Date date = new Timestamp(2022, 5, 8, 17, 29, 30, 0);

        timer.scheduleAtFixedRate(timerTask, date, 1000);

        Thread.sleep(5000000);
    }
}
