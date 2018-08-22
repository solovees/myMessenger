package main.ServerPart.net.commands;

import main.ServerPart.net.Session;
import main.messages.Message;
import main.myexceptions.CommandException;


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
     * @throws CommandException - все исключения перебрасываются как CommandException
     */
    void execute(Session session, Message message) throws CommandException;
}
