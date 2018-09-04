package main.ClientPart;

import main.messages.Message;
import main.messages.MessagesType;
import main.messages.NewPassMessage;
/**
 * Класс команда для обработки сообщения /user_pass
 * сменить пароль
 * @author Егор Соловьев
 */
public class CommandPass implements ICommand {
    @Override
    public Message execute(String[] line) {
        NewPassMessage message = new NewPassMessage(MessagesType.USER_PASS);
        message.setLogin(ClientHandler.getUser().getLogin());
        message.setNewPass(line[1]);
        return message;
    }
}
