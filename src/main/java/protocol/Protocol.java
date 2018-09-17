package main.java.protocol;

import main.java.messages.Message;

import java.net.ProtocolException;

/**
 * interface для сериализации объектов
 * @author  Егор Соловьев
 */
public interface Protocol {
    /**
     * метод принимает объект Message и представляет его в наборе byte
     * @param message - сообщение
     * @return
     * @throws ProtocolException
     */
    public byte[] encode(Message message) throws ProtocolException;

    /**
     * метод принимает набор байт и возвращает объект Message
     * @param objData - набор byte
     * @return
     * @throws ProtocolException
     */
    public Message decode(byte[] objData) throws ProtocolException;
}
