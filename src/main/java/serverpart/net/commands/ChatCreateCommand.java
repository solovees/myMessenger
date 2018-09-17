package main.java.serverpart.net.commands;

import main.java.serverpart.Store.MessageStoreClass;
import main.java.serverpart.Store.UserStoreClass;
import main.java.serverpart.net.Session;
import main.java.messages.CreateChatMessage;
import main.java.messages.Message;
import main.java.messages.MessagesType;
import main.java.messages.ServerStringMessage;
import main.java.myexceptions.CommandException;

import java.io.IOException;

/**
 * Класс команда создания чата
 * @author Егор Соловьев
 */
public class ChatCreateCommand implements Command {

    /** id админа */
    private Long admine;
    /** id чата */
    private Long chatId;

    @Override
    public void execute(Session session, Message message , UserStoreClass usc, MessageStoreClass msc) throws CommandException {
        message = (CreateChatMessage)message;
        admine = ((CreateChatMessage) message).getAdmin();
        chatId = msc.addChat(admine);
        msc.addUserToChat(admine, chatId);
        for(Long i: ((CreateChatMessage) message).getIds()){
            msc.addUserToChat(i, chatId);
        }

        try {
            session.send(new ServerStringMessage(MessagesType.SERVER_STRING,"Чат успешно создан"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommandException("Ошибка при создании чата");
        }

    }
}
