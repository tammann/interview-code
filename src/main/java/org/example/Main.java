/*
 * Copyright (C) Ergonomics AG - All Rights Reserved
 * Unauthorized use of this application is strictly prohibited.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Ergonomics AG <info@ergonomics.ch>, December 2019
 */
package org.example;

import java.io.File;
import java.io.IOException;

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
        System.err.println("Hi, welcome to the challenge....");
        ots.mark("after welcome");
        final ParserImpl first = new BetterParsr();
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
        System.err.println(String.format("Read %d characters.", data.length()));
        System.err.println(ots.stop());
    }
}
