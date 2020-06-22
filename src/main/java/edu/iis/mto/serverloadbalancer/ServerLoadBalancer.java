package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

    public void balance(Server[] singleServerArray, Vm[] vms) {
        for (Vm vm : vms) {
            singleServerArray[0].addVm(vm);
        }
    }
}
