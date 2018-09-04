package main.ServerPart.net.commands;

import main.ServerPart.Store.MessageStoreClass;
import main.ServerPart.Store.SqlConnection;
import main.ServerPart.Store.UserStoreClass;
import main.ServerPart.net.Session;
import main.messages.Message;
import main.messages.MessagesType;
import main.myexceptions.CommandException;
import main.myexceptions.IllegalAcceptToUser;


import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class AllCommands {
    /** Словарь команд*/
    private Map<MessagesType, Command> map = new HashMap<>();

    /** Классы для взаимодействия с базами данных */
    private UserStoreClass usc;
    private MessageStoreClass msc;
    private Connection con;

    /**
     * конструктор
     */
    public AllCommands(){
        con = SqlConnection.getConnection();
        //usc = new UserStoreClass(con);
       // msc = new MessageStoreClass(con);
    }
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
    public void makeCommand(Session session, Message message) throws CommandException, IllegalAcceptToUser {
       Command command = map.get(message.getMessagesType());
       command.execute(session, message ,new UserStoreClass(con), new MessageStoreClass(con));
    }
}
