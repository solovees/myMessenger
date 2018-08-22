package main.ServerPart.net.commands;

import main.ServerPart.net.Session;
import main.messages.Message;
import main.messages.NameMessage;
import main.myexceptions.CommandException;


public class NameCommand implements Command {

    /** имя пользователя */
    private String name;

    @Override
    public void execute(Session session, Message message) throws CommandException {
        message = (NameMessage) message;
        name = ((NameMessage) message).getName();
        session.getUser().setName(name);
    }
}
