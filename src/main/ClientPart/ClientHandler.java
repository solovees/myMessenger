package main.ClientPart;

import main.ClientPart.ClientCommands.*;
import main.ClientPart.CommandsFromServer.*;
import main.ConnectionHandler;
import main.messages.Message;
import main.messages.MessagesType;
import main.myexceptions.ProtocolException;
import main.protocol.BinaryProtocol;
import main.protocol.Protocol;
import main.user.User;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

import static main.messages.MessagesType.SERVER_CHAT_IDS;
import static main.messages.MessagesType.TEXT_MESS;

/**
 * Клиент для тестирования серверного приложения
 */
public class ClientHandler implements ConnectionHandler {


    /**
     * Пользователь сессии
     */
    private static User user = null;

    /**
     * сокет на клиента
     */
    private Socket socket;

    /**
     * Протокол, хост и порт
     *
     * */
    private Protocol protocol;
    private int port;
    private String host;

    /**
     * С каждым сокетом связано 2 канала in/out
     */
    private InputStream in;
    private OutputStream out;

    /**
     * метод возвращает поток ввода
     * @return
     */
    public InputStream getIn() {
        return in;
    }

    /**
     * метод возвращает поток вывода
     * @return
     */
    public OutputStream getOut() {
        return out;
    }

    /**
     * Тред "слушает" сокет на наличие входящих сообщений от сервера
     */
    private Thread socketThread;

    /**
     * метод возвращает протокол
     * @return
     */
    public Protocol getProtocol() {
        return protocol;
    }

    /**
     * метод устанавливает протокол
     * @param protocol - протокол
     */
    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * метод возвращает порт
     * @return
     */
    public int getPort() {
        return port;
    }

    /**
     * метод устанавливает порт
     * @param port - порт
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * метод возвращает хост
     * @return
     */
    public String getHost() {
        return host;
    }

    /**
     * метод устанавливает хост
     * @param host - хост
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * метод устанавливает пользователя
     * @param user - пользователь
     */
    public static void setUser(User user) {
        ClientHandler.user = user;
    }

    /**
     * метод возвращает пользователя
     * @return
     */
    public static User getUser() {
        return user;
    }

    /**
     * метод инициализирует сокет
     * @throws IOException
     */
    public void initSocket(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        this.in = socket.getInputStream();
        this.out = socket.getOutputStream();
        setProtocol(new BinaryProtocol());

        /**
         * Инициализируем поток-слушатель. Синтаксис лямбды скрывает создание анонимного класса Runnable
         */
        socketThread = new Thread(() -> {
            final byte[] buf = new byte[1024 * 64];
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    // Здесь поток блокируется на ожидании данных
                    int read = in.read(buf);
                    if (read > 0) {
                        // По сети передается поток байт, его нужно раскодировать с помощью протокола
                        Message msg = protocol.decode(Arrays.copyOf(buf, read));
                        onMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
        socketThread.start();
    }

    public Socket getSocket() {
        return socket;
    }

    /**
     * Обрабатывает входящую строку, полученную с консоли
     */
    public void processInput(String line) throws IOException, ProtocolException {
        String[] tokens = line.split(" ");
        String cmdCommand = tokens[0];
        ICommand command = null;
        Message message = null;
        switch (cmdCommand) {
            case "/sign_in":
                command = new CommandSignIn();
                message = command.execute(tokens);
                send(message);
                break;
            case "/login":
                command = new CommandLogin();
                message = command.execute(tokens);
                send(message);
                break;
            case "/help":
                command = new CommandHelp();
                command.execute(tokens);
                break;
            case "/user":
                command = new CommandUser();
                message = command.execute(tokens);
                send(message);
                break;
            case "/user_info":
                command = new CommandUserInfo();
                message = command.execute(tokens);
                send(message);
                break;
            case "/user_pass":
                command = new CommandPass();
                message = command.execute(tokens);
                send(message);
                break;
            case "/chat_list":
                command = new CommandChatList();
                message = command.execute(tokens);
                send(message);
                break;
            case "/chat_create":
                command = new CommandChatCreate();
                message = command.execute(tokens);
                send(message);
                break;
            case "/chat_history":
                command = new CommandChatHistory();
                message = command.execute(tokens);
                send(message);
                break;
            case "/chat_find":
                command = new CommandChatFind();
                message = command.execute(tokens);
                send(message);
                break;
            case "/chat_send":
                command = new CommandSend();
                message = command.execute(tokens);
                send(message);
                break;
            default:
                System.out.print("Не верная команда, обратитесь на помощью /help");
                break;
        }
    }


    @Override
    public void send(Message msg) throws  IOException {
        Protocol protocol = new BinaryProtocol();
        out.write(protocol.encode(msg));
        out.flush();
    }

    @Override
    public void onMessage(Message msg)  {
        MessagesType type = msg.getMessagesType();
        ICommandServer commandServer = null;
        switch (type){
            case SERVER_USER:           // сообщений от сервера вернуть User
                commandServer = new CommandServerUser();
                break;
            case SERVER_STRING:         // сообщение от сервера текстовое
                commandServer = new CommandString();
                break;
            case SERVER_CHAT_IDS:       // сообщение от сервера с списком чатов
                commandServer = new CommandChatIds();
                break;
            case TEXT_MESS:
                commandServer = new CommandText();
                break;
        }
        commandServer.execute(msg);
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


    public static void main(String[] args) throws Exception {

        ClientHandler client = new ClientHandler();
        client.initSocket("127.0.0.1", 3345);
        System.out.println("Client connected to socket");
        DataOutputStream oos = new DataOutputStream(client.getOut());
        DataInputStream ois = new DataInputStream(client.getIn());
        Scanner scanner = new Scanner(System.in);
        String line = "";
        System.out.println("\nДля выхода нажмите q + enter. Для получения списка команд /help");
        while (!line.equals("q")){
            System.out.println();
            line = scanner.nextLine();
            client.processInput(line);

        }
    }
}
