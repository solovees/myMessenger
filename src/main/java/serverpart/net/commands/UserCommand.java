package main.java.serverpart.net.commands;

import main.java.serverpart.Store.MessageStoreClass;
import main.java.serverpart.Store.UserStoreClass;
import main.java.serverpart.net.Session;
import main.java.messages.*;
import main.java.myexceptions.CommandException;
import main.java.user.User;

import java.io.IOException;

/**
 * Класс команда для получения информации о пользователе
 * @author Егор Соловьев
 */
public class UserCommand implements Command {

    @Override
    public void execute(Session session, Message message, UserStoreClass usc, MessageStoreClass msc) throws CommandException {
        message = (InfoMessage)message;
        User user = usc.getUserById(((InfoMessage) message).getId());
        ServerUserMessage userMessage = new ServerUserMessage(MessagesType.SERVER_USER);
        userMessage.setUser(user);
        try {
            session.send(userMessage);
            System.out.print(userMessage);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommandException("Ошибка в команде UserCommand");
        }

    }
}
