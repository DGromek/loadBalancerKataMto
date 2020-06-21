package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
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

    public void addVm(Vm vm){
        vms.add(vm);
        filledCapacity += vm.getSize();
    }

    public boolean contains(Vm vm) {
        return this.vms.contains(vm);
    }
}
