# Exercise 8.3 - Relational Database

Create a relational database container and create and access the case study database.

### Step 1

Open the Cloud Platform Console at https://console.cloud.google.com. Go to Compute Engine and VM Instances.
Start the VM if it isn’t running and connect using SSH.

### Step 2

Change to the lab directory.  
`cd`  
`cd devops-lesson-8/lab-8.3`  

### Step 3

Initialize directory structure

Create a Docker data volume to hold the database.

`docker volume create --name monitoring_data`  

Confirm that the data volume was created.

`docker volume ls`  

Examine the script that will run and create the scase study database structure.

`cat runserver_first`  

Run the script to create a container with MySQL running and create the database.

`./runserver_first`  

You will need to monitor the logs to see when MySQL has completed creating
the student database and is waiting for connections. This may take several minutes
to complete.  
`docker logs mysql`  

When you see the following in the logs you may continue. Run the command until you see.

`mysqld: ready for connections.`  

Find the IP address of the server.  
`docker inspect mysql`

### Step 4

Load data into the student database

Examine the script that will run the MySQL client. Notice that we are using the same image.  
`cat runclient`  

Run the MySQL client container.  
`./runclient`  

You will be placed inside the client container running the Bash command shell.
You can now type commands to use the database. You may need to change the IP address to that of the server.  
`mysql -h 172.17.0.2 -u student -p monitoring`  

You will be prompted for the password. Enter 'student' as the password  
`Enter password:`  

When you connect you will see the MySQL client prompt  
`mysql>`  

At the prompt read in the definition for the employee information.  
`mysql> source /data/docker.sql;`  

### Step 5

Examine the database tables in the database by selecting the first 10 rows from each

`mysql> show tables;`  

Get the content of the docker table.  

`mysql> select * from docker;`  

Insert another row of data and query the table again.

### Step 5
 
Clean up after the lab

 Exit from the MySQL client  
`mysql> quit;`  

 Exit from the client container  
 `exit`  

