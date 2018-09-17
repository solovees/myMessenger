package main.java.messages;

import main.java.user.User;

import java.io.Serializable;

/**
 * Класс для передачи пользователя клиенту со стороны сервера
 * @author Егор Соловьев
 */
public class ServerUserMessage extends Message implements Serializable{

    /** Пользоваетль */
    private User user;

    /**
     * конструктор
     * @param type - тип сообщения
     */
    public ServerUserMessage(MessagesType type) {
        super(type);
    }

    /**
     * метод возвращает пользователя
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * метод устанавливает пользователя
     * @param user - пользоваетель
     */
    public void setUser(User user) {
        this.user = user;
    }
}
