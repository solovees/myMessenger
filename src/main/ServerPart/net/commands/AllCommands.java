package main.ServerPart.net.commands;

import main.ServerPart.net.Session;
import main.messages.Message;
import main.messages.MessagesType;
import main.myexceptions.CommandException;


import java.util.HashMap;
import java.util.Map;

public class AllCommands {
    /** очередь сообщений */
    private Map<MessagesType, Command> map = new HashMap<>();

    /**
     * метод добавляет сообщение и соответствующую ей команду
     * @param type - тип сообщение
     * @param command - команда
     */
    public void addNewCommand(MessagesType type, Command command){
        map.put(type, command);
    }

    /**
     * метол выполняет команду в зависимости от сообщения
     * @param session - сессия
     * @param message - сообщение
     * @throws CommandException
     */
    public void makeCommand(Session session, Message message) throws CommandException {
       Command command = map.get(message.getMessagesType());
       command.execute(session, message);
    }
}
