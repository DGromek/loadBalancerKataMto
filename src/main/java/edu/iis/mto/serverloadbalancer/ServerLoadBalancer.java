package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

    public void balance(Server[] servers, Vm[] vms) throws NotEnoughFreeSpaceInServerException {
        Server serverWithLesserFillLevel = servers[0];

        for (Vm vm : vms) {
            for (Server server : servers) {
                if (serverWithLesserFillLevel.isBiggerOrSameFilled(server) && (server.getFreeSpace() >= vm.getSize())) {
                    serverWithLesserFillLevel = server;
                }
            }
            serverWithLesserFillLevel.addVm(vm);
        }
    }
}
