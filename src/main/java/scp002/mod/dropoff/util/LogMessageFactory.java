package scp002.mod.dropoff.util;

import org.apache.logging.log4j.message.*;
import scp002.mod.dropoff.DropOff;

public class LogMessageFactory implements MessageFactory {

    public static final LogMessageFactory INSTANCE = new LogMessageFactory();

    private final String prefix = "[" + DropOff.MOD_ID + "]: ";

    @Override
    public Message newMessage(Object message) {
        return new ObjectMessage(prefix + message);
    }

    @Override
    public Message newMessage(String message) {
        return new SimpleMessage(prefix + message);
    }

    @Override
    public Message newMessage(String message, Object... params) {
        return new ParameterizedMessage(prefix + message, params);
    }

}
