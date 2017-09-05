package devops.integration;

import java.util.List;

import devops.business.Docker;

public interface DockerDAO {
	public Integer save( Docker docker ) ;
	public void delete( Docker docker ) ;
	public List<Docker> findAll() ;
	public void close() ;
}
