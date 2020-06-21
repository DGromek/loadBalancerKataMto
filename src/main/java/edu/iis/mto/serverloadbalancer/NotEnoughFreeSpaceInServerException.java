package edu.iis.mto.serverloadbalancer;

public class NotEnoughFreeSpaceInServerException extends Exception {

    public NotEnoughFreeSpaceInServerException(String message) {
        super(message);
    }

    public NotEnoughFreeSpaceInServerException() {

    }
}
