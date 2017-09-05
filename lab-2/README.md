# Exercise 8.4 case study DAO.

In this exercise we will use MySQL and Hibernate to create a DAO.
Hibernate is an ORM which greatly simplifies the persistence of objects.
We will test the DAO using JUnit integration tests.

We are not using Maven for this project as sometimes it gets dependencies
wrong. The necessary jars are part of the project.

### Step 1

Start your Google Compute Engine virtual machine and connet to it using SSH.

Start the MySQL server we created earlier.  
`cd`
`cd devops-lesson-8/lab-8.4`  
`./runserver`  

Start the vncserver.  
`vncserver`  

### Step 2

Start the VNC client on your local machine using x.x.x.x:5901. Where
x.x.x.x is the external IP address of the virtual machine. The password is ‘simplilearn’.  

### Step 3

Start Eclipse. Open a new Java project using the devops-lesson-8/DevOps
directory in your home directory as the location of the project.

The code won't compile initially. Right click on the DevOps project and
select _Preferences_ then select _Java Build Path_.  
Go to the Libraries tab and do the following:  
* Select _Add Library_ and ad the JUnit 4 library.  
* Select _Add Class Folder_ and add the resources folder.  
* Select _Add Jars_ open the DevOps folder and if there is a lib folder
open that and add all jars from all of the folders under lib. Eclipse may have
done this step for you.  
* Select OK and everything should compile.

### Step 4

Open up the src, test and resources folders in the _Package Explorer_.  
Open up the files Docker.java, DockerDAO.java, DockerDAOImpl.java,
DockerDAOTest.java and hibernate.cfg.xml.  
Check that hibernate.cfg.xml hase the right MySQL connection information.  

Right click on DockerDAOTests.java and select _Run as_ _JUnit Test_.  
The unit tests should run successfully.  
Check out the console logs and see what output Hibernate produced.

### Step 5

Try adding another unit test. Remember that these are integration tests,
so each test should either be independent of the database state or the
database needs to be reset before running the tests.

