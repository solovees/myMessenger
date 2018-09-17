package main.java.ClientPart.CommandsFromServer;

import main.java.messages.Message;
import main.java.messages.ServerChatsIdMessege;

import java.util.LinkedList;

/**
 * Класс для обработки сообщения от сервера
 */
public class CommandChatIds implements ICommandServer {
    @Override
    public void execute(Message msg) {
        msg = (ServerChatsIdMessege)msg;
        LinkedList<Long> ids = ((ServerChatsIdMessege) msg).getChatsIds();
        System.out.print("id чатов:");
        for (Long i: ids)
            System.out.print(i);
    }
}
