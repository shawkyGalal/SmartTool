@echo off

pushd %~dp0
set THIS_DIR=%CD%

set ip_address_string="IPv4 Address"
for /f "usebackq tokens=2 delims=:" %%f in (`ipconfig ^| findstr /c:%ip_address_string%`) do set YOUR_IP_ADDRESS=%%f

echo ******************************************************************************
echo * Current directory: %THIS_DIR%
echo * Localhost IP address: %YOUR_IP_ADDRESS%
echo *  
echo * WebPivotTable use node.js to run a web server at back end.
echo *  
echo * If node.js hasn't been installed on this machine yet,
echo *    please install it from https://nodejs.org first,
echo *    then run this again.
echo *
echo * If node.js had been installed already, 
echo *    you will see "Express server listening on port 8002"
echo *  
echo * Keep this console running, 
echo *    then open a browser with url "http://localhost:8002" in this machine
echo *
echo *    Or url "http://%YOUR_IP_ADDRESS%:8002" 
echo *      if access through other machines in the same local network.
echo *
echo * If you see "Error: listen EADDRINUSE",
echo *    then some other running server in this machine use port 8002 already
echo *    please close that server and run this again.
echo *  
echo ********************************************************************************


cmd /k node app
