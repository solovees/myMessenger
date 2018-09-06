package main.ClientPart.ClientCommands;

import main.ClientPart.ClientHandler;
import main.messages.Message;
import main.messages.MessagesType;
import main.messages.NameMessage;
/**
 * Класс команда для обработки сообщения /user добавить никнейм для текущего пользователя
 * @author Егор Соловьев
 */
public class CommandUser implements ICommand {

    @Override
    public Message execute(String[] line) {
        NameMessage message = new NameMessage(MessagesType.USER_NAME);
        message.setId(ClientHandler.getUser().getId());
        message.setLogin(ClientHandler.getUser().getLogin());
        message.setName(line[1]);
        return message;
    }
}
