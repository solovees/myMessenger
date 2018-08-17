package messages;

/**
 * Класс сообщения для получения информации о конкретном пользователе
 * @author Егор Соловьев
 */
public class InfoMessage extends Message{

    /** id пользователя, о котором нужно получить информацию*/
    private Long id;
    /**
     * конструктор
     * @param type - тип сообщения
     */
    public InfoMessage(MessagesType type) {
        super(type);
    }

    /**
     * метод возвращает id пользователя
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * метод устанавливает id  пользователя
     * @param id - id
     */
    public void setId(Long id) {
        this.id = id;
    }
}
