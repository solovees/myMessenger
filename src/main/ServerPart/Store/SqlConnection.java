package main.ServerPart.Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;

/**
 * класс для создания подключения к базе данных MySql
 * @author Егор Соловьев
 */
public class SqlConnection {

    /** url подключения к бд*/
    private static final String URL = "jdbc:mysql://127.0.0.1:3310/messenger?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    /** имя пользователя*/
    private static final String USERNAME = "root";
    /** пароль пользователя*/
    private static final String PASSWORD = "root";
    /** объект подключения к бд */
    private static Connection con = null;

    /**
     * метод регистрирует jdbc драйвер и возвращает подклчение к бд
     * @return
     */
    public static Connection getConnection(){

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if(con == null){
                con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
            return con;
        } catch (SQLException e) {
            System.err.print("Не удалось загрузить класс драйвера");
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
