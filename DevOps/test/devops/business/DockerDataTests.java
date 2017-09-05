package devops.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.dockerjava.api.model.Event;
import com.github.dockerjava.api.model.EventType;

public class DockerDataTests {
	private Event createEvent  ;
	private DockerData dockerData ;
	
	@Before
	public void setup() {
		dockerData = new DockerData() ;
		createEvent = new Event( "create", "8100f5fdc9784e78799ecf5a7e8961349d2ce21e655ad7247c4034764ae7e62e", "centos", 1483609742L ) ;
		createEvent.withType( EventType.CONTAINER ) ;
	}
	
	@Test
	public void dockerCreateContainerEvent() {
		Docker docker = dockerData.processDockerData( createEvent ) ;
		System.out.println( docker ) ;
		Assert.assertEquals( "Create", "CONTAINER CREATE from centos", docker.getMessage().substring( 0, 28 ) ) ;
	}

}
