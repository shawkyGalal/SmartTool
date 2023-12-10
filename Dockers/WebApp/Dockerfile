# Use an official Tomcat runtime as a parent image
FROM tomcat:latest

# Install git
RUN apt-get update && apt-get install -y git

# Set the working directory
WORKDIR /usr/local/tomcat/webapps/

# Clone your GitHub repository
RUN git clone https://github.com/shawkyGalal/SmartTool.git .

# Build your application (if necessary)
# ...

# The rest of your Dockerfile
# ...
