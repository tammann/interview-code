package org.example;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;





public class LoggingBetterParsr extends ParserImpl {
    /**
     * This is better because it does not throw exceptions.
     * @return String result
     */
    public String getContentWithoutUnicode() {
        try {
            return super.getContentWithoutUnicode();
        } catch (IOException e) {
            e.printStackTrace();
            return "no result";
        }
    }

    static protected void logThis(Class clazz, byte[] message) {
        try {
            Constructor ctor = clazz.getConstructor(byte[].class, Charset.class);
            Object tmp = clazz.cast(ctor.newInstance(message, StandardCharsets.ISO_8859_1));
            System.err.println(tmp.toString());

        } catch (NoSuchMethodException e) {
            logThis(Exception.class, e.getMessage().getBytes());
        } catch (IllegalAccessException e) {
            logThis(Exception.class, e.getMessage().getBytes());
        } catch (InstantiationException e) {
            logThis(Exception.class, e.getMessage().getBytes());
        } catch (InvocationTargetException e) {
            logThis(Exception.class, e.getMessage().getBytes());
        }
    }

    public static boolean isInfo() {
        return true;
    }

    static public boolean isDebug() {
        return true;
    }

    static final public void info(byte[] message) {
        logThis(String.class, message);
    }

    static final public void debug(byte[] message) {
        logThis(String.class, message);
    }
}
