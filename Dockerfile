# Use an official Tomcat runtime as a parent image
FROM tomcat:9.0

# Install maven
RUN apt-get update && apt-get install -y git
RUN apt-get install -y maven
RUN apt-get install -y vim

# Set the working directory
# move the manager app to the webapp folder 
RUN cp -r /usr/local/tomcat/webapps.dist/* /usr/local/tomcat/webapps

# override default tomcat configuration  
RUN cp -r /usr/local/tomcat/conf/tomcat-users.xml  /usr/local/tomcat/conf/tomcat-users-original.xml

COPY tomcat/config/tomcat-users.xml /usr/local/tomcat/conf/
COPY tomcat/config/context.xml  	/usr/local/tomcat/webapps/manager/META-INF
COPY tomcat/config/context.xml  	/usr/local/tomcat/webapps/docs/META-INF
COPY tomcat/config/context.xml  	/usr/local/tomcat/webapps/examples/META-INF
COPY tomcat/config/context.xml  	/usr/local/tomcat/webapps/host-manager/META-INF

RUN mkdir -p /temp
WORKDIR /temp
RUN git clone https://github.com/shawkyGalal/Apigee-ResourceManager.git 
WORKDIR /temp/Apigee-ResourceManager
RUN git pull 
RUN mvn clean install -DskipTests


# COPY src/main/webapp/WEB-INF/lib/*.jar /usr/local/tomcat/lib/
RUN mkdir -p /temp/SmartTool

COPY . /temp/SmartTool
# Build ( Package )  application with maven 

WORKDIR /temp/SmartTool
RUN  mvn install:install-file -Dfile=src/main/webapp/WEB-INF/lib/side-sas.jar   -DgroupId=com.smartvalue.side  -DartifactId=sas -Dversion=1.0  -Dpackaging=jar
RUN  mvn install:install-file -Dfile=src/main/webapp/WEB-INF/lib/side-swaf.jar   -DgroupId=com.smartvalue.side  -DartifactId=swaf -Dversion=1.0  -Dpackaging=jar
RUN  mvn install:install-file -Dfile=src/main/webapp/WEB-INF/lib/bc4jhtml.jar   -DgroupId=com.smartvalue  -DartifactId=bc4jhtml -Dversion=1.0  -Dpackaging=jar
RUN  mvn install:install-file -Dfile=src/main/webapp/WEB-INF/lib/clrt.jar   -DgroupId=com.smartvalue  -DartifactId=clrt -Dversion=1.0  -Dpackaging=jar
RUN  mvn install:install-file -Dfile=src/main/webapp/WEB-INF/lib/jas.jar   -DgroupId=com.smartvalue  -DartifactId=jas -Dversion=1.0  -Dpackaging=jar
RUN  mvn install:install-file -Dfile=src/main/webapp/WEB-INF/lib/ojsp.jar   -DgroupId=com.smartvalue  -DartifactId=ojsp -Dversion=1.0  -Dpackaging=jar
RUN  mvn install:install-file -Dfile=src/main/webapp/WEB-INF/lib/ojsputil.jar   -DgroupId=com.smartvalue  -DartifactId=ojsputil -Dversion=1.0  -Dpackaging=jar
RUN  mvn install:install-file -Dfile=src/main/webapp/WEB-INF/lib/org.jboss.seam-jboss-seam-2.1.0.SP1.jar   -DgroupId=com.smartvalue  -DartifactId=org.jboss.seam-jboss-seam-2.1.0.SP1 -Dversion=1.0  -Dpackaging=jar
RUN  mvn install:install-file -Dfile=src/main/webapp/WEB-INF/lib/jboss-seam-ui-2.1.0.SP1.jar   -DgroupId=com.smartvalue  -DartifactId=jboss-seam-ui-2.1.0.SP1 -Dversion=1.0  -Dpackaging=jar
RUN  mvn install:install-file -Dfile=src/main/webapp/WEB-INF/lib/richfaces-impl-jsf2-3.3.3.Final.jar   -DgroupId=com.smartvalue  -DartifactId=richfaces-impl-jsf2-3.3.3.Final -Dversion=1.0  -Dpackaging=jar
RUN  mvn install:install-file -Dfile=src/main/webapp/WEB-INF/lib/richfaces-api-3.3.3.Final.jar   -DgroupId=com.smartvalue  -DartifactId=richfaces-api-3.3.3.Final -Dversion=1.0  -Dpackaging=jar
RUN  mvn install:install-file -Dfile=src/main/webapp/WEB-INF/lib/richfaces-ui-3.3.3.Final.jar   -DgroupId=com.smartvalue  -DartifactId=richfaces-ui-3.3.3.Final -Dversion=1.0  -Dpackaging=jar
RUN  mvn install:install-file -Dfile=src/main/webapp/WEB-INF/lib/sftp.jar   -DgroupId=com.smartvalue  -DartifactId=sftp -Dversion=1.0  -Dpackaging=jar
RUN  mvn install:install-file -Dfile=src/main/webapp/WEB-INF/lib/xmlparserv2.jar   -DgroupId=com.smartvalue  -DartifactId=xmlparserv2 -Dversion=1.0  -Dpackaging=jar
RUN  mvn install:install-file -Dfile=src/main/webapp/WEB-INF/lib/jtds-1.2.2.jar   -DgroupId=com.smartvalue  -DartifactId=jtds-1.2.2 -Dversion=1.0  -Dpackaging=jar 

# RUN mvn install -DskipTests  
RUN mvn package -DskipTests 

RUN cp -r /temp/SmartTool/target/*.war /usr/local/tomcat/webapps

