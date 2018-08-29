package main.ServerPart.Store;

import main.user.User;

/**
 * Хранилище информации о пользователе
 * @author Егор Соловьев
 */
public interface UserStore {

    /**
     * Добавить пользователя в хранилище
     * Вернуть его же
     * @param user - пользователь
     * @return
     */
    User addUser(User user);

    /**
     * Обновить информацию о пользователе
     * @param user - пользователь
     * @return
     */
    User updateUser(User user);

    /**
     * Получить пользователя по логину/паролю
     * return null if user not found
     * @param login - логин
     * @param pass - пароль
     * @return
     */
    User getUser(String login, String pass);

    /**
     * Получить пользователя по id, например запрос информации/профиля
     * return null if user not found
     * @param id - id пользователя
     * @return
     */
    User getUserById(Long id);

    /**
     * Получить пользователя по login
     * return null if user not found
     * @param login - логин пользователя
     * @return
     */
    User getUserByLogin(String login);
}
