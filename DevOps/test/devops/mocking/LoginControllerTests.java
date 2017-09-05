package devops.mocking;

import org.junit.*;
import org.mockito.Mockito;

public class LoginControllerTests {

	@Test
	public void mockingVerificationOfLogin() {
		Authenticate mockAuthenticator = Mockito.mock(Authenticate.class) ;
		LoginController loginController = new LoginController( mockAuthenticator ) ;

		loginController.authenticate( "user", "password" ) ;

		Mockito.verify( mockAuthenticator ).verifyUser( "user", "password" ) ;
	}

	@Test
	public void stubbingOfLoginAuthenticator() {
		Authenticate mockAuthenticator = Mockito.mock(Authenticate.class) ;
		LoginController loginController = 
		   new LoginController( mockAuthenticator ) ;
		Mockito.when( mockAuthenticator.verifyUser( "user", "password" ) )
		   .thenReturn( "abcd1234" ) ;
		loginController.authenticate( "user", "password" ) ;

		Assert.assertEquals( "Token", "abcd1234", loginController.getToken() );
	}
}
