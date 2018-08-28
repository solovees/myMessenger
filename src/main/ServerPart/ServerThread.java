package main.ServerPart;

import main.ServerPart.net.Session;
import main.messages.Message;
import main.messages.MessagesType;
import main.protocol.BinaryProtocol;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class ServerThread extends Thread{
    // сокет для соединения с клиентом
    private Socket socket;
    // протокол передачи сообщений
    private BinaryProtocol binaryProtocol;
    //
    final byte[] buf;
    private Session session;
    private InputStream in;

    public  ServerThread(Socket socket, BinaryProtocol protocol, Session session){
        this.socket = socket;
        this.binaryProtocol = protocol;
        this.session = session;
        this.buf = new byte[1024 * 64];
    }
    @Override
    public void run() {
        try {
             in = socket.getInputStream();

            while (!socket.isClosed()){

                int read = in.read(buf);
                if (read > 0) {
                    // Читается поток байт, его нужно раскодировать с помощью протокола
                    Message msg = binaryProtocol.decode(Arrays.copyOf(buf, read));
                    //передать сообщение в сессию для выполнения команды
                    session.onMessage(msg);
                }
                System.out.print("\nСообщение отправленно клиенту");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            session.close();
            try {
                in.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
