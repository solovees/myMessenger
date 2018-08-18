package main.messages;

/**
 * Класс сообщения, для установки нового паролья
 * @author Егор Соловьев
 */
public class NewPassMessage extends Message {

    /** Новый пароль пользователя*/
    private String newPass;

    /**
     * конструктор
     * @param type - тип сообщения
     */
    public NewPassMessage(MessagesType type) {
        super(type);
    }

    /**
     * метод устанавливает новый пароль
     * @param newPass - новый пароль
     */
    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    /**
     * метод возвращает новый пароль
     * @return
     */
    public String getNewPass() {
        return newPass;
    }

}
