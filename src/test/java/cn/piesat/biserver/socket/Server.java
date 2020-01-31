package cn.piesat.biserver.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author zk
 * @date 2020/1/20 16:55
 */
public class Server {
    static ExecutorService threadPool = Executors.newCachedThreadPool();
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(10000);
            while (true) {
                Socket accept = socket.accept();

                threadPool.execute(() -> {
                    Timer t = new Timer();
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("连接超时。。。。");
                        }
                    },3000, 3000);
                    try {
                        InputStream inputStream = accept.getInputStream();
                        System.out.println("有数据");
                        byte[] b = new byte[4];
                        int read = 0;
                        inputStream.read(b);
                        t.cancel();
                        System.out.println(new String(b));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
