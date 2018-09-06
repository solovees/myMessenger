package main.ClientPart.ClientCommands;

import main.ClientPart.ClientHandler;
import main.messages.ChatFindMessage;
import main.messages.Message;
import main.messages.MessagesType;

/**
 * Класс команда для обработки сообщения /chat_find
 * поиск в чате
 * @author Егор Соловьев
 */
public class CommandChatFind implements ICommand {

    @Override
    public Message execute(String[] line) {
        ChatFindMessage message = new ChatFindMessage(MessagesType.CHAT_FIND);
        message.setLogin(ClientHandler.getUser().getLogin());
        message.setChatId(Long.valueOf(line[1]).longValue());
        message.setRegex(line[2]);
        return message;
    }
}
