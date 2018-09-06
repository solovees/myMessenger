package main.ClientPart.ClientCommands;

import main.ClientPart.ClientHandler;
import main.messages.ChatMessage;
import main.messages.Message;
import main.messages.MessagesType;

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
