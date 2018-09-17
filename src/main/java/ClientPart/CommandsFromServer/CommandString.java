package main.java.ClientPart.CommandsFromServer;

import main.java.messages.Message;
import main.java.messages.ServerStringMessage;

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
