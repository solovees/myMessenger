package main.messages;

import java.util.LinkedList;

/**
 * Класс для передачи истории сообщений
 * @author Егор Соловьев
 */
public class ServerTextMessage extends Message {

    /** переписка*/
    private LinkedList<String> text;
    /**
     * конструктор
     * @param type - тип сообщения
     */
    public ServerTextMessage(MessagesType type) {
        super(type);
    }

    /**
     * метод устанавливает переписку
     * @param text - переписка
     */
    public void setText(LinkedList<String> text) {
        this.text = text;
    }

    /**
     * метод возвращает переписку
     * @return
     */
    public LinkedList<String> getText() {
        return text;
    }
}
