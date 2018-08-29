package main.ServerPart.net.commands;

import main.ServerPart.Store.MessageStoreClass;
import main.ServerPart.Store.UserStoreClass;
import main.ServerPart.net.Session;
import main.chat.Chat;
import main.messages.*;
import main.myexceptions.CommandException;

import java.io.IOException;
import java.util.ArrayList;
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
        session.setUser(usc.getUserById(((ChatListMessage) message).getUserId()));

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
