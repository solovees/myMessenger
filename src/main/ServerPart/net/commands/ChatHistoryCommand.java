package main.ServerPart.net.commands;

import main.ServerPart.Store.MessageStoreClass;
import main.ServerPart.Store.UserStoreClass;
import main.ServerPart.net.Session;
import main.messages.ChatMessage;
import main.messages.Message;
import main.messages.MessagesType;
import main.messages.TextMessage;
import main.myexceptions.CommandException;

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
    private LinkedList<String> list;
    /**Сообщение для отправки переписки*/
    private TextMessage text;

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

        text = new TextMessage(MessagesType.SERVER_HISTORY);
        text.setText(list);
        try {
            session.send(text);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommandException("Ошибка в команде ChatHistoryCommand");
        }
    }
}
