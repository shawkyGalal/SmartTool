# Use the base image
FROM gvenzl/oracle-free

# Copy additional setup scripts to the container
COPY ./setup-scripts /opt/oracle/setup-scripts

# Run setup scripts
# RUN echo "Current directory:" && pwd && \
#    echo "Contents of /opt/oracle/setup-scripts:" && ls -l /opt/oracle/setup-scripts && \
#    echo "Executing chmod command" && \
#    chmod +x /opt/oracle/setup-scripts/*.sql
	
# RUN chmod +x /opt/oracle/setup-scripts/setup.sh
RUN /opt/oracle/setup-scripts/setup.sh
