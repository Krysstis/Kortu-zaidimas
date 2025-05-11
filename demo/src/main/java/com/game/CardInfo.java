package com.game;

public class CardInfo {
    private String type;
    private String name;
    private int value;

    public CardInfo(String type, String name, int value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name + " " + value + " " + type;
    }
}