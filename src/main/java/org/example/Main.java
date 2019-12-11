/*
 * Copyright (C) Ergonomics AG - All Rights Reserved
 * Unauthorized use of this application is strictly prohibited.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Ergonomics AG <info@ergonomics.ch>, December 2019
 */
package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * This is the entry point of the application.
 * @since 1.0
 */
public final class Main {
    /**
     * The main class should never be instantiated.
     */
    private Main() {
    }

    /**
     * Here we go...
     * @param args The command line arguments.
     */
    @SuppressWarnings("ProhibitPublicStaticMethods")
    public static void main(final String[] args) {
        final OrderedTimestamps ots = new OrderedTimestamps();
        if (LoggingBetterParsr.isDebug())
            LoggingBetterParsr.info("Hi, welcome to the challenge....".getBytes(StandardCharsets.UTF_8));
        ots.mark("after welcome");
        final ParserImpl first = new LoggingBetterParsr();
        first.setFile(new File("LICENSE.txt"));
        final Parser second = first;
        String data = null;
        try {
            ots.mark();
            data = second.getContentWithoutUnicode();
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
        ots.mark("before result");
        String string;
        if (LoggingBetterParsr.isDebug()) {
            string = String.format("Read %d characters.", data.length());
            LoggingBetterParsr.info(string.getBytes());
        }
        // logging:
        System.err.println(ots.stop());
    }
}
