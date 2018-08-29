package main.messages;

/**
 * Класс сообщение для передачи строки от сервера к клиенту
 * @author Егор Соловьев
 */
public class ServerStringMessage extends Message {

    /** Текстовое сообщение от сервера клиенту*/
    String text;
    /**
     * конструктор
     * @param type - тип сообщения
     * @param text - сообщение
     */
    public ServerStringMessage(MessagesType type, String text) {
        super(type);
        this.text = text;
    }

    /**
     * метод возвращает текст сообщения
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * метод устанавливает текст сообщения
     * @param text - текст
     */
    public void setText(String text) {
        this.text = text;
    }
}
