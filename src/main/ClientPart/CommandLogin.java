package main.ClientPart;

import main.messages.LoginMessage;
import main.messages.MessagesType;

public class CommandLogin implements ICommand {
    @Override
    public void execute(String[] line) {
        LoginMessage message = new LoginMessage(MessagesType.USER_LOGIN);
        message.setLogin(line[1]);
        message.setPass(line[2]);

    }
}
