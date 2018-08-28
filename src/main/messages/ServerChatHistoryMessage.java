package main.messages;

import main.chat.Chat;

/**
 * Класс сообщение для передачи пользователю чата со стороны сервера
 * @author Егор Соловьев
 */
public class ServerChatHistoryMessage extends Message {

    /** Чат*/
    private Chat chat;

    /**
     * конструктор
     * @param type - тип сообщения
     */
    public ServerChatHistoryMessage(MessagesType type) {
        super(type);
    }

    /**
     * метод возврашщает чат
     * @return
     */
    public Chat getChat() {
        return chat;
    }

    /**
     * метод устанавливает чат
     * @param chat - чат
     */
    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
