package main.ClientPart.ClientCommands;

import main.ClientPart.ClientHandler;
import main.messages.CreateChatMessage;
import main.messages.Message;
import main.messages.MessagesType;

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
