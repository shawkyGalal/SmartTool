
# SmartTool 
## Project Scope: 
A General Purpose Java Web based application with an Oracle Database customizable for multi purpose for example :

* Enterprise Performance Management (EPM)

* Student Information Management 



# How To Run 


You Could login with the default admin user creSmartTooldentials : 
User name 	: admin 
Password 	: admin123


## Run The SmartTool application on a Google Cloud Compute engine instance 
Consider this Application deployment on GCP will incure charges for your GCP billing account 


## (QuickStart) Setup using CloudShell

Use the following GCP CloudShell tutorial, and follow the instructions.

[![Open in Cloud Shell](https://gstatic.com/cloudssh/images/open-btn.png)](https://ssh.cloud.google.com/cloudshell/open?cloudshell_git_repo=https://github.com/shawkyGalal/apigee-samples&cloudshell_git_branch=main&cloudshell_workspace=.&cloudshell_tutorial=cloudshell/cloudshell-tutorial.md)


1 - Create new VM instance using the following gcloud command ( Update as per your prefrences  

~~~
PROJECT=moj-prod-apigee		# replace with your own value
ZONE=us-central1-a		# replace with your own value
VM_NAME=smarttool		# replace with your own value

gcloud config set project $PROJECT

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
    --tags=http-server \
    --no-shielded-secure-boot \
    --shielded-vtpm \
    --shielded-integrity-monitoring \
    --labels=goog-ec-src=vm_add-gcloud \
    --reservation-affinity=any \
   # --metadata-from-file startup-script=setup_ssh.sh
~~~

### Start the new VM
~~~
gcloud compute instances start  --zone  $ZONE  $VM_NAME
~~~

### Copy the repo to the new VM

~~~
scp -r . root@$VM_NAME:/temp
~~~

### SSH to the new VM

~~~
gcloud compute ssh  --zone  $ZONE  $VM_NAME
~~~

Accept all the defaults 

5- Run the following build script 


### Install Docker----
~~~
	sudo curl -fsSL https://get.docker.com -o get-docker.sh
	sudo sh get-docker.sh
	sudo systemctl start docker
	sudo systemctl enable docker
	# -- To verify docker installation --
	docker --version 
~~~


### Run smarttool as a service docker composer ---- 

~~~
    sudo cp /temp/SmartTool/smarttool.service   /etc/systemd/system/smarttool.service
    sudo systemctl start smarttool
    sudo systemctl enable smarttool
~~~

Note : the last comand ( systemctl start smarttool ) may take up to 15 minutes to complete 

Verify step: 

~~~
sudo docker container ls 
~~~

You should get a list of 2 containers :  SmartTooDB , SmartToolApp as the below : 

CONTAINER ID   IMAGE           COMMAND                  CREATED              STATUS                          PORTS                                                                                                                                           NAMES

cc835ec90722   smarttool-app   "catalina.sh run"        About a minute ago   Up About a minute               0.0.0.0:8080->8080/tcp, :::8080->8080/tcp                                                                                                       SmartToolApp


0834c7f08963   smarttool-db    "/bin/bash -c $ORACL…"   About a minute ago   Up About a minute (unhealthy)   0.0.0.0:1521->1521/tcp, :::1521->1521/tcp, 0.0.0.0:8443->8443/tcp, :::8443->8443/tcp, 0.0.0.0:27017->27017/tcp, :::27017->27017/tcp, 1522/tcp   SmartToolDB

7- Access the smarttool application from your browser : 

~~~
http://<VM-External-IP>:8080/SmartTool/index.jsp
~~~
Where <VM-External-IP> is the external public IP assigned to the VM , you could get it from GCP 

You Could login with the default admin user credentials : 
User name 	: admin 
Password 	: admin123

## Delete VM 
Simply login to your GCP and delete the vm to stop billing your account. 



