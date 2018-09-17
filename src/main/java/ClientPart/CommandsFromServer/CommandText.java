package main.java.ClientPart.CommandsFromServer;

import main.java.messages.Message;
import main.java.messages.ServerTextMessage;

import java.util.LinkedList;

/**
 * Класс для обработки сообщения сервера
 * @author Егор Соловьев
 */
public class CommandText implements ICommandServer {
    @Override
    public void execute(Message msg) {
        msg = (ServerTextMessage)msg;
        LinkedList<String> text = ((ServerTextMessage) msg).getText();
        for(String line: text){
            System.out.println(line);
        }
    }
}
