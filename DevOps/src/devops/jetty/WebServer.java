package devops.jetty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.servlet.JspServlet;
import org.eclipse.jetty.annotations.ServletContainerInitializersStarter;
import org.eclipse.jetty.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.plus.annotation.ContainerInitializer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class WebServer {
	public static final int PORT = 8080 ;
	private static final String WEBAPP_DIRECTORY = "webapp";
    private static final String CONTEXT_PATH = "/";
	private Server server ;
	
	public WebServer() throws IOException {
		server = new Server( PORT ) ;
        
        WebAppContext webAppContext = new WebAppContext() ;
        webAppContext.setClassLoader( Thread.currentThread().getContextClassLoader() ) ;
        webAppContext.setResourceBase( WEBAPP_DIRECTORY ) ;        
        webAppContext.setContextPath( CONTEXT_PATH );
        webAppContext.addServlet( JspServlet.class, "*.jsp" ) ;
        
        JettyJasperInitializer sci = new JettyJasperInitializer();
        ContainerInitializer initializer = new ContainerInitializer( sci, null ) ;
        List<ContainerInitializer> initializers = new ArrayList<ContainerInitializer>();
        initializers.add( initializer ) ;
        webAppContext.setAttribute( "org.eclipse.jetty.containerInitializers", initializers ) ;
        
        webAppContext.addBean( new ServletContainerInitializersStarter( webAppContext ), true ) ;
        
		server.setHandler( webAppContext ) ;
		server.setStopAtShutdown( true ) ;
	}
	
	public void startJetty() {
		try {
			server.start() ;
			server.join() ;
		} catch (Exception e) {
			System.err.println( "Exception " + e ) ;
		}
	}
	
	public static void main( String[] args ) throws IOException {
		new WebServer().startJetty() ;
	}
}
