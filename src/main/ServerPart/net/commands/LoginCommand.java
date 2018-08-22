package main.ServerPart.net.commands;

import main.ServerPart.net.Session;
import main.messages.LoginMessage;
import main.messages.Message;
import main.myexceptions.CommandException;


public class LoginCommand implements Command {

    /** логин*/
    private String login;
    /** пароль*/
    private String pass;

    @Override
    public void execute(Session session, Message message) throws CommandException {
        message = (LoginMessage) message;
        login = ((LoginMessage) message).getLogin();
        pass = ((LoginMessage) message).getPass();
        session.getUser().setLogin(login);
        session.getUser().setPassword(pass);
    }
}
