package org.example;

public enum ApiKeys {
    CURRENCY_API("j7Nd8XXjn1WXIQcPhQkQxptEQZtOlNbf");

    private final String key;
    ApiKeys(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }
}
