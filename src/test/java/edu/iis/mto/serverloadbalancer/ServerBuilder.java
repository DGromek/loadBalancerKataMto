package edu.iis.mto.serverloadbalancer;

public class ServerBuilder {
    private int capacity = 10;
    private int filledCapacity;

    public ServerBuilder() {
    }

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public ServerBuilder withFilledCapacity(int filledCapacity) {
        if (filledCapacity > this.capacity) {
            throw new IllegalArgumentException("Filled capacity can't be bigger than capacity.");
        }
        this.filledCapacity = filledCapacity;
        return this;
    }

    public Server build() {
        Server server = new Server(capacity);
        Vm filler = new Vm(filledCapacity);
        server.addVm(filler);
        return server;
    }
}
