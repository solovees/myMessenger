package main.blockingqueue;

import main.messages.Message;
import main.myexceptions.CommandException;
import main.myexceptions.IllegalAcceptToUser;
import main.serverpart.net.Session;
import main.serverpart.net.commands.AllCommands;

/**
 * Класс который забирает из блокирущей очереди SessionMessageStore и выполняет дейстия с ними
 * @author Егор Соловьев
 */
public class ServerConsumer extends Thread {

    /** Сессия*/
    private Session session;
    /** Блокирующая очередь*/
    private MessageBlockingQueue messsageBlockingQueue;
    /** Все команды*/
    private AllCommands commands;
    /** Сообщение*/
    private Message msg;

    /**
     * Конструктор
     * @param messsageBlockingQueue - блокирующая очередь
     * @param commands - список всех команд по типу сообщения
     */
    public ServerConsumer(MessageBlockingQueue messsageBlockingQueue, AllCommands commands){
        this.messsageBlockingQueue = messsageBlockingQueue;
        this.commands = commands;
    }
    @Override
    public void run() {
        while (true)
        try {
            SessionMessageStore sm = messsageBlockingQueue.get();
            if(sm != null){
                session = sm.getSession();
                msg = sm.getMessage();
                commands.makeCommand(session,msg);
            }
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (IllegalAcceptToUser illegalAcceptToUser) {
            illegalAcceptToUser.printStackTrace();
        } finally {
            session = null;
            msg = null;
        }
    }
}
