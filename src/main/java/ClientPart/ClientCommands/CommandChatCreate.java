package main.java.ClientPart.ClientCommands;

import main.java.ClientPart.ClientHandler;
import main.java.messages.CreateChatMessage;
import main.java.messages.Message;
import main.java.messages.MessagesType;

import java.util.ArrayList;

/**
 * Класс команда для обработки сообщения /chat_create
 * создать чаты
 * @author Егор Соловьев
 */
public class CommandChatCreate implements ICommand {

    @Override
    public Message execute(String[] line) {
        CreateChatMessage message = new CreateChatMessage(MessagesType.CHAT_CREATE);
        message.setLogin(ClientHandler.getUser().getLogin());
        message.setAdmin(ClientHandler.getUser().getId());
        ArrayList<Long> array = new ArrayList<>();
        for (int i = 1; i < line.length ; ++i ){
            array.add(Long.valueOf(line[i]).longValue());
        }
        message.setIds(array);
        return message;
    }
}
