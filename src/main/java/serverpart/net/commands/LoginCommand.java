package main.java.serverpart.net.commands;

import main.java.serverpart.Store.MessageStoreClass;
import main.java.serverpart.Store.UserStoreClass;
import main.java.serverpart.net.Session;
import main.java.messages.LoginMessage;
import main.java.messages.Message;
import main.java.messages.MessagesType;
import main.java.messages.ServerUserMessage;
import main.java.myexceptions.AuthorizationException;
import main.java.myexceptions.CommandException;
import main.java.user.User;

import java.io.IOException;

/**
 * Класс обработки команды регистрации/авторизации пользователя
 */
public class LoginCommand implements Command {

    /** логин*/
    private String login;
    /** пароль*/
    private String pass;


    @Override
    public void execute(Session session, Message message, UserStoreClass usc, MessageStoreClass msc) throws CommandException {
        // получает лоигн и пароль из сообщения
        message = (LoginMessage) message;
        login = ((LoginMessage) message).getLogin();
        pass = ((LoginMessage) message).getPass();
        //если пользователь не существует в базе данных, то добавляем его в нее
        User user = null;
        try {
            user = new User(login, pass);
        } catch (AuthorizationException e) {
            e.printStackTrace();
        }
        User userNew = usc.addUser(user);
        if(userNew != null){
            ServerUserMessage serverMessage = new ServerUserMessage(MessagesType.SERVER_USER);
            serverMessage.setUser(userNew);
            try {
                session.send(serverMessage);
            } catch (IOException e) {
                e.printStackTrace();
                throw new CommandException("Ошибка в команде LoginCommand");
            }
        }




    }
}
