package main.serverpart.Store;

import main.chat.Chat;
import main.messages.Message;

import java.util.LinkedList;

/**
 * Хранилище информации о сообщениях
 * @author Егор Соловьев
 */
public interface MessageStore {

    /**
     * получаем список id пользователей заданного чата
     * @param userId - id пользователя
     * @return
     */
    LinkedList<Long> getChatsByUserId(Long userId);

    /**
     * получить информацию о чате
     * @param chatId - id чата
     * @return
     */
    Chat getChatById(Long chatId);

    /**
     * Список сообщений из чата
     * @param chatId - id чата
     * @return
     */
    LinkedList<Long> getMessagesFromChat(Long chatId);

    /**
     *  Получить информацию о сообщении
     * @param messageId  - id сообщения
     * @return
     */
    String getMessageById(Long messageId);

    /**
     * Добавить сообщение в чат
     * @param chatId - id  чата
     * @param message - сообщение
     * @param userId - id  пользователя
     */
    void addMessage(Long chatId, Message message, Long userId);

    /**
     * Добавить пользователя к чату
     * @param userId - id пользовтеля
     * @param chatId - чат
     */
    void addUserToChat(Long userId, Long chatId);

    /**
     * добавляем чат для пользователя и возвращает его id
     * @param userId - id пользователя
     */
     Long addChat(Long userId);

}
