package main.ServerPart.Store;

import main.myexceptions.AuthorizationException;
import main.user.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Хранилище информации о пользователе
 * @author Егор Соловьев
 */
public class UserStoreClass implements UserStore {

    /**Соединение с базой данных */
    private Connection con;
    /**Хранит и выполняет sql запросы*/
    private Statement stmt = null;
    /** Получает результаты sql запросов*/
    private ResultSet res = null;

    /**
     * конструктор
     * @param con - подключение к базе данных
     */
    public UserStoreClass(Connection con){
        this.con = con;
    }

    /**
     * Добавить пользователя в хранилище
     * Вернуть его же
     * @param user - пользователь
     * @return
     */
    @Override
    public User addUser(User user) {
        String sql = "INSERT INTO messenger.user (id, login, password, name) VALUES" +
                "(" + user.getId().toString() + "," + user.getLogin().toString() +
                " ," + user.getPassword().toString() + ", " + user.getName().toString();
        try {
            stmt = con.createStatement();
            int n = stmt.executeUpdate(sql);
            res = stmt.executeQuery(sql);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                res.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Обновить информацию о пользователе
     * @param user - пользователь
     * @return
     */
    @Override
    public User updateUser(User user) {
        String sql = "UPDATE messenger.user SET " +
                "id =" + user.getId().toString() +
                ", login =" + user.getLogin().toString() +
                ", password =" + user.getPassword().toString() +
                ", name = " + user.getName().toString();
        try {
            stmt = con.createStatement();
            int n = stmt.executeUpdate(sql);
            res = stmt.executeQuery(sql);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                res.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Получить пользователя по логину/паролю
     * return null if user not found
     * @param login - логин
     * @param pass - пароль
     * @return
     */
    @Override
    public User getUser(String login, String pass) {
        String sql = "SELECT * FROM messenger.user where login =" +
                login + "and password = " + pass;
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            if(res.next()) {
                User user = new User(res.getLong("id"), res.getString("login"), res.getString("password"));
                user.setName(res.getString("name"));
                return user;
            }
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                res.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Получить пользователя по id, например запрос информации/профиля
     * return null if user not found
     * @param id - id пользователя
     * @return
     */
    @Override
    public User getUserById(Long id) {
        String sql = "SELECT * FROM messenger.user where id = " + id.toString();
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            if(res.next()) {
                User user = new User(id, res.getString("login"), res.getString("password"));
                user.setName(res.getString("name"));
                return user;
            }
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                res.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
