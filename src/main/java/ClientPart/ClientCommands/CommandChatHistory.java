package main.java.ClientPart.ClientCommands;

import main.java.ClientPart.ClientHandler;
import main.java.messages.ChatMessage;
import main.java.messages.Message;
import main.java.messages.MessagesType;

/**
 * Класс команда для обработки сообщения /chat_history
 * получить историю чата
 * @author Егор Соловьев
 */
public class CommandChatHistory implements ICommand {

    @Override
    public Message execute(String[] line) {
        ChatMessage message = new ChatMessage(MessagesType.CHAT_HISTORY);
        message.setLogin(ClientHandler.getUser().getLogin());
        message.setChatId(Long.valueOf(line[1]).longValue());
        return message;
    }
}
