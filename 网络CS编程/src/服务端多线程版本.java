import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Super-Zhang
 * @date 2021-03-11 10:03
 *
 *  服务器端接收到客户端消息之后，开启一个新的线程来处理通信，同时服务器重新开始阻塞等待新客户端连接。
 */
public class 服务端多线程版本 {
    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        服务端多线程版本 s2 = new 服务端多线程版本();
        s2.domain();
    }
    public void domain() throws IOException {
        ServerSocket s =new ServerSocket(10086);
        System.out.println("The Server is start: " + s);
        try {
            for(;;) {
                //阻塞,直到有客户端连接
                Socket socket = s.accept();
                //通过构造函数，启动线程
                new ServerThread(socket);
            }
        } finally {
            s.close();
        }
    }
    /**
     *
     *
     */
    public class ServerThread extends Thread {
        //服务器线程处理
        //和本线程相关的socket
        private Socket socket =null;
        //IO句柄
        private BufferedReader sin;
        private PrintWriter sout;
        public ServerThread(Socket socket) throws IOException {
            this.socket = socket;
            //初始化sin和sout的句柄
            sin = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            sout = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream(), "UTF-8")), true);
            //开启线程
            start();
        }
        public void run(){
            //服务器处理代码
            try {
                System.out.println("111111111111111111111111111-thread id:" + this.getId());
                String info =null;
                while((info=sin.readLine())!=null){
                    System.out.println("我是服务器，客户端说："+info);
                }
                socket.shutdownInput();
                //输出信息
                sout.write("欢迎您！");
                sout.flush();
                System.out.println("closing the server socket!");
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                System.out.println("close the Server socket and the io.");
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
