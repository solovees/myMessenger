package user;

import myexceptions.AuthorizationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс предназначен для создания объектов, с помощью которых можно хранить данные о пользователе
 * @author Егор Соловьев
 * @version 1.0
 */
public class User {
    /** id пользователя*/
    private Long id;
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
     * @param  id - id пользователя
     * @throws  AuthorizationException
     */
    public  User(Long id, String login, String password) throws AuthorizationException {
        if(!checkLogin(login) || !(checkPassword(password)))
            throw new AuthorizationException("Ошибка при авторизации");
        this.login = login;
        this.password = password;
        this.id = id;
        name = null;
    }

    /**
     * метод возвращает id
     * @return
     */
    public Long getId() {
        return id;
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
