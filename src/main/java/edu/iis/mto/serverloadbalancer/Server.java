package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server {

    private int capacity;
    private int filledCapacity = 0;
    private List<Vm> vms = new ArrayList<>();

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public double getFillPercentage() {
        return filledCapacity * 1.0d / capacity * 100.0;
    }

    public int getFreeSpace() {
        return capacity - filledCapacity;
    }

    public void addVm(Vm vm) throws NotEnoughFreeSpaceInServerException {
        if (this.filledCapacity + vm.getSize() > this.capacity) {
            throw new NotEnoughFreeSpaceInServerException("There is not enough space for this Vm! Free space: " + (this.capacity - this.filledCapacity) + " Vm size: " + vm.getSize());
        }
        vms.add(vm);
        filledCapacity += vm.getSize();
    }

    public boolean contains(Vm vm) {
        return this.vms.contains(vm);
    }

    public boolean contains(Vm[] vms) {
        return this.vms.containsAll(Arrays.asList(vms));
    }

    public boolean isMoreOrSameFilled(Server server) {
        return this.getFillPercentage() >= server.getFillPercentage();
    }
}
