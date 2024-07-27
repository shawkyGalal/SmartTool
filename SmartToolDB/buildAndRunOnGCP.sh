#!/bin/bash

# Function to check if gcloud is installed
check_gcloud() {
  if ! command -v gcloud &> /dev/null; then
    echo "Error: gcloud is not installed. Please install gcloud and try again."
    exit 1
  fi
}

# Function to create a VM
create_vm() {
  local name="$1"
  local machine_type="$2"
  local zone="$3"

  # Use Application Default Credentials for authentication (avoid storing credentials)
  gcloud compute instances create "$name" \
    --machine-type "$machine_type" \
    --zone "$zone" \
    --boot-disk-size 10GB \
    --quiet &> /dev/null

  if [ $? -eq 0 ]; then
    echo "VM '$name' created successfully."
  else
    echo "Error: Failed to create VM '$name'."
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

# Function to install Docker on VM
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

# Function to copy Dockerfile folder to VM
copy_dockerfile_folder() {
  local folder_path="$1"
  local vm_name="$2"

  # Use gcloud compute scp for secure file transfer within the project
  gcloud compute scp --recurse "$folder_path" "<span class="math-inline">USER@</span>{vm_name}:~/dockerfile_folder"

  if [ $? -eq 0 ]; then
    echo "Dockerfile folder copied to VM '$name'."
  else
    echo "Error: Failed to copy Dockerfile folder to VM '$name'."
    exit 1
  fi
}

# Function to build and run Dockerfile on VM
build_and_run_dockerfile() {
  local vm_name="$1"
  local image_name="$2"
  local container_name="$3"

  # Use gcloud compute ssh to execute commands on the VM
  gcloud compute ssh "$vm_name" << EOF
    cd ~/dockerfile_folder
    docker build -t "$image_name" .
    docker run -d --name "$container_name" "$image_name"
EOF

  if [ $? -eq 0 ]; then
    echo "Docker image '$image_name' built and container '$container_name' running on VM '$name'."
  else
    echo "Error: Failed to build or run Dockerfile on VM '$name'."
    exit 1
  fi
}

# Process arguments
VM_NAME=""
MACHINE_TYPE=""
ZONE=""
IMAGE_NAME=""
CONTAINER_NAME=""

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
    --IMAGE_NAME)
      IMAGE_NAME="$2"
      shift # past argument
      shift # past value
      ;;
    --CONTAINER_NAME)
      CONTAINER_NAME="$2"
      shift # past argument
      shift # past value
      ;;
  esac
done

# Function calls
# (Function definitions are omitted for brevity)
check_gcloud
create_vm "$VM_NAME" "$MACHINE_TYPE" "$ZONE"
install_docker "$VM_NAME" 
copy_dockerfile_folder "/SmartToolDB" "$VM_NAME" 
# ... (other function calls as needed)
build_and_run_dockerfile "$VM_NAME" "$IMAGE_NAME" "$CONTAINER_NAME"