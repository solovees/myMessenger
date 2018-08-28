package main.messages;

import java.util.LinkedList;

/**
 * @author Егор Соловьев
 * Класс сообщедние для передачи списка чатов пользователю
 */
public class ServerChatsIdMessege extends Message {


    /** список id чатов*/
    private LinkedList<Long> chatsIds;

    /**
     * конструктор
     * @param type - тип сообщения
     */
    public ServerChatsIdMessege(MessagesType type) {
        super(type);
    }

    /**
     * метод возвращает список id чатов
     * @return
     */
    public LinkedList<Long> getChatsIds() {
        return chatsIds;
    }

    /**
     * метод устанавливает список id чатов
     * @param chatsIds - список id чатов
     */
    public void setChatsIds(LinkedList<Long> chatsIds) {
        this.chatsIds = chatsIds;
    }
}
