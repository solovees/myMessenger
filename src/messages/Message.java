package messages;

import java.io.Serializable;

/**
 * Класс сообщения
 * @author Егор Соловьев
 * @version 1.0
 */
public class Message  implements Serializable{
    /** Тип сообщения*/
    protected MessagesType messagesType;

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
}
