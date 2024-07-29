#!/bin/bash

# Function to check if gcloud is installed
check_gcloud() {
  if ! command -v gcloud &> /dev/null; then
    echo "Error: gcloud is not installed. Please install gcloud and try again."
    exit 1
  fi
}

# Function to delete a VM
delete_vm() {
  local name="$1"

  # Use gcloud compute instances delete in a quiet mode to suppress output
  gcloud compute instances delete "$name" --quiet &> /dev/null

  if [ $? -eq 0 ]; then
    echo "VM '$name' deleted successfully."
  else
    echo "Error: Failed to delete VM '$name'."
    exit 1
  fi
}

# Read VM name from argument (or prompt for input if not provided)
vm_name=""
if [ $# -eq 1 ]; then
  vm_name="$1"
else
  read -p "Enter the name of the VM to delete: " vm_name
fi

# Check if gcloud is installed
check_gcloud

# Delete the VM
delete_vm "$vm_name"
