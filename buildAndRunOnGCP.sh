# Process arguments
PROJECT="moj-prod-apigee"
VM_NAME="smarttool"
MACHINE_TYPE="e2-small"
ZONE="us-central1-c"


while [[ $# -gt 0 ]]; do
  key="$1"
  case $key in
    --VM_NAME)
      VM_NAME="$2"
      shift # past argument
      shift # past value
      ;;
    --MACHINE_TYPE)
      MACHINE_TYPE="$2"
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
  esac
done

check_gcloud

gcloud auth login --no-browser  

gcloud compute instances create ${VM_NAME} \
--project=$PROJECT \
--zone=$ZONE \
--machine-type=$MACHINE_TYPE \
--network-interface=network-tier=PREMIUM,stack-type=IPV4_ONLY,subnet=default \
--maintenance-policy=MIGRATE \
--provisioning-model=STANDARD \
--service-account=598074804327-compute@developer.gserviceaccount.com \
--scopes=https://www.googleapis.com/auth/devstorage.read_only,https://www.googleapis.com/auth/logging.write,https://www.googleapis.com/auth/monitoring.write,https://www.googleapis.com/auth/service.management.readonly,https://www.googleapis.com/auth/servicecontrol,https://www.googleapis.com/auth/trace.append --tags=http-server,https-server --create-disk=auto-delete=yes,boot=yes,device-name=smarttool,image=projects/debian-cloud/global/images/debian-11-bullseye-v20240709,mode=rw,size=64,type=projects/moj-prod-apigee/zones/us-central1-f/diskTypes/pd-balanced \
--no-shielded-secure-boot \
--shielded-vtpm \
--shielded-integrity-monitoring \
--labels=goog-ec-src=vm_add-gcloud \
--reservation-affinity=any

install_git "$VM_NAME"
install_docker "$VM_NAME" 
clone_repo "$VM_NAME"
run_composer "$VM_NAME" 


# Function to check if gcloud is installed
check_gcloud() {
  if ! command -v gcloud &> /dev/null; then
    echo "Error: gcloud is not installed. Please install gcloud and try again."
    exit 1
  fi
}


install_git() {
  local vm_name="$1"

  # Use gcloud compute ssh to execute commands on the VM (secure)
  	gcloud compute ssh "$vm_name" << EOF
    	apt-get update && apt-get install -y git
	EOF
 if [ $? -eq 0 ]; then
    echo "git install Completed successfully on "
  else
    echo "Error: Failed to install git on VM '$name'."
    exit 1
  fi
}


install_docker() {
  local vm_name="$1"

  # Use gcloud compute ssh to execute commands on the VM (secure)
  gcloud compute ssh "$vm_name" << EOF
    curl -fsSL https://get.docker.com -o get-docker.sh
    sudo sh get-docker.sh
    sudo systemctl start docker
    sudo systemctl enable docker
EOF
 if [ $? -eq 0 ]; then
    echo "Docker installed on VM '$name'."
  else
    echo "Error: Failed to install Docker on VM '$name'."
    exit 1
  fi
}


clone_repo() {
  local vm_name="$1"

  # Use gcloud compute ssh to execute commands on the VM (secure)
  	gcloud compute ssh "$vm_name" << EOF
  		cd /temp
    	git clone https://github.com/shawkyGalal/SmartTool.git
	EOF
 if [ $? -eq 0 ]; then
    echo "smarttool repo cloned Completed successfully on "
  else
    echo "Error: Failed to clone smarttool repo to VM  '$name'."
    exit 1
  fi
}

run_composer() {
  local vm_name="$1"

  # Use gcloud compute ssh to execute commands on the VM (secure)
  	gcloud compute ssh "$vm_name" << EOF
  		cd /temp/SmartTool
		Startup.bat 
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



