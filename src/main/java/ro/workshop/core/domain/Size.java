package ro.workshop.core.domain;

import java.util.Random;

public enum Size {
    SMALL("S"),
    MEDIUM("M"),
    LARGE("L"),
    EXTRA_LARGE("XL"),
    EXTRA_EXTRA_LARGE("XXL");


    private String value;

    Size(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }

    public static Size getRandom(){
        int random = new Random().nextInt(4);
        switch (random){
            case 0:
                return SMALL;
            case 1:
                return MEDIUM;
            case 2:
                return LARGE;
            case 3:
                return EXTRA_LARGE;
            default:
                return EXTRA_EXTRA_LARGE;
        }
    }
}
