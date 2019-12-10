package org.example;

import java.io.IOException;

public interface Parser {
    String getContentWithoutUnicode() throws IOException;
}
