package edu.iis.mto.serverloadbalancer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServerLoadBalancerTest {

    private ServerLoadBalancer serverLoadBalancer;

    @Before
    public void setUp() {
        serverLoadBalancer = new ServerLoadBalancer();
    }

    @Test
    public void oneServerAndNoVmsShouldResultInServerWithNoVms() {
        Server[] singleServerArray = {new Server(10)};
        Vm[] vms = new Vm[0];

        serverLoadBalancer.balance(singleServerArray, vms);

        assertEquals(0, singleServerArray[0].getFillPercentage(), 0.001);
    }

    @Test
    public void oneServerWith10CapacityAndOneVmWith10SizeShouldResultInFullyFilledServer() {
        Server[] singleServerArray = {new Server(10)};
        Vm[] vms = {new Vm(10)};
        serverLoadBalancer.balance(singleServerArray, vms);

        assertEquals(100.0d, singleServerArray[0].getFillPercentage(), 0.001);
        assertTrue(singleServerArray[0].contains(vms[0]));
    }

    @Test
    public void oneServerAndOneVmWithSmallerSizeThanServerCapacityShouldResultInPartialFilledServer() {
        Server[] singleServerArray = {new Server(10)};
        Vm[] vms = {new Vm(5)};
        serverLoadBalancer.balance(singleServerArray, vms);

        assertEquals(50.0, singleServerArray[0].getFillPercentage(), 0.001);
        assertTrue(singleServerArray[0].contains(vms[0]));
    }

    @Test
    public void oneServerAndFewVmsWithFillingServerCapacityShouldResultInFilledServerContainingAllVms() {
        Server[] singleServerArray = {new Server(10)};
        Vm[] vms = {new Vm(5), new Vm(3), new Vm(1), new Vm(1)};
        serverLoadBalancer.balance(singleServerArray, vms);

        assertEquals(100.0, singleServerArray[0].getFillPercentage(), 0.001);
        assertTrue(singleServerArray[0].contains(vms));
    }

    @Test
    public void twoServersAndOneVmShouldBeAssignedToLessFilledServer() {
        Server[] servers = {new ServerBuilder().withCapacity(10)
                                               .withFilledCapacity(6).build(), new ServerBuilder().withCapacity(10)
                                                                                                  .withFilledCapacity(3).build(),};
        Vm[] vms = {new Vm(4)};
        serverLoadBalancer.balance(servers, vms);

        assertFalse("First Server should't contain vm", servers[0].contains(vms[0]));
        assertTrue("Second Server should contain vm", servers[1].contains(vms[0]));

        assertEquals(60.0, servers[0].getFillPercentage(), 0.001);
        assertEquals(70.0, servers[1].getFillPercentage(), 0.001);
    }

    @Test(expected = NotEnoughFreeSpaceInServerException.class)
    public void serverTooOverloadedToAddVmShouldResultInException() throws NotEnoughFreeSpaceInServerException {
        Server[] singleServerArray = {new Server(10)};
        Vm[] vms = {new Vm(12)};
        serverLoadBalancer.balance(singleServerArray, vms);
    }
}
