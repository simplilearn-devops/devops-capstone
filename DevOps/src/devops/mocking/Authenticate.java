package devops.mocking;

public interface Authenticate {
	public String verifyUser( String user, String password ) ;
	public boolean tokenIsValid( String token ) ;
}
