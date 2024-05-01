package ru.ssau.flightsmonitor.exception;

public class NoEntityException extends Exception {
    public NoEntityException(Long entityId) {
        super("Not found " + entityId.toString());
    }
}
