package devops.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import devops.integration.DockerRouting;

public final class MonitoringServer {
	private final Logger logger ;
	private final CamelContext context ;
	
	public MonitoringServer() {
		logger = LoggerFactory.getLogger( getClass() ) ;
		context = new DefaultCamelContext() ;
		try {
			context.addRoutes( new DockerRouting( DockerRouting.DIRECT_ENDPOINT ) ) ;
		} catch ( Exception e ) {
			logger.error( "Exception adding route", e ) ;
		}
	}
	
	public void execute() {
		logger.info( "Executing Camel" ) ;
		try {
			context.start() ;
			Thread.sleep( 120000 ) ;
			context.stop() ;
		} catch (Exception e) {
			logger.error( "Exception on execute", e ) ;
		}
	}
	
	public static void main(String[] args) {
		new MonitoringServer().execute() ;
	}

}
