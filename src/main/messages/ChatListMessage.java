package main.messages;

/**
 * Класс сообщения , все чаты пользователя
 * @author Егор Соловьев
 */
public class ChatListMessage extends Message {

    /** id пользователя*/
    private Long userId;

    /**
     * конструктор
     * @param type - тип сообщения
     */
    public ChatListMessage(MessagesType type) {
        super(type);
    }

    /**
     * метод устанавливает id пользователя
     * @param userId - id пользователя
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * метод возвращает id пользователя
     * @return
     */
    public Long getUserId() {
        return userId;
    }
}
