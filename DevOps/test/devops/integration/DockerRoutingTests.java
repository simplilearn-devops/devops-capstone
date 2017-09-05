package devops.integration;

import java.util.concurrent.TimeUnit;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class DockerRoutingTests extends CamelTestSupport {
	private static final String MOCK_OUTPUT = "mock:output" ;

	@Override
	protected RoutesBuilder createRouteBuilder() throws Exception {
		return new DockerRouting( MOCK_OUTPUT ) ;
	}

	@Test
	public void dockerMySqlContainer() throws InterruptedException {
		MockEndpoint mock = getMockEndpoint( MOCK_OUTPUT ) ;
		mock.expectedMessageCount( 5 ) ;
		this.assertMockEndpointsSatisfied( 10, TimeUnit.MINUTES ) ;
	}

}
