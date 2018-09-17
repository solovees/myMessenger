package main.java.serverpart.net.commands;

import main.java.serverpart.Store.MessageStoreClass;
import main.java.serverpart.Store.SqlConnection;
import main.java.serverpart.Store.UserStoreClass;
import main.java.serverpart.net.Session;
import main.java.messages.Message;
import main.java.messages.MessagesType;
import main.java.myexceptions.CommandException;
import main.java.myexceptions.IllegalAcceptToUser;


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
       command.execute(session, message, new UserStoreClass(con), new MessageStoreClass(con));
    }
}
