
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
Consider this Application deployment on GCP will incure charges for your GCP billing account 
1- Log in to your Google Cloud Console  
~~~

https://console.cloud.google.com/
~~~


2- Open a cloud shell session 

3 - Create new VM instance using the following gcloud command ( Update as per your prefrences  

~~~
PROJECT=moj-prod-apigee
ZONE=us-central1-a
VM_NAME=smarttool

gcloud compute instances create $VM_NAME \
    --project=$PROJECT \
    --zone=$ZONE \
    --machine-type=e2-medium \
    --network-interface=network-tier=PREMIUM,stack-type=IPV4_ONLY,subnet=default \
    --maintenance-policy=MIGRATE \
    --provisioning-model=STANDARD \
    --service-account=598074804327-compute@developer.gserviceaccount.com \
    --scopes=https://www.googleapis.com/auth/devstorage.read_only,https://www.googleapis.com/auth/logging.write,https://www.googleapis.com/auth/monitoring.write,https://www.googleapis.com/auth/service.management.readonly,https://www.googleapis.com/auth/servicecontrol,https://www.googleapis.com/auth/trace.append \
    --create-disk=auto-delete=yes,boot=yes,device-name=$VM_NAME,image=projects/debian-cloud/global/images/debian-12-bookworm-v20240709,mode=rw,size=100,type=projects/moj-prod-apigee/zones/us-central1-f/diskTypes/pd-balanced \
    --no-shielded-secure-boot \
    --shielded-vtpm \
    --shielded-integrity-monitoring \
    --labels=goog-ec-src=vm_add-gcloud \
    --reservation-affinity=any \
   # --metadata-from-file startup-script=setup_ssh.sh
~~~

4- SSH to the new VM

~~~
gcloud compute ssh  --zone  $ZONE <VM instance name>
~~~

5- Run the following build script 

~~~
	#--- Install git----- 
	sudo su 
	apt-get update && apt-get install -y git
    
    #-----Install Docker----
	curl -fsSL https://get.docker.com -o get-docker.sh
	sh get-docker.sh
	systemctl start docker
	systemctl enable docker
	# -- To verify --
	docker --version 
	
	#-----Clone Repo----
	mkdir /temp
	chmod 777 -R /temp
	cd /temp
	git clone https://github.com/shawkyGalal/SmartTool.git
    
    # ----Run smarttool as a service docker composer ---- 
    cp /temp/SmartTool/smarttool.service   /etc/systemd/system/smarttool.service
    systemctl start smarttool
    systemctl enable smarttool
		
~~~

Verify step: 

~~~
sudo docker container ls 
~~~

You should get a list of 2 containers :  SmartTooDB , SmartToolApp as the below : 

CONTAINER ID   IMAGE           COMMAND                  CREATED              STATUS                          PORTS                                                                                                                                           NAMES

cc835ec90722   smarttool-app   "catalina.sh run"        About a minute ago   Up About a minute               0.0.0.0:8080->8080/tcp, :::8080->8080/tcp                                                                                                       SmartToolApp
smarttool-app
0834c7f08963   smarttool-db    "/bin/bash -c $ORACL…"   About a minute ago   Up About a minute (unhealthy)   0.0.0.0:1521->1521/tcp, :::1521->1521/tcp, 0.0.0.0:8443->8443/tcp, :::8443->8443/tcp, 0.0.0.0:27017->27017/tcp, :::27017->27017/tcp, 1522/tcp   SmartToolDB

7- Access the smarttool application from your browser : 

~~~
http://<VM-External-IP>:8080/SmartTool/Company/40/loginScreen.jsp
~~~



