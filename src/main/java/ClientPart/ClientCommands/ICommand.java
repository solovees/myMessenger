package main.java.ClientPart.ClientCommands;


import main.java.messages.Message;

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
