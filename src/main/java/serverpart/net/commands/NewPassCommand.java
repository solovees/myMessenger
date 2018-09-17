package main.java.serverpart.net.commands;

import main.java.serverpart.Store.MessageStoreClass;
import main.java.serverpart.Store.UserStoreClass;
import main.java.serverpart.net.Session;
import main.java.messages.Message;
import main.java.messages.MessagesType;
import main.java.messages.NewPassMessage;
import main.java.messages.ServerStringMessage;
import main.java.myexceptions.CommandException;
import main.java.user.User;

import java.io.IOException;

/**
 * Класс для выполнения команды установки новго пароля
 * @author Егор Соловьев
 */
public class NewPassCommand implements Command {
    /** новый пароль пользователя*/
    private String newPass;

    @Override
    public void execute(Session session, Message message, UserStoreClass usc, MessageStoreClass msc) throws CommandException {
        //приводим тип сообщения и обновляем пароль пользователю
        message = (NewPassMessage)message;
        User user;
        user = usc.getUserByLogin(message.getLogin());
        user.setPassword(newPass);
        user = usc.updateUser(user);
        session.setUser(user);
        try {
            session.send(new ServerStringMessage(MessagesType.SERVER_STRING, "Пароль обнавлен"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommandException("Ошибка в команде NewPassCommand");
        }
    }
}
