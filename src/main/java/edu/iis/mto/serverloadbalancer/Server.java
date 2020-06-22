package edu.iis.mto.serverloadbalancer;

public class Server {

    private int capacity;
    private int filledCapacity = 0;

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public double getFillPercentage() {
        return 1.0d * filledCapacity / capacity  * 100;
    }
}
