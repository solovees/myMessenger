package main.messages;

/**
 * Класс сообщение, для отправки сообщения в чат
 * @author Егор Соловьев
 */
public class ChatSendMessage extends ChatMessage {
    /** Текстовое сообщение */
    private String content;
    /** логин пользователя */
    private String login;


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
