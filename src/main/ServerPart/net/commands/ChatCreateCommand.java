package main.ServerPart.net.commands;

import main.ServerPart.Store.MessageStoreClass;
import main.ServerPart.Store.UserStoreClass;
import main.ServerPart.net.Session;
import main.messages.CreateChatMessage;
import main.messages.Message;
import main.messages.MessagesType;
import main.messages.ServerStringMessage;
import main.myexceptions.CommandException;

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
