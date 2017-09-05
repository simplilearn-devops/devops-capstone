package devops.integration;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.github.dockerjava.api.model.Event;

import devops.business.DockerData;

public class DockerProcessor implements Processor {
	private final DockerDAO dockerDAO ;
	private final DockerData dockerData ;
	
	public DockerProcessor() {
		dockerDAO = new DockerDAOImpl() ;
		dockerData = new DockerData() ;
	}

	@Override
	public void process( Exchange exchange ) throws Exception {
		Event event = exchange.getIn().getBody( Event.class ) ;
		dockerDAO.save( dockerData.processDockerData( event ) ) ;
	}

}
