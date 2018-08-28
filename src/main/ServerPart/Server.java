package main.ServerPart;

import main.ServerPart.net.Session;
import main.protocol.BinaryProtocol;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Класс сервер
 * @author  Егор Соловьев
 */
public class Server {
    /** Пул потоков для работы с подключениями*/
    static ExecutorService executeIt = Executors.newFixedThreadPool(2);
    /** Протокол для сериализации и десериализации сообщений */
    static BinaryProtocol protocol = new BinaryProtocol();

    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(3345);
            while (!serverSocket.isClosed()){
                Socket client = serverSocket.accept();
                executeIt.submit(new ServerThread(client, protocol ,new Session(client)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
