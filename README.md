
# SmartTool 
## Project Scope: 
A General Purpose Java Web based application with an Oracle Database customizable for multi purpose for example :

* Enterprise Performance Management (EPM)

* Student Information Management 



# How To Run 
## Run The SmartTool application on a local Docker Image 

### Prerequisite 

1- Install git on your machine
 
Install from -> [https://git-scm.com/book/en/v2/Getting-Started-Installing-Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

2- Install Docker on your machine 

Install from -> [https://docs.docker.com/engine/install/](https://docs.docker.com/engine/install/)

### Steps 
1- Clone application repository 

~~~
git clone https://github.com/shawkyGalal/SmartTool.git
~~~
2- From Your Command line execute the following 

~~~
cd SmartTool
Startup.bat 
~~~
from Your browser you should be able to Navigate to : 

~~~
http://localhost:8080/SmartTool/index.jsp
~~~

## Run The SmartTool application on a Google Cloud Compute engine instance 

1- Log in to your Google Cloud Console 

2- Open a cloud shell session 

3- Upload the ./buildAndRunOnGCP.sh  to the cloud shell

4- Optional if need to run smarttool as a service, Upload the ./smarttool.service  to the cloud shell

3- run the script : ./buildAndRunOnGCP.sh 


