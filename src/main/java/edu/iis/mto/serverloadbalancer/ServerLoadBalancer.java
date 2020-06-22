package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

    public void balance(Server[] servers, Vm[] vms) {
        Server serverWithLesserFillLevel = servers[0];

        for (Vm vm : vms) {
            for (Server server : servers) {
                if (serverWithLesserFillLevel.getFillPercentage() > server.getFillPercentage()) {
                    serverWithLesserFillLevel = server;
                }
            }
            serverWithLesserFillLevel.addVm(vm);
        }
    }
}
