package main.java.messages;

/**
 * Класс сообщения для регистрации/авторизации пользователя
 * @author Егор Соловьев
 */
public class LoginMessage extends Message {
    /** пароль*/
    private String pass;

    /**
     * конструктор
     * @param type - тип сообщения
     */
    public LoginMessage(MessagesType type) {
        super(type);
    }

    /**
     * метод возвращает пароль
     * @return
     */
    public String getPass() {
        return pass;
    }

    /**
     * метод устанавлиает пароль
     * @param pass - пароль
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
}
