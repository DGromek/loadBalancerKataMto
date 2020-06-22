package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

    public void balance(Server[] servers, Vm[] vms) throws NotEnoughFreeSpaceInServerException {
        Server serverWithLesserFillLevel = servers[0];

        for (Vm vm : vms) {
            for (Server server : servers) {
                if (serverWithLesserFillLevel.isBiggerFilled(server)) {
                    serverWithLesserFillLevel = server;
                }
            }
            serverWithLesserFillLevel.addVm(vm);
        }
    }
}
