package main.java.serverpart.net.commands;

import main.java.serverpart.Store.MessageStoreClass;
import main.java.serverpart.Store.UserStoreClass;
import main.java.serverpart.net.Session;
import main.java.messages.*;
import main.java.myexceptions.CommandException;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Класс команда для получения списка чптов
 * @author  Егор Соловьев
 */
public class ChatListCommand implements Command {
    /** список id чата*/
    private LinkedList<Long> ids;
    /** сообщение отправляемое пользователю */
    private ServerChatsIdMessege serverMsg;


    @Override
    public void execute(Session session, Message message, UserStoreClass usc, MessageStoreClass msc) throws CommandException {
        message = (ChatListMessage) message;
        session.setUser(usc.getUserByLogin(((ChatListMessage) message).getLogin()));

        ids = msc.getChatsByUserId(session.getUser().getId());
        serverMsg = new ServerChatsIdMessege(MessagesType.SERVER_CHAT_IDS);
        serverMsg.setChatsIds(ids);
        try {
            session.send(serverMsg);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommandException("Ошибка в команде ChatListCommand");
        }


    }
}
