package main.serverpart;

import main.blockingqueue.*;
import main.messages.MessagesType;
import main.serverpart.net.Session;
import main.protocol.BinaryProtocol;
import main.serverpart.net.commands.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс сервер
 * @author  Егор Соловьев
 */
public class Server {
    /** Пул потоков для работы с подключениями*/
    // static ExecutorService executeIt = Executors.newFixedThreadPool(2);
    /** Протокол для сериализации и десериализации сообщений */
    static BinaryProtocol protocol = new BinaryProtocol();

    // объект для хранения всех команд
    private static AllCommands commands;

    static MessageBlockingQueue messageBlockingStack = new MessageBlockingQueue();

    public static void main(String[] args){
        init();
        new ServerConsumer(messageBlockingStack,commands).start();
        try {
            ServerSocket serverSocket = new ServerSocket(3345);
            while (!serverSocket.isClosed()){
                Socket client = serverSocket.accept();
                new ServerThread(protocol ,new Session(client, messageBlockingStack)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * инициализирует команды
     */
    private static void init(){
        commands = new AllCommands();
        commands.addNewCommand(MessagesType.USER_LOGIN, new LoginCommand());
        commands.addNewCommand(MessagesType.USER_INFO, new UserCommand());
        commands.addNewCommand(MessagesType.USER_NAME, new NameCommand());
        commands.addNewCommand(MessagesType.USER_PASS, new NewPassCommand());
        commands.addNewCommand(MessagesType.CHAT_LIST, new ChatListCommand());
        commands.addNewCommand(MessagesType.CHAT_CREATE, new ChatCreateCommand());
        commands.addNewCommand(MessagesType.CHAT_HISTORY, new ChatHistoryCommand());
        commands.addNewCommand(MessagesType.CHAT_FIND, new ChatFindCommand());
        commands.addNewCommand(MessagesType.CHAT_SEND, new ChatSendCommand());
        commands.addNewCommand(MessagesType.USER_SIGN_IN, new SignInCommand());
    }
}
