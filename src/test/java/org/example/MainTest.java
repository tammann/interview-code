package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MainTest {
    @Test
    void test1() {
        Main.main(new String[]{"Hello"});
        assertTrue(true); // no exceptions
    }
}