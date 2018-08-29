package main.ServerPart.net.commands;

import main.ServerPart.Store.MessageStoreClass;
import main.ServerPart.Store.UserStoreClass;
import main.ServerPart.net.Session;
import main.messages.*;
import main.myexceptions.CommandException;
import main.myexceptions.IllegalAcceptToUser;
import main.user.User;

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
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommandException("Ошибка в команде UserCommand");
        }

    }
}
