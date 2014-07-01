package ro.workshop.core.domain;

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

    public String toString() {
        return getValue();
    }
}
