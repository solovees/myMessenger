package main.ServerPart.net.commands;

import main.ServerPart.Store.MessageStoreClass;
import main.ServerPart.Store.UserStoreClass;
import main.ServerPart.net.Session;
import main.messages.Message;
import main.messages.MessagesType;
import main.messages.NameMessage;
import main.messages.ServerStringMessage;
import main.myexceptions.CommandException;
import main.myexceptions.IllegalAcceptToUser;
import main.user.User;

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
            user = usc.updateUser(session.getUser());
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
