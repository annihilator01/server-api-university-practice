package com.universitypractice.springapplication.enums;

public enum Status {
    ONLINE,
    OFFLINE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
