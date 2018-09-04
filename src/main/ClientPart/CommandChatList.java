package main.ClientPart;

import main.messages.ChatListMessage;
import main.messages.Message;
import main.messages.MessagesType;

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
