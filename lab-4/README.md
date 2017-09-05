# Exercise 8.2 Jenkins and Git

In this exercise we will integrated Jenkins ad Git in Docker containers.

### Step 1

Start the Google Compute Engine virtual machine and connect to it using SSH.

Check that the firewall allows traffice on port 8080. It should have been set
up in an earlier lesson.

Delete SSH hosts to prevent problems later.  
`rm -r ~/.ssh/known_hosts`

Change to the exercise directory. 
`cd`  
`cd devops-lesson-8/lab-8.2`  

Create a directory for Jenkins data.  
`mkdir jenkins`  
`chmod 777 jenkins`  

### Step 2

Get a Jenkins image from DockerHub.  
`docker pull jenkins`  

Start jenkins.  
`docker run -d --name jenkins -p 8080:8080 -p 50000:50000 -v $PWD/jenkins:/var/jenkins_home/ jenkins`  

Wait until jenkins is up and running. Use:  
`docker logs jenkins`  

### Step 3

We need to ensure that we can connect from Jenkins to Git via SSH.  
First run an interactive jenkins container and create SSH keys.  
`docker run -it --rm -v $PWD/jenkins:/var/jenkins_home jenkins /bin/sh`  
Generate SSH keys.  
`ssh-keygen -t rsa`  
Exit the container with control-D.  

Copy the public keys for the student and jenkins users.  
`sudo cat ~/.ssh/id_rsa.pub jenkins/.ssh/id_rsa.pub > git/authorized_keys`  

Build a Git image.  
`docker build -t git git`

Start a Git container.  
`docker run -d -p 2022:22 --name git git`  
Find its IP address and make a note of it.  
`docker inspect git`  

Now run a Jenkins container again and verify that you can connect.  
`docker run -it --rm -v $PWD/jenkins:/var/jenkins_home jenkins /bin/sh`  
Now connect to the Git container using SSH. You may have to change the
last digit of the IP address to that of your container.  
`ssh git@172.17.0.5`  
Answer yes to accept the key.  
You should connect OK.  
Exit both containers with control-D control-D.

Clone the Git repository. Ignore the warning about the repository being empty.  
`git clone ssh://git@localhost:2022/home/git/project.git` 

Check in some data.  
`cd project`  
`echo "First message" > readme.txt`  
`git add readme.txt`  
`git commit -m "First checkin"`  
`git push`  

### Step 4

You will need an admin password. Get the jenkins logs and scroll back to find
the password. Copy it ready to paste later.  
`docker logs jenkins`

Start a browser on your local machine and enter the URL x.x.x.x:8080 replacing
x.x.x.x with the external IP address of your virtual machine.

You will see an _Unlock Jenkins_ screen. Paste the admin password and hit
_Continue_.

The next screen is _Customize Jenkins_. Hit _Select plugins to install_.

Select _Source Code Management_. Make sure that _Git plugin_ is selected.

Select _Install_.

A _Getting Started_ screen will be displayed and plugins will start
downloading. This will take a few minutes.

You will next be asked to create an admin user. Fill in the form and select
_Save and Finish_.

Jenkins is set up. Select _Start using Jenkins_.

### Step 5

Select _create new jobs_.  
Enter the name _DevOps_.  
Select _Freestyle project_.  
Hit _OK_.

Scroll down to _Source Code Management_.  
Select _Git_.  
Enter the following URL possibly changing the last digit of the IP to your Git IP.  
`ssh://git@172.17.0.5/home/git/project.git`  
Hit _Apply_. The URL should be accepted. If there is an error in red check the
URL.  
Hit _Save_.

You will be taken to the _Project DevOps_ page.

Select _Build Now_. The project should build.  
Click on the _#1_ at the bottom of the page. It will show the build and the 
date and time.  
Select _Console Output_.  
You should see the details of the build.

### Step 6

Stop the servers.  
`docker stop jenkins git`  

