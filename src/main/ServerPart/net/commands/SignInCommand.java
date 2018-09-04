package main.ServerPart.net.commands;

import main.ServerPart.Store.MessageStoreClass;
import main.ServerPart.Store.UserStoreClass;
import main.ServerPart.net.Session;
import main.messages.*;
import main.myexceptions.AuthorizationException;
import main.myexceptions.CommandException;
import main.user.User;

import java.io.IOException;

public class SignInCommand implements Command {
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
        User user = null;
        user = usc.getUser(login,pass);
        ServerUserMessage serverMessage = new ServerUserMessage(MessagesType.SERVER_USER);
        if (user != null){
            serverMessage.setUser(user);
            try {
                session.send(serverMessage);
            } catch (IOException e) {
                e.printStackTrace();
                throw new CommandException("Ошибка в команде LoginCommand");
            }
        }

    }
}
