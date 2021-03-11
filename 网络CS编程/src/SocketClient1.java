import java.io.*;
import java.net.Socket;

/**
 * @author Super-Zhang
 * @date 2021-03-11 9:27
 * 客户端socket代码
 */
public class SocketClient1 {
    public static void main(String[] args) throws IOException {
//        1.创建客户端Socket，指定服务器地址和接口
        Socket socket = new Socket("localhost", 10086);
//        2.利用输出流向服务端发送消息。
        OutputStream os = socket.getOutputStream();//字节输出流。
        PrintWriter pw = new PrintWriter(os);//将输出流包装成打印流。
        pw.write("用户名：admin ； 密码：123");
        pw.flush();
        socket.shutdownOutput();  //注意别关错了。
//        3.获取输入流，并读取服务端相应的信息。
        InputStream is=socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));//带上字符编码，否则会乱码
        String info=null;
        while ((info=br.readLine())!=null){
            System.out.println("我是客户端，服务器端说："+info);
        }
        //4.关闭资源
        br.close();
        is.close();
        pw.close();
        os.close();
        socket.close();
    }

}
