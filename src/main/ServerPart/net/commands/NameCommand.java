package main.ServerPart.net.commands;

import main.ServerPart.Store.MessageStoreClass;
import main.ServerPart.Store.UserStoreClass;
import main.ServerPart.net.Session;
import main.messages.Message;
import main.messages.NameMessage;
import main.myexceptions.CommandException;
import main.myexceptions.IllegalAcceptToUser;


public class NameCommand implements Command {

    /** имя пользователя */
    private String name;

    @Override
    public void execute(Session session, Message message, UserStoreClass usc, MessageStoreClass msc) throws CommandException, IllegalAcceptToUser {
        if(session.getUser() == null){
            throw new IllegalAcceptToUser("Регистрация не пройдена");
        }
        else{
            message = (NameMessage) message;
            name = ((NameMessage) message).getName();
            session.getUser().setName(name);
            usc.updateUser(session.getUser());
        }

    }
}
