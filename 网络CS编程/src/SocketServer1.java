import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Super-Zhang
 * @date 2021-03-11 9:15
 * 服务端socket代码
 */
public class SocketServer1 {

    /**
     * 基于TCP协议的Socket通信，实现用户登录，服务端
     */

    public static void main(String[] args) throws IOException{
        //1.创建服务器端Socket，即ServerSocket，指定绑定的端口并监听。
        ServerSocket serverSocket = new ServerSocket(10086);//1024-65535之间
        //2.调用accept（）方法开始监听，等待客户端连接。阻塞知道客户端连接才会唤醒。
        Socket socket= serverSocket.accept();
        System.out.println("===============");
        //3.获取输入流，读取客户端信息。
        InputStream is=socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8"); //这里加上编码，防止读取乱码。
        BufferedReader br = new BufferedReader(isr);
//
        String info=null;
        while ((info=br.readLine())!=null){
            System.out.println("我是服务端，客户端说："+info);
        }
        socket.shutdownInput();//关闭输入流。
        //4.利用输入流，相应客户端请求。
        OutputStreamWriter outSW = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
        PrintWriter pw = new PrintWriter(outSW);
        pw.write("欢迎您！");
        pw.flush();

        //5.关闭资源
        pw.close();
        outSW.close();
        br.close();
        isr.close();
        is.close();
        socket.close();
        serverSocket.close();
    }


}
