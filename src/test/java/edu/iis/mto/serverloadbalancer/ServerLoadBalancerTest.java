package edu.iis.mto.serverloadbalancer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServerLoadBalancerTest {

	@Test
	public void oneServerAndNoVmsShouldResultInServerWithNoVms() {
		Server[] servers = {new Server(10)};
		Vm[] vms = new Vm[0];
		ServerLoadBalancer serverLoadBalancer = new ServerLoadBalancer();

		serverLoadBalancer.balance(servers, vms);

		assertEquals(10, servers[0].getCapacity());
	}
}
