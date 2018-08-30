package main;

import main.messages.Message;
import main.myexceptions.ProtocolException;

import java.io.IOException;

/**
 * Описывает поведение слушателя сокета
 * @author Егор Соловьев
 */
public interface ConnectionHandler {
    /**
     * Отправить сообщение.
     * Требуется обработать 2 типа ошибок
     * @throws ProtocolException - ошибка протокола (не получилось кодировать/декодировать)
     * @throws IOException - ошибка чтения/записи данных в сеть
     */
    void send(Message msg) throws ProtocolException, IOException;

    /**
     * Реакция на сообщение, пришедшее из сети
     */
    void onMessage(Message msg);

    /**
     * Закрываем соединение и освобождаем ресурсы
     */
    void close();

}
