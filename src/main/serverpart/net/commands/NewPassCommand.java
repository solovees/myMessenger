package main.serverpart.net.commands;

import main.serverpart.Store.MessageStoreClass;
import main.serverpart.Store.UserStoreClass;
import main.serverpart.net.Session;
import main.messages.Message;
import main.messages.MessagesType;
import main.messages.NewPassMessage;
import main.messages.ServerStringMessage;
import main.myexceptions.CommandException;
import main.user.User;

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
