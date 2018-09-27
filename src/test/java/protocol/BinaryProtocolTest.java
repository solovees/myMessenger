package test.java.protocol;

import main.java.messages.Message;
import main.java.messages.MessagesType;
import main.java.protocol.BinaryProtocol;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.net.ProtocolException;

import static org.junit.Assert.*;

public class BinaryProtocolTest {

    /** Сообщение*/
    private Message message;
    /** Протокол бинарный*/
    private BinaryProtocol protocol = new BinaryProtocol();

    @Before
    public void createMessage(){
        message = new Message(MessagesType.USER_LOGIN);
        message.setLogin("login");
    }

    @Test
    public void encodeTest() {
        byte[] expected = new byte[1024*64];
        byte[] actual = new byte[1024*64];
        try {
            actual = protocol.encode(message);
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput oos = new ObjectOutputStream(bos)) {
             oos.writeObject(message);
             expected = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertArrayEquals(expected, actual);
    }

    @Ignore
    @Test
    public void decodeTest() {
        byte[] expected = new byte[1024*64];
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput oos = new ObjectOutputStream(bos)) {
            oos.writeObject(message);
            expected = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}