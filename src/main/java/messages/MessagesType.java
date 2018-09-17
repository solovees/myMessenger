package main.java.messages;

/**
 * Enum для хранения типов команд
 * @author Егор Соловьев
 */
public enum MessagesType {
    USER_LOGIN,     // команда /login
    USER_INFO,      // команда /user_info
    USER_NAME,      // команда /user
    USER_PASS,      // команда /user_pass
    CHAT_LIST,      // команда /chat_list
    CHAT_CREATE,    // команда /chat_create
    CHAT_HISTORY,   // команда /chat_history
    CHAT_FIND,      // команда /chat_find
    CHAT_SEND,      // команда /chat_send
    SERVER_USER,    // сообщений от сервера вернуть User
    SERVER_STRING,  // сообщение от сервера текстовое
    SERVER_CHAT_IDS,// сообщение от сервера с списком чатов
    TEXT_MESS,      // сообщение из чата
    USER_SIGN_IN    // команда /sign_in

}
