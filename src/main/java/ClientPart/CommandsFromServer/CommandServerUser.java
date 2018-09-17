package main.java.ClientPart.CommandsFromServer;

import main.java.ClientPart.ClientHandler;
import main.java.messages.Message;
import main.java.messages.ServerUserMessage;
import main.java.user.User;

/**
 * класс для обработки команды сервера
 */
public class CommandServerUser implements  ICommandServer {
    @Override
    public void execute(Message msg) {
        msg = (ServerUserMessage)msg;
        User user = ((ServerUserMessage) msg).getUser();
        ClientHandler.setUser(user);
        System.out.print("\nДанные пользователя" +
                "\nid: " + user.getId().toString() +
                "\nlogin: " + user.getLogin() +
                "\nname: " + user.getName());
    }
}
