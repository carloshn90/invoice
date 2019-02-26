package com.carlos.invoice.server.matchers;

import org.mockito.ArgumentMatcher;

public class IsStringNotEmptyAndLengthEq implements ArgumentMatcher<String> {

    private int length;

    public IsStringNotEmptyAndLengthEq(int length) {
        this.length = length;
    }

    @Override
    public boolean matches(String value) {

        if (value == null || value.isEmpty()) {
            return false;
        }

        return value.length() == this.length;
    }
}
