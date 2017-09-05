# Exercise 8.5 Camel Integration

Use Apache Camel to get Docker events.

### Step 1

Start your Google Compute Engine virtual machine and connect to it using SSH.

### Step 2 - skip this step if you have already made these changes.  

We need to change the Docker startup script so that it listens on TCP port
2375.  
`sudo vi /etc/systemd/system/multi*/docker.service`  
Add a second -H switch the the ExecStart line so that it reads:  
`ExecStart=/usr/bin/dockerd -H fd:// -H tcp://0.0.0.0:2375`  
Reload the daemon processes.  
`sudo systemctl daemon-reload`  
Restart Docker.  
`sudo systemctl restart docker`  
Check that there is a listener on port 2375:  
`netstat -an | grep 2375`  

Install telnet.  
`sudo apt-get install telnet`  
Connect to Docker using telnet:  
`telnet localhost 2375` 
Now type the following two HTTP headers then hit enter twice.  
`GET /events HTTP/1.1`  
`Host: localhost`  
You should see a response from Docker.  
Exit telnet by typing control-] followed by quit.  

### Step 3

Start the VNC server.  
`vncserver`  

Start the VNC client on your local machine and enter x.x.x.x.:5901, replacing
x.x.x.x with the external IP address of your virtual machine.

Start Eclipse.

Open up the DevOps project and the src and test folders. Open the files
DockerRouting.java and DockerRoutingTests.java. The first file uses
Apache Camel to read event data from Docker. The second file uses the Apache
Camel test framework to run routes.

Run DockerRoutingTests.java as a JUnit test.

Go to your SSH window and generate events.  
`docker run -it --rm centos /bin/bash`  
Exit with control-D.  

Go back to Eclipse and the integration test should have passed.  
Look through the console output and see what events were reported.
The actual Docker message is the text starting _Event_.

### Step 4

Open the files DockerData.java and DockerDataTests.java in Eclipse. Se
that we are populating a Docker object from Event data. The unit test
artifically generate the event.  
Run the unit tests in DockerDataTests.java. They should pass.

### Step 5

Run the integration tests again. Use the log file output to add
another unit test to DockerDataTests.java for a different event type.

