package main.ClientPart.CommandsFromServer;

import main.ClientPart.ClientHandler;
import main.messages.Message;
import main.messages.ServerUserMessage;
import main.user.User;

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
