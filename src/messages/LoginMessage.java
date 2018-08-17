package messages;

/**
 * Класс сообщения для регистрации/авторизации пользователя
 * @author Егор Соловьев
 */
public class LoginMessage extends Message {
    /** логин*/
    private String login;
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
     * метод возвращает логин
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     * метод возвращает пароль
     * @return
     */
    public String getPass() {
        return pass;
    }

    /**
     * метод устанавливает логин
     * @param login - логин
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * метод устанавлиает пароль
     * @param pass - пароль
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
}
