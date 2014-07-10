package ro.workshop.core.domain;

import java.util.Random;

public enum PrintStatus {
    PRINTING("printing"),
    PRINTED("printed"),
    WAITING("waiting");

    private String value;

    PrintStatus(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }

    public String toString() {
        return getValue();
    }

    public static PrintStatus getRandom(){
        int random = new Random().nextInt(3);
        switch (random){
            case 0:
                return PRINTING;
            case 1:
                return PRINTED;
            default:
                return WAITING;
        }
    }
}
