package chat;

import myexceptions.MessageMyException;
import myexceptions.ParticipantMyException;
import user.User;

import java.util.LinkedList;

/**
 * Класс Чат, хранит сообщение и участников чата
 * @author Егор Соловье
 * @version 1.0
 */
public class Chat {

    /**id чата */
    private Long id;
    /**список id сообщений */
    private LinkedList<Long> messages;
    /**список id участников */
    private LinkedList<Long> participanties;
    /**админ чата*/
    private User admin = null;

    /**
     * Конструктор
     * @param id - id чата
     * @param admin - User админ чата
     */
    public Chat(Long id, User admin){
        this.id = id;
        this.admin = admin;
    }

    /**
     * метод добавляет id сообщения в чат
     * @param id - id сообщения
     * @throws MessageMyException
     */
    public void addMessages(Long id) throws MessageMyException {
        if (!checkMessages(id))
            throw new MessageMyException("Сообщение с таким id уже существует в чате");
        messages.add(id);
    }

    /**
     * метод добавляет участника в чат
     * @param p - id участника
     * @throws ParticipantMyException
     */
    public  void addParticipant(Long p) throws ParticipantMyException {
        if(!checkParticipants(p))
            throw new ParticipantMyException("Участник с таким id уже существует в чате");
        participanties.add(p);
    }

    /**
     * метод возвращает все id сообщения в чате
     * @return
     */
    public LinkedList<Long> getMessages() {
        return messages;
    }

    /**
     * метод возвращает всех участников чата
     * @return
     */
    public LinkedList<Long> getParticipanties() {
        return participanties;
    }

    /**
     * метод возвращает id чата
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * метод проверяет, что участника с таким именем нет в чате
     * @param p - id участника
     * @return
     */
    private boolean checkParticipants(Long p){
        for(Long participant: participanties){
            if(p.equals(participant))
                return false;
        }
        return true;
    }

    /**
     * метод возвращает админа чата
     * @return
     */
    public User getAdmin() {
        return admin;
    }



    /**
     * метод проверяет, что сообщения с таким id еще нет в чате
     * @param m - id сообщения
     * @return
     */
    private boolean checkMessages(Long m){
        for(Long messege: messages){
            if(m.equals(messege))
                return false;
        }
        return true;
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
        if(other.id.equals(id))
            return false;
        return true;
    }
}
