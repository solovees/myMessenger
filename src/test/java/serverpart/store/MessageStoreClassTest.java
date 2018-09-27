package test.java.serverpart.store;

import main.java.chat.Chat;
import main.java.serverpart.Store.MessageStoreClass;
import main.java.serverpart.Store.SqlConnection;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;

/**
 * Класс тестирует взаимодействие с базой данных
 * @author Егор Соловьев
 */
public class MessageStoreClassTest {

    /** Подключение к БД*/
    private static Connection con = SqlConnection.getConnection();
    /** Экземпляр теститруемого класса */
    private static MessageStoreClass msc = new MessageStoreClass(con);
    /** Хранит и выполняет sql запросы */
    private static Statement stmt;
    /** Обрабатывает результаты sql запросов */
    private static ResultSet res;

    @Test
    public void getChatsByUserIdTest() {
        String sql = "SELECT chat_id FROM messenger.chats WHERE user_id = 14" ;
        LinkedList<Long> expected = new LinkedList<Long>();
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            while (res.next()){
                expected.add(res.getLong("chat_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        LinkedList<Long> actual = msc.getChatsByUserId(14L);
        assertEquals(expected, actual);
    }

    @Test
    public void getChatByIdTest() {
        String sql = "SELECT * FROM messenger.chat WHERE id = 5";
        Chat expected = null;
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            res.next();
            expected = new Chat(5L, res.getLong("admin"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Chat actual = msc.getChatById(5L);
        assertEquals(expected, actual);
    }

    @Test
    public void getMessagesFromChatTest() {
        String sql = "SELECT id FROM messenger.messages WHERE chat_id = 5";
        LinkedList<Long> expected = new LinkedList<Long>();
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            while (res.next()){
                expected.add(res.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LinkedList<Long> actual = msc.getMessagesFromChat(5L);
        assertEquals(expected, actual);

    }

    @Test
    public void getMessageByIdTest() {
        String sql = "SELECT " +
                "m.content as content, " +
                "u.login as login " +
                "FROM messenger.messages m INNER JOIN messenger.user u on m.user_id = u.id WHERE m.id = 8 ";
        String expected = null;
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            if(res.next()){
                expected = res.getString("login") + ": " + res.getString("content");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String actual = msc.getMessageById(8L);
        assertEquals(expected ,actual);
    }
}