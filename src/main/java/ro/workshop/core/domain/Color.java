package ro.workshop.core.domain;

import java.util.Random;

public enum Color {
    BLUE("blue"),
    RED("red"),
    ORANGE("orange"),
    GREEN("green"),
    YELLOW("yellow"),
    PURPLE("purple");

    private String value;

    Color(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }

    public static Color getRandom(){
        int random = new Random().nextInt(6);
        switch (random){
            case 0:
                return BLUE;
            case 1:
                return RED;
            case 2:
                return ORANGE;
            case 3:
                return GREEN;
            case 4:
                return YELLOW;
            default:
                return PURPLE;
        }
    }
}
