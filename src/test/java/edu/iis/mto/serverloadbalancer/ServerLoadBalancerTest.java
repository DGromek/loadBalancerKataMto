package edu.iis.mto.serverloadbalancer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServerLoadBalancerTest {

	@Test
	public void oneServerAndNoVmsShouldResultInServerWithNoVms() {
		Server[] servers = {new Server(10)};
		Vm[] vms = new Vm[0];
		ServerLoadBalancer serverLoadBalancer = new ServerLoadBalancer();

		serverLoadBalancer.balance(servers, vms);

		assertEquals(0, servers[0].getFillPercentage(), 0.001);
	}

	@Test
	public void oneServerWith10CapacityAndOneVmWith10SizeShouldResultInFullyFilledServer() {
		Server[] servers = {new Server(10)};
		Vm[] vms = {new Vm(10)};
		ServerLoadBalancer serverLoadBalancer = new ServerLoadBalancer();

		serverLoadBalancer.balance(servers, vms);
		assertEquals(100.0d, servers[0].getFillPercentage(), 0.001);
		assertTrue(servers[0].contains(vms[0]));
	}
	
}
