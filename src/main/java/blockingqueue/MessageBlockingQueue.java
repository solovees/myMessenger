package main.java.blockingqueue;

import main.java.messages.Message;
import main.java.serverpart.net.Session;

import java.util.Stack;

/**
 * Класс блокирующей очереди
 */
public class MessageBlockingQueue {

    /** стек для хранения SessionMessage*/
    private Stack<SessionMessageStore> stack = new Stack<>();
    /** метка true/false */
    private boolean setValue = false;

    /**
     * метод устанавливает сообщение и сессию и добаляет в стек SessionMessage
     * @param message - сообщение
     * @param session - сессия
     */
    public synchronized void set(Message message, Session session){
        while (setValue) try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stack.push(new SessionMessageStore(session,message));
        setValue = true;
        notifyAll();
    }

    /**
     * метод берет объект из блокирующей очереди и возвращает его
     * @return
     */
    public synchronized SessionMessageStore get(){

        while (!setValue) try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!stack.isEmpty()){
            SessionMessageStore sm = stack.pop();
            return sm;
        }
        setValue = false;
        notifyAll();
        return null;
    }


}
