package main.ServerPart.net;

import main.ServerPart.net.commands.*;
import main.messages.Message;
import main.messages.MessagesType;
import main.myexceptions.CommandException;
import main.myexceptions.ProtocolException;
import main.protocol.BinaryProtocol;
import main.protocol.Protocol;
import main.user.User;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
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

    // объект для хранения всех команд
    private AllCommands commands;

    /**
     * С каждым сокетом связано 2 канала in/out
     */
    private InputStream in;
    private OutputStream out;

    public Session(){
        commands = new AllCommands();
    }

    @Override
    public void send(Message msg) throws ProtocolException, IOException {
        Protocol protocol = new BinaryProtocol();
        in.read(protocol.encode(msg));
    }

    @Override
    public void onMessage(Message msg)  {
        try {
            commands.makeCommand(this, msg);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
            commands = null;
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

    private void init(){
        try {
            socket = new Socket(InetAddress.getLocalHost(), 80);
            in = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        commands.addNewCommand(MessagesType.USER_LOGIN, new LoginCommand());
        commands.addNewCommand(MessagesType.USER_INFO, new UserCommand());
        commands.addNewCommand(MessagesType.USER_NAME, new NameCommand());
        commands.addNewCommand(MessagesType.USER_PASS, new NewPassCommand());
        commands.addNewCommand(MessagesType.CHAT_LIST, new ChatListCommand());
        commands.addNewCommand(MessagesType.CHAT_CREATE, new ChatCreateCommand());
        commands.addNewCommand(MessagesType.CHAT_HISTORY, new ChatHistoryCommand());
        commands.addNewCommand(MessagesType.CHAT_FIND, new ChatFindCommand());
        commands.addNewCommand(MessagesType.CHAT_SEND, new ChatSendCommand());

    }
}
