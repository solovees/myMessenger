package main.ServerPart.net.commands;

import main.ServerPart.Store.MessageStoreClass;
import main.ServerPart.Store.UserStoreClass;
import main.ServerPart.net.Session;
import main.messages.*;
import main.myexceptions.CommandException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ChatFindCommand implements Command {
    /** список id сообщений*/
    private LinkedList<Long> ids;
    /**Список сообщений */
    private LinkedList<String> list;
    /**Сообщение для отправки */
    private TextMessage text;
    /**Подстрока для поиска */
    private  String substring;

    @Override
    public void execute(Session session, Message message, UserStoreClass usc, MessageStoreClass msc) throws CommandException {
        message = (ChatFindMessage)message;
        ids = (LinkedList<Long>) msc.getMessagesFromChat(((ChatMessage) message).getChatId());
        for(Long i: ids){
            String msg = msc.getMessageById(i);
            if(checkSubstring(msg)){
                list.add(msg);
            }
        }

        text = new TextMessage(MessagesType.TEXT_MESS);
        text.setText(list);
        try {
            session.send(text);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommandException("Ошибка в команде ChatFindCommand");
        }
    }

    private boolean checkSubstring(String s){
        Pattern pat;
        Matcher mat;
        pat = Pattern.compile(substring);
        mat = pat.matcher(s);
        return mat.matches();
    }
}
