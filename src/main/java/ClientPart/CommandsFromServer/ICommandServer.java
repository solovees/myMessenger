package main.java.ClientPart.CommandsFromServer;

import main.java.messages.Message;

/**
 * Интерфейс для реализации паттерна команда, команды для обработки сообщений со стороны сервера
 * @author Егор Соловьев
 */
public interface ICommandServer {
    /**
     * метод получает сообщение от сервера и выполняет необходимые действия
     * @param msg
     */
    void execute(Message msg);
}
