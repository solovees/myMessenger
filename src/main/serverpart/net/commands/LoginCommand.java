package main.serverpart.net.commands;

import main.serverpart.Store.MessageStoreClass;
import main.serverpart.Store.UserStoreClass;
import main.serverpart.net.Session;
import main.messages.LoginMessage;
import main.messages.Message;
import main.messages.MessagesType;
import main.messages.ServerUserMessage;
import main.myexceptions.AuthorizationException;
import main.myexceptions.CommandException;
import main.user.User;

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
