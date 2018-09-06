package main.serverpart.net.commands;

import main.serverpart.Store.MessageStoreClass;
import main.serverpart.Store.UserStoreClass;
import main.serverpart.net.Session;
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
    private LinkedList<String> list = new LinkedList<>();
    /**Сообщение для отправки */
    private ServerTextMessage text;
    /**Подстрока для поиска */
    private  String substring;

    @Override
    public void execute(Session session, Message message, UserStoreClass usc, MessageStoreClass msc) throws CommandException {
        message = (ChatFindMessage)message;
        substring = ((ChatFindMessage) message).getRegex();
        ids = (LinkedList<Long>) msc.getMessagesFromChat(((ChatMessage) message).getChatId());
        for(Long i: ids){
            String msg = msc.getMessageById(i);
            if(checkSubstring(msg, substring)){
                list.add(msg);
            }
            System.out.print(msg + substring);
        }
        text = new ServerTextMessage(MessagesType.TEXT_MESS);
        text.setText(list);
        try {
            session.send(text);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommandException("Ошибка в команде ChatFindCommand");
        }
    }

    /**
     * метод проверяет вхождение подстроки в строку
     * @param s - строка
     * @return
     */
    private boolean checkSubstring(String s, String substring){
        Pattern pat;
        Matcher mat;
        pat = Pattern.compile(substring);
        mat = pat.matcher(s);

        return mat.find();
    }
}
