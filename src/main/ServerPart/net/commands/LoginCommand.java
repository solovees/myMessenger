package main.ServerPart.net.commands;

import main.ServerPart.Store.MessageStoreClass;
import main.ServerPart.Store.UserStoreClass;
import main.ServerPart.net.Session;
import main.messages.LoginMessage;
import main.messages.Message;
import main.messages.MessagesType;
import main.messages.ServerUserMessage;
import main.myexceptions.CommandException;

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
        if(usc.getUser(login,pass) == null){
            session.getUser().setLogin(login);
            session.getUser().setPassword(pass);
            usc.addUser(session.getUser());
        } else{
            session.getUser().setLogin(login);
            session.getUser().setPassword(pass);
        }
        ServerUserMessage serverMessage = new ServerUserMessage(MessagesType.SERVER_USER);
        serverMessage.setUser(session.getUser());
        try {
            session.send(serverMessage);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommandException("Ошибка в команде LoginCommand");
        }



    }
}
