package main.messages;

import java.io.Serializable;

/**
 * Класс сообщения
 * @author Егор Соловьев
 * @version 1.0
 */
public class Message implements Serializable{
    /** Тип сообщения*/
    protected MessagesType messagesType;

    /** логин*/
    private String login;

    /**
     * конструктор
     * @param type - тип сообщения
     */
    public Message(MessagesType type){
        this.messagesType = type;
    }

    /**
     * метод возвращает тип сообщения
     * @return
     */
    public MessagesType getMessagesType() {
        return messagesType;
    }

    /**
     * метод возвращает логин
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     * метод устанавливает логин
     * @param login - логин
     */
    public void setLogin(String login) {
        this.login = login;
    }

}
