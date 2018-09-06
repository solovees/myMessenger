package main.ClientPart.CommandsFromServer;

import main.messages.Message;
import main.messages.ServerStringMessage;

/**
 * Класс для обработки сообщения сервера
 */
public class CommandString implements ICommandServer {
    @Override
    public void execute(Message msg) {
        msg = (ServerStringMessage) msg;
        System.out.print("\n Ответ сервера: " + ((ServerStringMessage) msg).getText());
    }
}
