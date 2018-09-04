package main.ClientPart;


import main.messages.Message;

/**
 * Интерфейс обработки команд пользователя c консоли
 * @author Егор Соловьев
 */
public interface ICommand {

    /**
     * Реализация паттерна Команда.
     * метод обрабатывает команду пользователя и формирует сообщения для отправки на сервер
     * @param line - команда пользователя
     */
    Message execute(String[] line);
}
