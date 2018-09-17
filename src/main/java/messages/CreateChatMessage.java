package main.java.messages;

import java.util.ArrayList;

/**
 * Класс сообщение для создания чата
 * @author Егор Соловьев
 */
public class CreateChatMessage extends Message{

    /** ids пользвателей с которыми создать чат*/
    private ArrayList<Long> ids;

    /** админ чата */
    private Long admin;

    /**
     * конструктор
     * @param type - тип сообщения
     */
    public CreateChatMessage(MessagesType type) {
        super(type);
    }

    /**
     * метод возвращает список id учатников для чата
     * @return
     */
    public ArrayList<Long> getIds() {
        return ids;
    }

    /**
     * метод возращает админа чата
     * @return
     */
    public Long getAdmin() {
        return admin;
    }

    /**
     * метод устанавливает админа чата
     * @param admin - админ
     */
    public void setAdmin(Long admin) {
        this.admin = admin;
    }

    /**
     * метод устанавливает id участников чата
     * @param ids - список id участников
     */
    public void setIds(ArrayList<Long> ids) {
        this.ids = ids;
    }
}
