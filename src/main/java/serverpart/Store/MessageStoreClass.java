package main.java.serverpart.Store;

import main.java.chat.Chat;
import main.java.messages.ChatSendMessage;
import main.java.messages.Message;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *  Хранилище информации о сообщениях
 *  @author Егор Соловьев
 */
public class MessageStoreClass implements MessageStore {

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
    public MessageStoreClass(Connection con){
        this.con = con;
    }

    /**
     * метод возвращает список id пользователей заданного чата
     * @param userId - id пользователя
     * @return
     */
    @Override
    public LinkedList<Long> getChatsByUserId(Long userId) {
        String sql = "SELECT chat_id FROM messenger.chats WHERE user_id = " + userId;
        LinkedList<Long> result = new LinkedList<Long>();
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            while (res.next()){
                result.add(res.getLong("chat_id"));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                stmt.close();
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * метод возвращает информацию о чате
     * @param chatId - id  чата
     * @return НЕГОТОВ
     */
    @Override
    public Chat getChatById(Long chatId) {
        String sql = "SELECT * FROM messenger.chat WHERE id = " + chatId.toString();
        Chat chat ;
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            res.next();
            chat = new Chat(chatId, res.getLong("admin"));
            return chat;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if(stmt != null)
                    stmt.close();
                if(res != null)
                    res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * метод возвращает список сообщений из чата
     * @param chatId - id чата
     * @return
     */
    @Override
    public LinkedList<Long> getMessagesFromChat(Long chatId) {
        String sql = "SELECT id FROM messenger.messages WHERE chat_id = " + chatId.toString();
        LinkedList<Long> result = new LinkedList<Long>();
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            while (res.next()){
                result.add(res.getLong("id"));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                stmt.close();
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * метод возвращает сообщение по id
     * @param messageId - id сообщения
     * @return
     */
    @Override
    public String getMessageById(Long messageId) {
        String sql = "SELECT " +
                "m.content as content, " +
                "u.login as login " +
                "FROM messenger.messages m " +
                "INNER JOIN messenger.user u on m.user_id = u.id " +
                "WHERE m.id =" + messageId ;
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            String resalt = null;
            if(res.next()){
                resalt = res.getString("login") + ": " + res.getString("content");
            }

            return resalt;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                stmt.close();
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Добавить сообщение в чат
     * @param chatId - id чата
     * @param message - сообщение
     * @param userId - сессия
     */
    @Override
    public void addMessage(Long chatId, Message message, Long userId) {
        message = (ChatSendMessage) message;
        String sql = "INSERT INTO messenger.messages(content, user_id, chat_id) VALUES ('" + ((ChatSendMessage) message).getContent() +
                "', " + userId + ", " + chatId + ")";
        try {
            stmt = con.createStatement();
            int n = stmt.executeUpdate(sql);
           // res = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                res.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Добавить пользователя к чату
     * @param userId - id пользовтеля
     * @param chatId - чат
     */
    @Override
    public void addUserToChat(Long userId, Long chatId) {
        String sql = "INSERT INTO messenger.chats (user_id, chat_id) VALUES ("
                + userId.toString() + " ," + chatId.toString() + " )";
        try {
            stmt = con.createStatement();
            int n = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * добавляем чат для пользователя
     * @param userId - id пользователя
     */
    @Override
    public Long addChat(Long userId) {
        String sql = "INSERT INTO messenger.chat (admin) VALUES(" + userId + ")";
        String sql2 = "SELECT * FROM messenger.chat WHERE id = LAST_INSERT_ID()";
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            res = stmt.executeQuery(sql2);
            if(res.next()){
                return res.getLong("id");
            } else return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                stmt.close();
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }


}
