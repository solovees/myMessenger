package main.serverpart.net.commands;

import main.serverpart.Store.MessageStoreClass;
import main.serverpart.Store.UserStoreClass;
import main.serverpart.net.Session;
import main.messages.ChatSendMessage;
import main.messages.Message;
import main.messages.MessagesType;
import main.messages.ServerStringMessage;
import main.myexceptions.CommandException;

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
