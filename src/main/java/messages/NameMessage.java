package main.java.messages;

/**
 * Класс сообщения для установки никнейма пользователя
 * @author Егор Соловьев
 */
public class NameMessage extends Message {

    /** Имя пользователя*/
    private String name;

    /** id пользоателя */
    private Long id;
    /**
     * конструктор
     * @param type - тип сообщения
     */
    public NameMessage(MessagesType type) {
        super(type);
    }

    /**
     * метод устанавливает Id
     * @param id - id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * метод возвращает id
     * @return
     */
    public Long getId() {
        return id;
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
