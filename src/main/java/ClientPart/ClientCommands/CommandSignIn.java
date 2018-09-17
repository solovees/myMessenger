package main.java.ClientPart.ClientCommands;

import main.java.messages.LoginMessage;
import main.java.messages.Message;
import main.java.messages.MessagesType;
/**
 * Класс команда для обработки сообщения /sign_in
 * вход пользователя в систему
 * @author Егор Соловьев
 */
public class CommandSignIn implements ICommand {
    @Override
    public Message execute(String[] line) {
        LoginMessage message = new LoginMessage(MessagesType.USER_SIGN_IN);
        message.setLogin(line[1]);
        message.setPass(line[2]);
        return message;
    }
}
