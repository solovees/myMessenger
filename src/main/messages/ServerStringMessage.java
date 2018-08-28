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
     */
    public ServerStringMessage(MessagesType type) {
        super(type);
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
