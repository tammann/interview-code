package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class is thread safe.
 *
 * Inspired by: https://github.com/yegor256/quiz/blob/master/Parser.java
 */
public class ParserImpl implements Parser {
    private File file;
    public synchronized void setFile(File f) {
        file = f;
    }
    public synchronized File getFile() {
        return file;
    }
    public String getContent() throws IOException {
        FileInputStream i = new FileInputStream(file);
        String output = "";
        int data;
        while ((data = i.read()) > 0) {
            output += (char) data;
        }
        return output;
    }
    public String getContentWithoutUnicode() throws IOException {
        FileInputStream i = new FileInputStream(file);
        String output = "";
        int data;
        while ((data = i.read()) > 0) {
            if (data < 0x80) {
                output += (char) data;
            }
            if (data == 1) {
                throw new RuntimeException("this should never happen with ascii text");
            }
        }
        return output;
    }

    @Override
    public String getMoreContent() {
        return null;
    }

    @Override
    public String isContentCorrect() {
        return null;
    }

    @Override
    public String setContent() {
        return null;
    }

    @Override
    public Object assertConsistency() {
        return null;
    }

    public void saveContent(String content) throws Throwable {
        FileOutputStream o = new FileOutputStream(file);
        try {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
