package main.serverpart.net;

import main.ConnectionHandler;
import main.blockingqueue.*;
import main.messages.Message;
import main.protocol.BinaryProtocol;
import main.protocol.Protocol;
import main.user.User;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Здесь храним всю информацию, связанную с отдельным клиентом.
 * - объект User - описание пользователя
 * - сокеты на чтение/запись данных в канал пользователя
 */
public class Session implements ConnectionHandler {

    /**
     * Пользователь сессии, пока не прошел логин, user == null
     * После логина устанавливается реальный пользователь
     */
    private User user;

    // сокет на клиента
    private Socket socket;



    /**
     * С каждым сокетом связано 2 канала in/out
     */
    private InputStream in;
    private OutputStream out;

    private MessageBlockingQueue bk;

    /**
     * Конструткор
     * @param socket - сокет
     */
    public Session(Socket socket, MessageBlockingQueue bk){
        //commands = new AllCommands();
        this.socket = socket;
        this.bk = bk;
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void send(Message msg) throws  IOException {
        Protocol protocol = new BinaryProtocol();
        out.write(protocol.encode(msg));
    }

    @Override
    public void onMessage(Message msg)  {
        System.out.print(msg.getLogin() + " - логин пользователя\n");
        bk.set(msg, this);
    }

    @Override
    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод возвращает пользователя
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * метод для установки пользователя
     * @param user - пользователь
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * метод возвращает сокет
     * @return
     */
    public Socket getSocket() {
        return socket;
    }


}
