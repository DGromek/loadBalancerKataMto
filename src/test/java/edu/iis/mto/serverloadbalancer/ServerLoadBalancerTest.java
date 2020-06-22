package edu.iis.mto.serverloadbalancer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServerLoadBalancerTest {

    @Test
    public void oneServerAndNoVmsShouldResultInServerWithNoVms() {
        ServerLoadBalancer serverLoadBalancer = new ServerLoadBalancer();
        Server[] singleServerArray = {new Server(10)};
        Vm[] vms = new Vm[0];

        serverLoadBalancer.balance(singleServerArray, vms);

        assertEquals(0, singleServerArray[0].getFillPercentage(), 0.001);
    }

    @Test
    public void oneServerWith10CapacityAndOneVmWith10SizeShouldResultInFullyFilledServer() {
        ServerLoadBalancer serverLoadBalancer = new ServerLoadBalancer();
        Server[] singleServerArray = {new Server(10)};
        Vm[] vms = {new Vm(10)};
        serverLoadBalancer.balance(singleServerArray, vms);

        assertEquals(100.0d, singleServerArray[0].getFillPercentage(), 0.001);
        assertTrue(singleServerArray[0].contains(vms[0]));
    }
}
