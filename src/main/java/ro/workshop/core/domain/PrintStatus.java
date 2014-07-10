package ro.workshop.core.domain;

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
}
