package main.blockingqueue;

import main.messages.Message;
import main.serverpart.net.Session;

/**
 * Класс хранит сессию и сообщение пришедшие из этой сессии
 * @author Егор Соловьев
 */
public class SessionMessageStore {
    /**Сессия */
    private Session session;
    /**Сообщение */
    private Message message;

    /**
     * Коснтруктор
     * @param session - сессия
     * @param message - сообщение
     */
    public SessionMessageStore(Session session, Message message){
        this.session = session;
        this.message = message;
    }

    /**
     * метод возвращает сообщение
     * @return
     */
    public Message getMessage() {
        return message;
    }

    /**
     * метод возвращает сессию
     * @return
     */
    public Session getSession() {
        return session;
    }
}
