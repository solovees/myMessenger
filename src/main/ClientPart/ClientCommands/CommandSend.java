package main.ClientPart.ClientCommands;

import main.ClientPart.ClientHandler;
import main.messages.ChatSendMessage;
import main.messages.Message;
import main.messages.MessagesType;

/**
 * Класс команда для обработки сообщения /chat_send
 * отправить сообщение
 * @author Егор Соловьев
 */
public class CommandSend implements ICommand {

    @Override
    public Message execute(String[] line) {
        ChatSendMessage message = new ChatSendMessage(MessagesType.CHAT_SEND);
        message.setLogin(ClientHandler.getUser().getLogin());
        message.setChatId(Long.valueOf(line[1]));
        StringBuffer str = new StringBuffer();
        for(int i = 2; i < line.length; i++){
            str.append(line[i]);
            str.append(" ");
        }
        message.setContent(str.toString());
        return message;
    }
}