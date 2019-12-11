package org.example;

import java.io.IOException;

public interface Parser {
    String getContentWithoutUnicode() throws IOException;
    String getMoreContent();
    String isContentCorrect();
    String setContent();
    Object assertConsistency(); // more checks
}
