package devops.integration;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import devops.business.Docker;

public class DockerDAOTests {
	private static DockerDAO dao ;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new DockerDAOImpl() ;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao.close() ;
	}

	@Test
	public void addNewDockerItem() {
		Docker docker = new Docker() ;
		docker.setMessage( "New Docker container created" ) ;
		List<Docker> items = dao.findAll() ;
		int nextId = items.get( items.size() - 1 ).getId() + 1 ;
		Assert.assertEquals( "Save", nextId, dao.save( docker ).intValue() ) ;
	}

}
