package main.java.serverpart.net.commands;

import main.java.serverpart.Store.MessageStoreClass;
import main.java.serverpart.Store.UserStoreClass;
import main.java.serverpart.net.Session;
import main.java.messages.ChatSendMessage;
import main.java.messages.Message;
import main.java.messages.MessagesType;
import main.java.messages.ServerStringMessage;
import main.java.myexceptions.CommandException;

import java.io.IOException;

/**
 * Класс команды отправить сообщение в чат
 * @author Егор Соловьев
 */
public class ChatSendCommand implements Command {

    /** id чата */
    private  Long idChat;


    @Override
    public void execute(Session session, Message message, UserStoreClass usc, MessageStoreClass msc) throws CommandException {
        idChat = ((ChatSendMessage) message).getChatId();
        session.setUser(usc.getUserByLogin(message.getLogin()));
        msc.addMessage(idChat, message, session.getUser().getId());
        try {
            session.send(new ServerStringMessage(MessagesType.SERVER_STRING, "Сообщение успешно отправленно"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommandException("Ошибка в команде ChatSendCommand");
        }

    }
}
