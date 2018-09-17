package main.java.ClientPart.ClientCommands;

import main.java.messages.LoginMessage;
import main.java.messages.Message;
import main.java.messages.MessagesType;
/**
 * Класс команда для обработки сообщения /login
 * регистрация нового пользователя
 * @author Егор Соловьев
 */
public class CommandLogin implements ICommand {
    @Override
    public Message execute(String[] line) {
        LoginMessage message = new LoginMessage(MessagesType.USER_LOGIN);
        message.setLogin(line[1]);
        message.setPass(line[2]);
        return message;
    }
}
