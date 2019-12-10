package org.example;

import java.io.IOException;

public class BetterParsr extends ParserImpl {
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
}
