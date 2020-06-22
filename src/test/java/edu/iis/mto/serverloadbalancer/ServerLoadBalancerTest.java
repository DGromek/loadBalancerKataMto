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
    public void oneServerAndNoVmsShouldResultInServerWithNoVms() throws NotEnoughFreeSpaceInServerException {
        Server[] singleServerArray = {new Server(10)};
        Vm[] vms = new Vm[0];

        serverLoadBalancer.balance(singleServerArray, vms);

        assertEquals(0, singleServerArray[0].getFillPercentage(), 0.001);
    }

    @Test
    public void oneServerWith10CapacityAndOneVmWith10SizeShouldResultInFullyFilledServer() throws NotEnoughFreeSpaceInServerException {
        Server[] singleServerArray = {new Server(10)};
        Vm[] vms = {new Vm(10)};
        serverLoadBalancer.balance(singleServerArray, vms);

        assertEquals(100.0d, singleServerArray[0].getFillPercentage(), 0.001);
        assertTrue(singleServerArray[0].contains(vms[0]));
    }

    @Test
    public void oneServerAndOneVmWithSmallerSizeThanServerCapacityShouldResultInPartialFilledServer()
            throws NotEnoughFreeSpaceInServerException {
        Server[] singleServerArray = {new Server(10)};
        Vm[] vms = {new Vm(5)};
        serverLoadBalancer.balance(singleServerArray, vms);

        assertEquals(50.0, singleServerArray[0].getFillPercentage(), 0.001);
        assertTrue(singleServerArray[0].contains(vms[0]));
    }

    @Test
    public void oneServerAndFewVmsWithFillingServerCapacityShouldResultInFilledServerContainingAllVms()
            throws NotEnoughFreeSpaceInServerException {
        Server[] singleServerArray = {new Server(10)};
        Vm[] vms = {new Vm(5), new Vm(3), new Vm(1), new Vm(1)};
        serverLoadBalancer.balance(singleServerArray, vms);

        assertEquals(100.0, singleServerArray[0].getFillPercentage(), 0.001);
        assertTrue(singleServerArray[0].contains(vms));
    }

    @Test
    public void twoServersAndOneVmShouldBeAssignedToLessFilledServer() throws NotEnoughFreeSpaceInServerException {
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

    @Test
    public void fewServersAndFewVmsShouldResultInBalancedPlacingOfVms() throws NotEnoughFreeSpaceInServerException {
        Server[] servers = {new Server(5), new Server(10), new Server(3)};
        Vm[] vms = {new Vm(7), new Vm(2), new Vm(4)};

        serverLoadBalancer.balance(servers, vms);

        assertTrue("First Server should contain third vm", servers[0].contains(vms[2]));
        assertTrue("Second Server should contain first vm", servers[1].contains(vms[0]));
        assertTrue("Third Server should contain second vm", servers[2].contains(vms[1]));

        assertEquals(80.0 ,servers[0].getFillPercentage(), 0.001);
        assertEquals(70.0 ,servers[1].getFillPercentage(), 0.001);
        assertEquals(66.666 ,servers[2].getFillPercentage(), 0.001);
    }
}
