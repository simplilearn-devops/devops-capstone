package devops.business;

import java.util.List;

import devops.integration.DockerDAO;

public class DataManagerImpl implements DataManager {
	private DockerDAO dockerDao ;
	
	public void setDockerDao( DockerDAO dockerDao ) {
		this.dockerDao = dockerDao;
	}

	@Override
	public String getTitle() {
		return "Docker Events" ;
	}

	@Override
	public List<Docker> getDockerData() {
		return dockerDao.findAll() ;
	}

}
