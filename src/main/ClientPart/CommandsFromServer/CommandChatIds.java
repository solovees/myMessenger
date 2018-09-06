package main.ClientPart.CommandsFromServer;

import main.messages.Message;
import main.messages.ServerChatsIdMessege;

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
