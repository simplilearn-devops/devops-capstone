# Exercise 8.7 Case Study Front End.

In this exercise we build the Case Study Web front end and connect it
through to the database.

### Step 1

Start the Goocle Compute Engine virtual machine and connect via SSH.

Start the MySQL server.  
`cd`  
`cd devops-lesson-8`  
`lab-8.6/runserver` 

Start the VNC server.  
`vncserver`

### Step 2

Connect the VNC client on your local machine to the server on port 5901.  
Start Eclipse and open up the DevOps project.

### Step 3

We are now going to walk through the code.  
For each file described, open it up in Eclipse and read through the code.
The code we have seen before won't be described again here.

The file _devops.jetty.WebServer_ implements a Web server using the Jetty
API. Most Web servers are stand alone. Jetty is designed to be embedded
in applications. What the code does is to listen on port 8080, enable
the processing of JSPs and tells it the Web content is in the webapps
directory.

The file _webapp/WEB-INF/web.xml_ is the entry point of the application.
It loads the Spring framework which is responsible for creating objects
and wiring them together.
The most important thing is it loads _web-context.xml_. It also loads
_application-context.xml_ which we will look at later.

The file _web-context.xml_ tells Spring that there are annotated POJOs
in the _devops.mvc_ package. It also tells Spring that the views are
JSP files on the WEB-INF/jsp directory.

The file _devops.mvc.MonitoringController_ is found by Sring because of
the _@Controller_ annotation. Spring will automaticall instantiate this
class. The _@Autowired_ annotation on the _setDataManager_ method will cause
Spring to inject a data manager automatically. The home method responds
to the URL _index.html_ and returns the view defined in index.jsp.
The monitoring method responds to the URL _monitoring.html_. It uses the
data manger to get the title and a list of Docker objects. Theses are
passed via the model map into the JSP monitoring.jsp.

The file monitoring.jsp takes the model data and converts it into HTML.
Anything in ${} extracts data from the model map. The ${} item in the
table call get methods on a Docker POJO.

The file _devops.busines.DataManager_ is an interface connecting the
presentation and business tiers. It defines the methods which the
controller can use.

The file _devops.business.DataManagerImpl_ implements the interface.
The DAO is injected into the setter method by Spring.

Finally the file _application-context.xml_ tells Spring to instantiate
a data manager and a DAO. It tells Spring to inject the DAO into the 
data manager. The _@Autowired_ annotation we saw in the controller
causes implicit injection of the data manager created here into the
controller.

### Step 3

Run the application.  
Run _devops.jetty.WebServer.java_ as a Java application.

On your local machine connect a Web browser to port 8080 on the external
IP address of your virtual machine.

You should see the opening Web page.  
Click on the link and you should se the Docker events from the database.

If you have time run the back end again and add more events.  
Refresh the page by clicking on the link and see the new data.

### Step 4

Disconnect form the SSH session with control-D.

Stop the virtual machine. It is important that do do this to avoid incurring costs.

Note that you will need the virtual machine and the DevOps code for
the Lesson 8 exercises as a starting point for the projects.
 
