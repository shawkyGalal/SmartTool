# Generate an RSA key for the CA:
domain=smart-value.online
cn=smarttool
validity=365

mkdir /etc/apigee/Certificates/Takamul_CA/$cn.$domain
cd /etc/apigee/Certificates/Takamul_CA/$cn.$domain 

openssl genrsa -out ${cn}.${domain}.key 2048


# Generate a CSR:

openssl req -new -key ${cn}.${domain}.key -out ${cn}.${domain}.csr -days validity


cd ..

############# Now let's play the CA part.################

### Generate a key for the subject. It is the same as we did for our subject.

openssl genrsa -out ca.${domain}.key 2048

### Generate a self signed certificate for the CA:
openssl req -new -x509 -key ca.${domain}.key -out ca.${domain}.crt -days 1825


### Signing -- One very easy way to sign a certificate is this:
$ openssl x509 -req -in ${cn}.${domain}.csr -CA ca.${domain}.crt -CAkey ca.${domain}.key -CAcreateserial -out ${cn}.${domain}.crt -days $validity

# Verify the certificate's content
openssl x509 -in ${cn}.${domain}.crt -text -noout

### Generate a pfx 
openssl pkcs12 -export -out ${cn}.${domain}.pfx -inkey ${cn}.${domain}.key -in ${cn}.${domain}.crt

### Verify pfx
openssl pkcs12 -info -in ${cn}.${domain}.pfx -passin pass:<your_password>


##### To Add Cert To Trust Store ######
https://gist.github.com/kekru/deabd57f0605ed95d5c8246d18483687

Copy the .crt file to /etc/pki/ca-trust/source/anchors on your CentOS machine
run :
update-ca-trust extract

or 

Update /etc/pki/java/cacerts file 
with keytool command to add your certificate : 
keytool -importcert -file "/opt/apigee/customer/application/certificates/2023/moj-cert.pem"  -keystore /etc/pki/java/cacerts -storepass changeit

test it with wget https://thewebsite.org
