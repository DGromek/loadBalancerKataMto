package edu.iis.mto.serverloadbalancer;

public class Server {
    private int capacity;

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
