package devops.business;

import java.util.Date;

import com.github.dockerjava.api.model.Event;

public class DockerData {
	
	public Docker processDockerData( Event event ) {
		String status = event.getStatus() != null ? event.getStatus().toUpperCase() : "NONE" ;
		String from = event.getFrom() != null ? event.getFrom() : "NONE" ;
		return new Docker( new Date( event.getTime() * 1000L )
				, event.getType() + " " + status + " from " + from + " id " + event.getId() ) ;
	}
}
