package main.java.serverpart.net.commands;

import main.java.serverpart.Store.MessageStoreClass;
import main.java.serverpart.Store.UserStoreClass;
import main.java.serverpart.net.Session;
import main.java.messages.Message;
import main.java.messages.MessagesType;
import main.java.messages.NameMessage;
import main.java.messages.ServerStringMessage;
import main.java.myexceptions.CommandException;
import main.java.user.User;

import java.io.IOException;

/**
 * Класс команда для установки имени у пользователя
 * @author Егор Соловьев
 */
public class NameCommand implements Command {

    /** имя пользователя */
    private String name;

    @Override
    public void execute(Session session, Message message, UserStoreClass usc, MessageStoreClass msc) throws CommandException{
        //ищем пользователя по логину, если не нашли то бросаем исключение
        User user = usc.getUserByLogin(message.getLogin());
        if(user == null){
            throw new CommandException("Регистрация не пройдена");
        }
        else{
            // преобразуем сообщение к нужному типу и добавляем имя в БД
            message = (NameMessage) message;
            name = ((NameMessage) message).getName();
            user.setName(name);
            usc.updateUser(user);
            session.setUser(user);
            try {
                session.send(new ServerStringMessage(MessagesType.SERVER_STRING, "Операция успешна выполненна"));
            } catch (IOException e) {
                e.printStackTrace();
                throw new CommandException("Ошибка в команде NameCommand");
            }
        }


    }
}
