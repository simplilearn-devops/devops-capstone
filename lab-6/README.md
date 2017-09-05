# Exercise 8.6 Monitoring back end.

Use Apache Camel to get Docker events.

### Step 1

Start your Google Compute Engine virtual machine and connect to it using SSH.

Change to the exercise directory.  
`cd`  
`cd devops-lesson-8/lab-8.6`  

Start the MySQL server.  
`./runserver`

### Step 2

Start the VNC server.  
`vncserver`  

Start the VNC client on your local machine and enter x.x.x.x.:5901, replacing
x.x.x.x with the external IP address of your virtual machine.

Start Eclipse.

Open up the DevOps project and the src and test folders. Open the files
DockerRouting.java, DockerProcessor.java and MonitoringServer.java.
These file complete a standalone application to read Docker events and store
them in the database.

Run MonitoringServer.java as a Java application. It will run for 2 minutes.

Go to your SSH window and generate events.  
`docker run -it --rm centos /bin/bash`  
Exit with control-D.  

### Step 3

Go back to the SSH terminal.  
Run the MySQL client.  
`./runclient`  

Run the MySQL client.  
`mysql -h 172.17.0.2 -u student -p monitoring`  
See that data has been written to the database.  
`select * from docker;`  
Exit with control-D and control-D.  

### Step 4

Restart the MonitoringServer if it has stopped.  
Generate some more events by starting containers.  
See that the database gets new records.

There is no need to shut anything down as we will soon be doing the final
exercise.
