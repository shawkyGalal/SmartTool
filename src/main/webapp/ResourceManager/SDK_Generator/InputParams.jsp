<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="sdk/moj_sdk" method="post">

<br><br>Sepecification URL : <select name = "specsUrl" id = "specsUrl"> 
	<option id = "1"  value = "https://api.moj.gov.local/v1/najiz-services/portal/openapi.json" >https://api.moj.gov.local/v1/najiz-services/portal/openapi.json</option>
	<option id = "2" value = "https://raw.githubusercontent.com/openapitools/openapi-generator/master/modules/openapi-generator/src/test/resources/3_0/petstore.json" >https://raw.githubusercontent.com/openapitools/openapi-generator/master/modules/openapi-generator/src/test/resources/3_0/petstore.json</option>
	<option id = "2" value = "https://api-test.moj.gov.local/v1/nic-scj-sms/openapi.json">https://api-test.moj.gov.local/v1/nic-scj-sms/openapi.json</option>
	
</select>

<br><br>Required Language <select name = "lang" id = "lang">
	<option id = "1" value =  "java" >java</option>
	<option id = "2"  value =  "php">php</option>
	<option id = "3"  value =  "ada">ada</option>
	<option id = "4"  value =  "apex">apex</option>
	<option id = "5"  value =  "cpp-tiny">cpp-tiny</option>
	<option id = "6"  value =  "csharp">csharp</option>
	<option id = "7"  value =  "dart">dart</option>
	
	<option id = "8"  value =  "go">go</option>
	<option id = "9"  value =  "graphql-nodejs-express-server">graphql-nodejs-express-server</option>
	<option id = "10"  value =  "python">python</option>
	<option id = "11"  value =  "rupy">rupy</option>
	<option id = "12"  value =  "rust">rust</option>
	
</select>
<!--  Available Languages -----
ada
ada-server
android
apache2
apex
asciidoc
aspnetcore
avro-schema
bash
crystal
c
clojure
cwiki
cpp-qt-client
cpp-qt-qhttpengine-server
cpp-pistache-server
cpp-restbed-server
cpp-restbed-server-deprecated
cpp-restsdk
cpp-tiny
cpp-tizen
cpp-ue4
csharp
csharp-functions
dart
dart-dio
eiffel
elixir
elm
erlang-client
erlang-proper
erlang-server
fsharp-functions
fsharp-giraffe-server
go
go-echo-server
go-server
go-gin-server
graphql-schema
graphql-nodejs-express-server
groovy
kotlin
kotlin-server
kotlin-spring
kotlin-vertx
ktorm-schema
haskell-http-client
haskell
haskell-yesod
java
jaxrs-cxf-client
java-helidon-client
java-helidon-server
java-inflector
java-micronaut-client
java-micronaut-server
java-msf4j
java-pkmst
java-play-framework
java-undertow-server
java-vertx
java-vertx-web
java-camel
jaxrs-cxf
jaxrs-cxf-extended
jaxrs-cxf-cdi
jaxrs-jersey
jaxrs-resteasy
jaxrs-resteasy-eap
jaxrs-spec
javascript
javascript-apollo-deprecated
javascript-flowtyped
javascript-closure-angular
jetbrains-http-client
jmeter
julia-client
julia-server
k6
lua
markdown
mysql-schema
n4js
nim
nodejs-express-server
objc
ocaml
openapi
openapi-yaml
plantuml
perl
php
php-nextgen
php-laravel
php-lumen
php-slim4
php-symfony
php-mezzio-ph
php-dt
postman-collection
powershell
protobuf-schema
python
python-fastapi
python-flask
python-aiohttp
python-blueplanet
r
ruby
ruby-on-rails
ruby-sinatra
rust
rust-server
scalatra
scala-akka
scala-akka-http-server
scala-finch
scala-gatling
scala-lagom-server
scala-play-server
scala-sttp
scala-sttp4
scalaz
spring
dynamic-html
html
html2
swift5
swift-combine
typescript
typescript-angular
typescript-aurelia
typescript-axios
typescript-fetch
typescript-inversify
typescript-jquery
typescript-nestjs
typescript-node
typescript-redux-query
typescript-rxjs
wsdl-schema
xojo-client
zapier
 -->
<br><br>Package Name (hidden): <input type="text" name = "packageName" id = "packageName" value = "org.moj.najiz.sdk">

<br><br>outputDirectory (hidden) :  <input type="text" name = "outputDirectory" id = "outputDirectory" value = "C:\Users\sfoda\Documents\GitHub\MOJ_SDK\src">
<br><br> Validate Specification ? <input type="checkbox" name="validateSpecs" id = "validateSpecs" > 
<br><br><input type="submit" name = "submit">
</form>
</body>
</html>