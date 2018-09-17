package main.java.serverpart.net.commands;

import main.java.serverpart.Store.MessageStoreClass;
import main.java.serverpart.Store.UserStoreClass;
import main.java.serverpart.net.Session;
import main.java.messages.ChatMessage;
import main.java.messages.Message;
import main.java.messages.MessagesType;
import main.java.messages.ServerTextMessage;
import main.java.myexceptions.CommandException;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Класс команда для получения истории чата
 * @author Егор Соловьев
 */
public class ChatHistoryCommand implements Command {
    /** список id сообщений*/
    private LinkedList<Long> ids;
    /**Список сообщений */
    private LinkedList<String> list = new LinkedList<>();
    /**Сообщение для отправки переписки*/
    private ServerTextMessage text;

    @Override
    public void execute(Session session, Message message, UserStoreClass usc, MessageStoreClass msc) throws CommandException {
        message = (ChatMessage)message;
        ids = (LinkedList<Long>) msc.getMessagesFromChat(((ChatMessage) message).getChatId());
        for(Long i: ids){
           String msg = msc.getMessageById(i);
            if( msg != null){
                list.add(msg);
            }
        }

        text = new ServerTextMessage(MessagesType.TEXT_MESS);
        text.setText(list);
        try {
            session.send(text);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommandException("Ошибка в команде ChatHistoryCommand");
        }
    }
}
