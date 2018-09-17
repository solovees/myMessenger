package main.java.ClientPart.ClientCommands;

import main.java.ClientPart.ClientHandler;
import main.java.messages.ChatListMessage;
import main.java.messages.Message;
import main.java.messages.MessagesType;

/**
 * Класс команда для обработки сообщения /chat_list
 * получить список чатов
 * @author Егор Соловьев
 */
public class CommandChatList implements ICommand {
    @Override
    public Message execute(String[] line) {
        ChatListMessage message = new ChatListMessage(MessagesType.CHAT_LIST);
        message.setLogin(ClientHandler.getUser().getLogin());
        return message;
    }
}
