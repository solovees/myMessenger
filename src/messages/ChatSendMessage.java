package messages;

/**
 * Класс сообщение, для отправки сообщения в чат
 * @author Егор Соловьев
 */
public class ChatSendMessage extends ChatMessage {
    /** Текстовое сообщение */
    private String content;


    /**
     * конструктор
     * @param type - тип сообщения
     */
    public ChatSendMessage(MessagesType type) {
        super(type);
    }

    /**
     * метод возвращает текст сообщения
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * метод устанавливает текст сообщения
     * @param content текс сообщения
     */
    public void setContent(String content) {
        this.content = content;
    }
}
