package main.serverpart.Store;

import main.myexceptions.AuthorizationException;
import main.user.User;
import org.junit.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class UserStoreClassTest {

    @Test
    public void addUser() {
        try {
            /**Соединение с базой данных */
            Connection con = SqlConnection.getConnection();
            /**Хранит и выполняет sql запросы*/
            Statement stmt = null;
            /** Получает результаты sql запросов*/
            ResultSet res = null;
            User user = new User("mantr6", "refe45dfdTRT");
            UserStoreClass usc = new UserStoreClass(con);
            User user_2 = usc.addUser(user);
            String actualLogin = user_2.getLogin();
            String expected =  "mantr6";
            Assert.assertEquals(expected, actualLogin);
        } catch (AuthorizationException e) {
            e.printStackTrace();
        }
    }
}