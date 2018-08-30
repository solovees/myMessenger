package main.ClientPart;


/**
 * Интерфейс обработки команд пользователя c консоли
 * @author Егор Соловьев
 */
public interface ICommand {

    /**
     * Реализация паттерна Команда.
     */
    void execute(String[] line);
}
