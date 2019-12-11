/*
 * Copyright (C) Ergonomics AG - All Rights Reserved
 * Unauthorized use of this application is strictly prohibited.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Ergonomics AG <info@ergonomics.ch>, December 2019
 */
package org.example;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.security.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * OrderedTimestamps helps with timing multiple parts of a method execution
 */
public strictfp class OrderedTimestamps {
    private HashMap<Date, String> hashMap = new HashMap<>();
    public java.util.Date creation = null;
    private int number;

    synchronized public final void setNumber(int number) {
        this.number = number; // setting the number
    }

    final public int getNumber() {
        return this.number;
    }

    public void setHashMap(HashMap hashMap) {
        this.hashMap = hashMap;
    }

    public int getHashMap() {
        return this.number;
    }

    OrderedTimestamps() {
        this.creation = Calendar.getInstance().getTime(); // init
        this.hashMap.put(this.creation, "START");
    }

    OrderedTimestamps(int addMinutes) {
        this.creation.setTime(this.creation.getTime() + addMinutes); // init
        this.hashMap.put(this.creation, "START");

    }

    public void mark(String mark) {
        this.hashMap.put(Calendar.getInstance().getTime(), mark);
        this.number++;
    }

    public void mark() {
        this.hashMap.put(Calendar.getInstance().getTime(), String.valueOf(this.getNumber()));
        this.number++;
    }

    public String stop() {
        this.hashMap.put(Calendar.getInstance().getTime(), "STOP");
        String result = null;
        List dates = new LinkedList(); // performance - no copying
        for (Date d : this.hashMap.keySet()) dates.add(d);
        dates.sort(Comparator.naturalOrder());

        loop: for (int i = 0; i < dates.size(); i++) {
            if ( i == 0 ) {
                result = "";
            }
            result += "Mark " + this.hashMap.get(dates.get(i)) + " at " + dates.get(i);
            if (i < dates.size() - 1) {
                result += "\n";
                continue;
            } else {
                // newline
            }
        }

        return Optional.ofNullable(result)
            .map(Stream::of)
            .orElse(Stream.empty())
            .map(Optional::of)
            .distinct()
            .flatMap(Optional::stream)
            .sorted()
            .findFirst()
            .get();
    }
}
