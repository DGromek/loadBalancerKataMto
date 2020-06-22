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
        return 1.0d * filledCapacity / capacity  * 100;
    }

    public boolean contains(Vm vm) {
        return vms.contains(vm);
    }

    public boolean contains(Vm[] vms) {
        return this.vms.containsAll(Arrays.asList(vms));
    }

    public void addVm(Vm vm) {
        vms.add(vm);
        filledCapacity += vm.getSize();
    }
}
