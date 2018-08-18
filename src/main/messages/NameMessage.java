package main.messages;

/**
 * Класс сообщения для установки никнейма пользователя
 * @author Егор Соловьев
 */
public class NameMessage extends Message {

    /** Имя пользователя*/
    private String name;
    /**
     * конструктор
     * @param type - тип сообщения
     */
    public NameMessage(MessagesType type) {
        super(type);
    }

    /**
     * метод устанавливает имя пользователя
     * @param name - имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * метод возвращает имя пользователя
     * @return
     */
    public String getName() {
        return name;
    }
}
