package main.ClientPart;

import main.ConnectionHandler;
import main.messages.Message;
import main.myexceptions.ProtocolException;
import main.protocol.BinaryProtocol;
import main.protocol.Protocol;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Клиент для тестирования серверного приложения
 */
public class ClientHandler implements ConnectionHandler {


    /**
     * Пользователь сессии
     */
    private String login;

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
     * метод инициализирует сокет
     * @throws IOException
     */
    public void initSocket(String host, int port) throws IOException {
         this.socket = new Socket(host, port);
        in = socket.getInputStream();
        out = socket.getOutputStream();

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
        switch (cmdCommand) {
            case "/login":
                command = new CommandLogin();
                break;
            case "/help":
                command = new CommandHelp();
                break;
            case "/user":

                break;
            case "/user_info":

                break;
            case "/user_pass":

                break;
            case "/chat_list":

                break;
            case "/chat_create":

                break;
            case "/chat_history":

                break;
            case "/chat_find":

                break;
            case "/chat_send":

                break;
            default:
                System.out.print("Не верная команда, обратитесь на помощью /help");
        }
        command.execute(tokens);
    }


    @Override
    public void send(Message msg) throws  IOException {
        Protocol protocol = new BinaryProtocol();
        out.write(protocol.encode(msg));
        out.flush();
    }

    @Override
    public void onMessage(Message msg)  {

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
        while (!client.getSocket().isClosed()){
            System.out.println("Введите сообщение: ");
            String line = scanner.nextLine();
            client.processInput(line);
        }
    }
}
