package com.xdd.coffeecoin;

import com.xdd.coffeecoin.commands.Command;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by qingying.xdd on 18/8/17.
 */
public class CoffeeCoin {
  private static final String TIP = "coffee coin> ";

  private static boolean isTerminated = false;

  public static void main(String[] args) throws IOException {
    int port = 7000;
    // 在端口上创建一个服务器套接字
    ServerSocket serverSocket = new ServerSocket(port);
    // 监听来自客户端的连接, only receive one client
    Socket socket = serverSocket.accept();

    DataInputStream dis =
            new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    DataOutputStream dos =
            new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

    do {
      String data = dis.readUTF();
      System.out.println("服务器端收到数据为：" + data);
      dos.writeUTF("reponse for :" + data);
      dos.flush();
    } while(dis.readByte() != 0);

    socket.close();
    serverSocket.close();
  }

  private static String executeCmd(Command clientCmd)
          throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
    if (clientCmd.isQuit()) {
      isTerminated = true;
      return "good bye! ^v^";
    }

    return clientCmd.execute().toString();
  }
}
