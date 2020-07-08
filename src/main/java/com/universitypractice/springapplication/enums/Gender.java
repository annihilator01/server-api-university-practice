package com.universitypractice.springapplication.enums;

public enum Gender {
    MALE,
    FEMALE,
    OTHER;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
