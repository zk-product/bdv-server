package cn.piesat.biserver.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author zk
 * @date 2020/1/20 15:23
 */
public class TimerTest {

    public static void main(String[] args) {

        while (true) {
            Timer t = timeMethod();
            cancel(t);
        }
    }

    public static Timer timeMethod() {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("触发");
            }
        }, 1000,1000);
        return t;
    }
    public static void cancel(Timer t) {
        t.cancel();
    }
}
