package main.messages;

/**
 * Класс сообщения , поиск в чате подстроки
 * @author Егор Соловьев
 */
public class ChatFindMessage extends ChatMessage {

    /** слова дял поиска в чате */
    private String substring;

    /**
     * конструктор
     * @param type - тип сообщения
     */
    public ChatFindMessage(MessagesType type) {
        super(type);
    }

    /**
     * метод возвращате слово для поиска в чате
     * @return
     */
    public String getRegex() {
        return substring;
    }

    /**
     * метод устанавливает слово для поиска в чате
     * @param regex - слово дял поиска в чате
     */
    public void setRegex(String regex) {
        this.substring = regex;
    }
}
