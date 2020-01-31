package cn.piesat.biserver.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author zk
 * @date 2020/1/20 16:55
 */
public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 10000);
            OutputStream outputStream = socket.getOutputStream();
            byte[] b = new byte[4];
            while (true) {
                outputStream.write(b);
                TimeUnit.SECONDS.sleep(1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
