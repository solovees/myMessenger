package main.java.ClientPart.ClientCommands;

import main.java.ClientPart.ClientHandler;
import main.java.messages.ChatFindMessage;
import main.java.messages.Message;
import main.java.messages.MessagesType;

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
