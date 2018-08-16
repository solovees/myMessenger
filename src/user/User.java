package user;

import chat.Chat;
import myexceptions.AuthorizationException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс предназначен для создания объектов, с помощью которых можно хранить данные о пользователе
 * @author Егор Соловьев
 * @version 1.0
 */
public class User {
    /** Список чатов пользователя*/
    private ArrayList<Chat> chats;
    /** Логин пользоваетля*/
    private String login;
    /** Пароль пользователя*/
    private String password;
    /** Никнэйм*/
    private String name;

    /**
     * Конструктор
     * @param login - логин
     * @param password - пароль
     * @throws  AuthorizationException
     */
    public  User(String login, String password) throws AuthorizationException {
        if(!checkLogin(login) || !(checkPassword(password)))
            throw new AuthorizationException("Ошибка при авторизации");
        this.login = login;
        this.password = password;
    }
    /**
     * метод возвращает чаты пользователя
     * @return
     */
    public ArrayList<Chat> getChats() {
        return chats;
    }

    /**
     * метод возвращает логин пользователя
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     * метод возвращает пароль пользователя
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * метод возвращает имя
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * метод устанавливает имя
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * метод устанавливает логин пользователя
     * @param login - логин
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * метод устанавливает пароль пользователя
     * @param password - пароль
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * метод добавляет чат
     * @param chat - чат
     */
    public void addChat(Chat chat){
        chats.add(chat);
    }

    /**
     * метод проверяет правильность логина
     * @param login - логин
     * @return
     */
    private boolean checkLogin(String login){
        Pattern pat;
        Matcher mat;
        pat = Pattern.compile("[a-zA-Z0-9]{5,}");
        mat = pat.matcher(login);

        return mat.matches();
    }

    /**
     * метод проверяет правильность пароля
     * @param password - пароль
     * @return
     */
    private boolean checkPassword(String password){
        Pattern pat;
        Matcher mat;
        pat = Pattern.compile("(?=.*[A-Z])(?=.*\\d)(?=.*[a-z])^[A-Za-z0-9]{10,}$");
        mat = pat.matcher(password);

        return true;
    }

    @Override
    public String toString() {
        return getClass().getName() + "@login: " + login + " name: " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if(other.login != null) {
            if (!other.login.equals(login))
                return false;
        } else {
            return false;
        }
        return true;

    }

    @Override
    public int hashCode() {
        return login.hashCode()*2 + password.hashCode()*11 + (name == null ? 1: name.hashCode()*3);
    }
}