package main.java.messages;

/**
 * Класс сообщения , все чаты пользователя
 * @author Егор Соловьев
 */
public class ChatListMessage extends Message {

    /**
     * конструктор
     * @param type - тип сообщения
     */
    public ChatListMessage(MessagesType type) {
        super(type);
    }

}
