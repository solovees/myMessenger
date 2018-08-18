package main.protocol;

import main.messages.Message;

import java.io.*;
import java.net.ProtocolException;

/**
 * Класс бинарного протокола, сериализация сообщений
 * @author Егор Соловьев
 */
public class BinaryProtocol implements Protocol{

    @Override
    public byte[] encode(Message message) throws ProtocolException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
              ObjectOutput oos = new ObjectOutputStream(bos)) {

            oos.writeObject(message);
            byte[] objData = bos.toByteArray();
            return objData;

        } catch (IOException e) {
            throw new ProtocolException("Failed to encode message");
        }
    }

    @Override
    public Message decode(byte[] objData) throws ProtocolException {
        try (ByteArrayInputStream bys = new ByteArrayInputStream(objData);
            ObjectInput ois = new ObjectInputStream(bys)){
            return  (Message) ois.readObject();
        } catch (IOException e) {
            throw new ProtocolException("Failed to decode message");
        } catch (ClassNotFoundException e) {
            throw new ProtocolException("Failed to decode message");
        }
    }
}
