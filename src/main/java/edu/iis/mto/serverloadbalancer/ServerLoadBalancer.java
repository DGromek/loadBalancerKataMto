package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

    public void balance(Server[] servers, Vm[] vms) throws NotEnoughFreeSpaceInServerException {
        Server serverWithLowerFillLevel = servers[0];

        for (Vm vm : vms) {
            for (Server server : servers) {
                if (server.getFillPercentage() < serverWithLowerFillLevel.getFillPercentage()) {
                    serverWithLowerFillLevel = server;
                }
            }
            serverWithLowerFillLevel.addVm(vm);
        }
    }
}
