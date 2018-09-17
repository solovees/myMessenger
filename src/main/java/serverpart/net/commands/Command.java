package main.java.serverpart.net.commands;

import main.java.serverpart.Store.MessageStoreClass;
import main.java.serverpart.Store.UserStoreClass;
import main.java.serverpart.net.Session;
import main.java.messages.Message;
import main.java.myexceptions.CommandException;


/**
 * Интерфейс обработки команд пользователя
 * @author Егор Соловьев
 */
public interface Command {

    /**
     * Реализация паттерна Команда. Метод execute() вызывает соответствующую реализацию,
     * для запуска команды нужна сессия, чтобы можно было сгенерить ответ клиенту и провести валидацию
     * сессии.
     * @param session - текущая сессия
     * @param message - сообщение для обработки
     * @param usc -  класс взаимодействия с бд для пользователя
     * @param msc -  класс взаимодействия с бд для сообщений
     * @throws CommandException - все исключения перебрасываются как CommandException
     */
    void execute(Session session, Message message, UserStoreClass usc, MessageStoreClass msc) throws CommandException;
}
