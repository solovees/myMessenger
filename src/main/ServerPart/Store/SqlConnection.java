package main.ServerPart.Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * класс для создания подключения к базе данных MySql
 * @author Егор Соловьев
 */
public class SqlConnection {

    /** url подключения к бд*/
    private static final String URL = "jdbc:mysql://localhost:3306/messenger";
    /** имя пользователя*/
    private static final String USER_NAME = "root";
    /** пароль пользователя*/
    private static  String PASSWORD = "root";
    /** объект подключения к бд */
    private static Connection con = null;

    /**
     * метод регистрирует jdbc драйвер и возвращает подклчение к бд
     * @return
     */
    public static Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            if(con == null){
                con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            }
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if( con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
