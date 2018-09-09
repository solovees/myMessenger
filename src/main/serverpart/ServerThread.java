package main.serverpart;

import main.serverpart.net.Session;
import main.messages.Message;
import main.protocol.BinaryProtocol;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;

/**
 * Класс поток для выполнения сокетного соединения
 * @author Егор Соловьев
 */
public class ServerThread extends Thread{
    /** сокет для соединения с клиентом */
    private Socket socket;
    /** протокол передачи сообщений */
    private BinaryProtocol binaryProtocol;
    /** массив байт для сериализации сообщения */
    final byte[] buf;
    /** сессия клиента*/
    private Session session;
    /** поток ввода для чтения данных из сокета */
    private InputStream in;

    /**
     * конструктор
     * @param protocol - протокол сериализации
     * @param session - сессия
     */
    public ServerThread(BinaryProtocol protocol, Session session){
        this.socket = session.getSocket();
        this.binaryProtocol = protocol;
        this.session = session;
        this.buf = new byte[1024 * 64];
    }

    @Override
    public void run() {
        try {
            in = socket.getInputStream();
            // пока открыто сокетное соединения читает поток байт из потока ввода
            while (!socket.isClosed()){
                int read = in.read(buf);
                if (read > 0) {
                    // Читается поток байт, его нужно раскодировать с помощью протокола
                    Message msg = binaryProtocol.decode(Arrays.copyOf(buf, read));
                    //передать сообщение в сессию для выполнения команды
                    session.onMessage(msg);
                }
                System.out.print("Сообщение отправленно клиенту\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // закрывает ресурсы
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
