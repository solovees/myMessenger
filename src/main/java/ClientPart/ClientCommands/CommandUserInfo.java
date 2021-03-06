package main.java.ClientPart.ClientCommands;

import main.java.messages.InfoMessage;
import main.java.messages.Message;
import main.java.messages.MessagesType;
/**
 * Класс команда для обработки сообщения /user_info и формирования сообщения
 * @author Егор Соловьев
 */
public class CommandUserInfo implements ICommand {

    @Override
    public Message execute(String[] line) {
        InfoMessage message = new InfoMessage(MessagesType.USER_INFO);
        message.setId(Long.valueOf(line[1]).longValue());
        return message;
    }
}
