package devops.integration;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import devops.business.Docker;

public class DockerDAOImpl implements DockerDAO {
	private final Logger logger ;
	private SessionFactory sessionFactory ;
	private Session session ;
	
	public DockerDAOImpl() {
		logger = LoggerFactory.getLogger( getClass() ) ;
		try {
			logger.info( "Opening connection" ); ;
			sessionFactory = new Configuration().configure( "hibernate.cfg.xml" ).buildSessionFactory() ;
			session = sessionFactory.openSession() ;
		} catch ( HibernateException e ) {
			logger.error( "Hibernate Exception", e ) ;
			System.exit( 1 ) ;
		}
	}
	
	@Override
	public void close() {
		logger.info( "Closing connection" ) ;
		sessionFactory.close() ;
	}

	@Override
	public Integer save( Docker docker ) {
		session.beginTransaction() ;
		Integer id = (Integer) session.save( docker ) ;
		session.getTransaction().commit() ;
		return id ;
	}
	
	@Override
	public void delete( Docker docker ) {
		session.beginTransaction() ;
		session.delete( docker ) ;
		session.getTransaction().commit() ;
	}

	@Override
	public List<Docker> findAll() {
		return session.createQuery( "From Docker", Docker.class ).getResultList() ;
	}

}
