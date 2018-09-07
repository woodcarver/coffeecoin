package com.xdd.coffeecoin.p2p;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Created by qingying.xdd on 18/9/6.
 */
public class Server {
  private List<Server> serverList;

  public void start() {}
  public void discovery() {
    // update serverList here
  }
  public void broadcast(Object obj) {
    // broadcast transaction or block
  }

  public void receive() {
    // receive transaction or block
  }

  public static void main(String[] args) throws IOException {
    int port = 7000;
    // 在端口上创建一个服务器套接字
    ServerSocket serverSocket = new ServerSocket(port);
    // 监听来自客户端的连接
    Socket socket = serverSocket.accept();

    DataInputStream dis =
            new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    DataOutputStream dos =
            new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

    do {
      double length = dis.readDouble();
      System.out.println("服务器端收到的边长数据为：" + length);
      double result = length * length;
      dos.writeDouble(result);
      dos.flush();
    } while(dis.readInt() != 0);

    socket.close();
    serverSocket.close();
  }
}
