package chat;

import java.util.ArrayList;

/**
 * Класс Чат, хранит сообщение и участников чата
 * @author Егор Соловье
 * @version 1.0
 */
public class Chat {

    /**id чата */
    private long id;
    /**список сообщений */
    private ArrayList<Long> messages;
    /**список участников */
    private ArrayList<Long> participanties;

    /**
     * метод добавляет id сообщения в чат
     * @param id - id сообщения
     */
    public void addMessages(Long id){
        messages.add(id);
    }

    /**
     * метод добавляет участника в чат
     * @param p - id участника
     */
    public  void addParticipant(Long p){
        participanties.add(p);
    }

    /**
     * метод возвращает все id сообщения в чате
     * @return
     */
    public ArrayList<Long> getMessages() {
        return messages;
    }

    /**
     * метод возвращает всех участников чата
     * @return
     */
    public ArrayList<Long> getParticipanties() {
        return participanties;
    }

    /**
     * метод возвращает id чата
     * @return
     */
    public long getId() {
        return id;
    }

    
    @Override
    public String toString() {
        return getClass().getName() + "@chat_id: " + id;
    }

    @Override
    public int hashCode() {
        return (int) (id*3 + messages.hashCode() + participanties.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(getClass() != obj.getClass())
            return  false;
        if(obj == null)
            return false;
        Chat other = (Chat) obj;
        if(other.id != id )
            return false;
        return true;
    }
}
