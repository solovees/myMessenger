package main.messages;

/**
 * Класс сообщения, поиск чата и возвращает историю сообщений чата
 * @author Егор Соловьев
 */
public class ChatMessage extends Message {

    /** id чата*/
    protected Long chatId;

    /**
     * конструктор
     * @param type - тип сообщения
     */
    public ChatMessage(MessagesType type) {
        super(type);
    }

    /**
     * метод устанавливает id чата
     * @param chatId - id  чата
     */
    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    /**
     * метод возвращает id чата
     * @return
     */
    public Long getChatId() {
        return chatId;
    }
}
