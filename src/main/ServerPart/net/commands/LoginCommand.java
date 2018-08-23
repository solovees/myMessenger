package main.ServerPart.net.commands;

import main.ServerPart.Store.MessageStoreClass;
import main.ServerPart.Store.UserStoreClass;
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
    public void execute(Session session, Message message, UserStoreClass usc, MessageStoreClass msc) throws CommandException {
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

    }
}
