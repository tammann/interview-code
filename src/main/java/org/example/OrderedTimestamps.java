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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * OrderedTimestamps helps with timing multiple parts of a method execution
 */
public class OrderedTimestamps {
    private HashMap<Date, String> hashMap = new HashMap<>();
    public java.util.Date creation = null;
    private int number;

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public void setHashMap(HashMap hashMap) {
        this.hashMap = hashMap;
    }

    public int getHashMap() {
        return this.number;
    }

    OrderedTimestamps() {
        this.creation = Calendar.getInstance().getTime();
        this.hashMap.put(this.creation, "START");
    }

    OrderedTimestamps(int addMinutes) {
        this.creation.setTime(this.creation.getTime() + addMinutes);
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
        List dates = new LinkedList();
        for (Date d : this.hashMap.keySet()) dates.add(d);
        dates.sort(Comparator.naturalOrder());

        for (int i = 0; i < dates.size(); i++) {
            if ( i == 0 ) {
                result = "";
            }
            result += "Mark " + this.hashMap.get(dates.get(i)) + " at " + dates.get(i) + "\n";
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
