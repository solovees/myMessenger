package main.ServerPart.Store;

import main.chat.Chat;
import main.messages.ChatSendMessage;
import main.messages.Message;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public List<Long> getChatsByUserId(Long userId) {
        String sql = "SELECT chat_id FROM chats WHERE user_id = " + userId.toString();
        List<Long> result = new ArrayList<Long>();
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
        String sql = "SELECT * FROM chat WHERE chat_id = " + chatId.toString();
        Chat chat ;
        try {
            stmt = con.createStatement();
            res = stmt.executeQuery(sql);
            chat = new Chat(chatId, res.getLong("admin"));
            return chat;
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
     * метод возвращает список сообщений из чата
     * @param chatId - id чата
     * @return
     */
    @Override
    public List<Long> getMessagesFromChat(Long chatId) {
        String sql = "SELECT id FROM messenger.messages WHERE chat_id = " + chatId.toString();
        List<Long> result = new ArrayList<Long>();
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
    public Message getMessageById(Long messageId) {
        return null;
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
        String sql = "INSERT INTO messages(content, user_id, chat_id) VALUES ('" + ((ChatSendMessage) message).getContent() +
                "', " + chatId.toString() + ", " + userId.toString() + ")";
        try {
            stmt = con.createStatement();
            int n = stmt.executeUpdate(sql);
            res = stmt.executeQuery(sql);
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
        }
    }

    /**
     * добавляем чат для пользователя
     * @param userId - id пользователя
     */
    @Override
    public void addChat(Long userId) {
        String sql = "UPDATE INTO messenger.chats (admin) VALUES(" + userId.toString() + ")";
        try {
            stmt = con.createStatement();
            int n = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
