# Use an official Tomcat runtime as a parent image
FROM tomcat:latest

# Install git
RUN apt-get update && apt-get install -y git
RUN apt-get install -y maven

# Set the working directory
# move the manager app to the webapp folder 
RUN cp -r /usr/local/tomcat/webapps.dist/* /usr/local/tomcat/webapps

WORKDIR /temp
# Clone your GitHub repository
RUN git clone https://github.com/shawkyGalal/SmartTool.git 
WORKDIR /temp/SmartTool
 
# Build ( Package )  application with maven 
RUN git pull
RUN mvn package -DskipTests 
RUN cp -r /temp/SmartTool/target/*.war /usr/local/tomcat/webapps
WORKDIR /usr/local/tomcat
# ...

# The rest of your Dockerfile
# ...

