# Process arguments
PROJECT="moj-prod-apigee"
VM_NAME="smarttool"
ZONE="us-central1-c"
STORAGE_SIZE=100


while [[ $# -gt 0 ]]; do
  key="$1"
  case $key in
    --VM_NAME)
      VM_NAME="$2"
      shift # past argument
      shift # past value
      ;;
    --ZONE)
      ZONE="$2"
      shift # past argument
      shift # past value
      ;;
    --PROJECT)
      PROJECT="$2"
      shift # past argument
      shift # past value
      ;;
    --STORAGE_SIZE)
      STORAGE_SIZE="$2"
      shift # past argument
      shift # past value
      ;;
  esac
done

build_vm "$VM_NAME" 
start_vm "$VM_NAME"
install_smarttool "$VM_NAME"



build_vm()
{
local VM_NAME="$1"
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
}    

start_vm() {
  local VM_NAME="$1"
  # Use gcloud compute ssh to execute commands on the VM (secure)
  	gcloud compute instances start $VM_NAME --zone $ZONE 
	if [ $? -eq 0 ]; then
	   echo "vm started Successfully "
	else
	   echo "Error: Failed to start  VM '$VM_NAME'."
	   exit 1
	fi
} 


install_smarttool() {
  local VM_NAME="$1"
  # Use gcloud compute ssh to execute commands on the VM (secure)
  	gcloud compute ssh "$VM_NAME" --zone $ZONE  << EOF
  	#--- Install git----- 
        sudo su 
    	apt-get update && apt-get install -y git
        curl -fsSL https://get.docker.com -o get-docker.sh
    
    #-----Install Docker----
        sh get-docker.sh
        systemctl start docker
        systemctl enable docker
	
	#-----Clone Repo----
        sudo mkdir /temp
  		sudo chmod 777 -R /temp
  		cd /temp
    	sudo git clone https://github.com/shawkyGalal/SmartTool.git
    
    # ----Run smarttool as a service ---- 
		gcloud compute ssh "$vm_name" << EOF
	  	sudo cp /temp/SmartTool/smarttool.service   /etc/systemd/system/smarttool.service
	  	sudo systemctl start smarttool
		sudo systemctl enable smarttool
  
	EOF
  if [ $? -eq 0 ]; then
    echo "smarttool install Completed successfully on "
  else
    echo "Error: Failed to install smarttool on VM '$VM_NAME'."
    exit 1
  fi
}



run_composer() {
  local vm_name="$1"

  # Use gcloud compute ssh to execute commands on the VM (secure)
  	gcloud compute ssh "$vm_name" << EOF
  		cd /temp/SmartTool
		sudo docker compose up 
	EOF
 if [ $? -eq 0 ]; then
    echo "docker compose Completed successfully on "
  else
    echo "Error: Failed to run docker compose on VM '$name'."
    exit 1
  fi
}


run_smarttool_as_service ()
{
	local vm_name="$1"

  # Use gcloud compute ssh to execute commands on the VM (secure)
  	gcloud compute ssh "$vm_name" << EOF
  		sudo cp ./smarttool.service   /etc/systemd/system/smarttool.service
  		sudo systemctl start smarttool
		sudo systemctl enable smarttool
		 
	EOF
 if [ $? -eq 0 ]; then
    echo "docker compose Completed successfully on "
  else
    echo "Error: Failed to run docker compose on VM '$name'."
    exit 1
  fi
  
}
 

# Function to get VM IP address
get_vm_ip() {
  local name="<span class="math-inline">1"
local ip\_address\=</span>(gcloud compute instances describe "$name" \
    --zone "$ZONE" | grep -E 'EXTERNAL_IP' | awk '{print $2}')

  if [ -n "$ip_address" ]; then
    echo "VM '$name' IP address: $ip_address"
    export VM_IP="$ip_address"  # Store IP for later use (not strictly necessary)
  else
    echo "Error: Could not retrieve IP address for VM '$name'."
    exit 1
  fi
}


allocate_static_ip()
{ 
#=== Allocate a static External IP ===========
	NETWORK_TIER="STANDARD"
	ACCESS_CONFIG_NAME="external-nat"
	
	EXTERNAL_IP=$(gcloud compute addresses create smarttool-external-ip \
	  --network-tier ${NETWORK_TIER} \
	  --format='value(address)')
	
	
	# Associate the IP with the VM instance
	gcloud compute instances add-access-config ${VM_NAME} \
	  --access-config-name ${ACCESS_CONFIG_NAME} \
	  --address ${EXTERNAL_IP} \
	  --zone ${ZONE}
 
}



# Function to check if gcloud is installed
check_gcloud() {
  if ! command -v gcloud &> /dev/null; then
    echo "Error: gcloud is not installed. Please install gcloud and try again."
    exit 1
  fi
}
 
 
generate_ssh-keypair()
{
#===== IN case running the script outside GCP cloud shell , To Allow SSH Access to the VM from Local Machine =========

# 1- Generate ssh key pair 
if [ ! -f ~/.ssh/id_rsa ]; then
  echo "Generating SSH key pair..."
  ssh-keygen -t rsa -b 4096 -f id_rsa -N ""
fi 

# 2- Read public key content
PUBLIC_KEY=$(cat ~/.ssh/id_rsa.pub)

# Create setup script including the public key value that will be passed as a startup script upon creating the VM  
cat << EOF > setup_ssh.sh
#!/bin/bash
# Ensure SSH directory exists
mkdir -p ~/.ssh
# Set directory permissions
chmod 700 ~/.ssh
# Add public key to authorized_keys
echo "$PUBLIC_KEY" >> ~/.ssh/authorized_keys
# Set file permissions
chmod 600 ~/.ssh/authorized_keys
EOF
#===== End to Allow SSH Access to the VM from Local Machine =========
}


