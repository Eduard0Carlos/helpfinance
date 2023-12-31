$currentLocation = Get-Location;

$dependenciesLocation = "$($currentLocation)\dependencies";

$runServerPath = "$($dependenciesLocation)\apache-tomcat-10.1.16\bin";

cd $runServerPath;
start startup.bat;

cd $currentLocation;

$cpCommand = "`"$($currentLocation)\web-api\target\classes;$($dependenciesLocation)\org\springframework\boot\spring-boot-starter-web\3.2.0-SNAPSHOT\spring-boot-starter-web-3.2.0-SNAPSHOT.jar;$($dependenciesLocation)\org\springframework\boot\spring-boot-starter\3.2.0-SNAPSHOT\spring-boot-starter-3.2.0-SNAPSHOT.jar;$($dependenciesLocation)\org\springframework\boot\spring-boot\3.2.0-SNAPSHOT\spring-boot-3.2.0-SNAPSHOT.jar;$($dependenciesLocation)\org\springframework\boot\spring-boot-autoconfigure\3.2.0-SNAPSHOT\spring-boot-autoconfigure-3.2.0-SNAPSHOT.jar;$($dependenciesLocation)\org\springframework\boot\spring-boot-starter-logging\3.2.0-SNAPSHOT\spring-boot-starter-logging-3.2.0-SNAPSHOT.jar;$($dependenciesLocation)\ch\qos\logback\logback-classic\1.4.11\logback-classic-1.4.11.jar;$($dependenciesLocation)\ch\qos\logback\logback-core\1.4.11\logback-core-1.4.11.jar;$($dependenciesLocation)\org\apache\logging\log4j\log4j-to-slf4j\2.21.1\log4j-to-slf4j-2.21.1.jar;$($dependenciesLocation)\org\apache\logging\log4j\log4j-api\2.21.1\log4j-api-2.21.1.jar;$($dependenciesLocation)\org\slf4j\jul-to-slf4j\2.0.9\jul-to-slf4j-2.0.9.jar;$($dependenciesLocation)\jakarta\annotation\jakarta.annotation-api\2.1.1\jakarta.annotation-api-2.1.1.jar;$($dependenciesLocation)\org\yaml\snakeyaml\2.2\snakeyaml-2.2.jar;$($dependenciesLocation)\org\springframework\boot\spring-boot-starter-json\3.2.0-SNAPSHOT\spring-boot-starter-json-3.2.0-SNAPSHOT.jar;$($dependenciesLocation)\com\fasterxml\jackson\core\jackson-databind\2.15.3\jackson-databind-2.15.3.jar;$($dependenciesLocation)\com\fasterxml\jackson\core\jackson-annotations\2.15.3\jackson-annotations-2.15.3.jar;$($dependenciesLocation)\com\fasterxml\jackson\core\jackson-core\2.15.3\jackson-core-2.15.3.jar;$($dependenciesLocation)\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.15.3\jackson-datatype-jdk8-2.15.3.jar;$($dependenciesLocation)\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.15.3\jackson-datatype-jsr310-2.15.3.jar;$($dependenciesLocation)\com\fasterxml\jackson\module\jackson-module-parameter-names\2.15.3\jackson-module-parameter-names-2.15.3.jar;$($dependenciesLocation)\org\springframework\boot\spring-boot-starter-tomcat\3.2.0-SNAPSHOT\spring-boot-starter-tomcat-3.2.0-SNAPSHOT.jar;$($dependenciesLocation)\org\apache\tomcat\embed\tomcat-embed-core\10.1.15\tomcat-embed-core-10.1.15.jar;$($dependenciesLocation)\org\apache\tomcat\embed\tomcat-embed-el\10.1.15\tomcat-embed-el-10.1.15.jar;$($dependenciesLocation)\org\apache\tomcat\embed\tomcat-embed-websocket\10.1.15\tomcat-embed-websocket-10.1.15.jar;$($dependenciesLocation)\org\springframework\spring-web\6.1.0\spring-web-6.1.0.jar;$($dependenciesLocation)\org\springframework\spring-beans\6.1.0\spring-beans-6.1.0.jar;$($dependenciesLocation)\io\micrometer\micrometer-observation\1.12.0\micrometer-observation-1.12.0.jar;$($dependenciesLocation)\io\micrometer\micrometer-commons\1.12.0\micrometer-commons-1.12.0.jar;$($dependenciesLocation)\org\springframework\spring-webmvc\6.1.0\spring-webmvc-6.1.0.jar;$($dependenciesLocation)\org\springframework\spring-aop\6.1.0\spring-aop-6.1.0.jar;$($dependenciesLocation)\org\springframework\spring-context\6.1.0\spring-context-6.1.0.jar;$($dependenciesLocation)\org\springframework\spring-expression\6.1.0\spring-expression-6.1.0.jar;$($dependenciesLocation)\com\oracle\database\jdbc\ojdbc8\23.3.0.23.09\ojdbc8-23.3.0.23.09.jar;$($dependenciesLocation)\org\slf4j\slf4j-api\2.0.9\slf4j-api-2.0.9.jar;$($dependenciesLocation)\org\springframework\spring-core\6.1.0\spring-core-6.1.0.jar;$($dependenciesLocation)\org\springframework\spring-jcl\6.1.0\spring-jcl-6.1.0.jar`"";

$webApiRunArguments = "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=localhost:59846 -XX:+ShowCodeDetailsInExceptionMessages -cp $($cpCommand) com.helpfinance.api.App"

Start-Process "java" -ArgumentList "$($webApiRunArguments)";

Start-Sleep -Seconds 5

Start-Process -FilePath "http://localhost:8080/api/v1/users";

Start-Sleep -Seconds 2

Start-Process -FilePath "http://localhost:8081/home";

Start-Sleep -Seconds 2

$popupMessage ="Ambientes inicializados: `n`nlocalhost:8081 - FrontEnd em JSP`nlocalhost:8080 - Web API";
$wshell = New-Object -ComObject Wscript.Shell
$answer = $wshell.Popup("$($popupMessage)",0,"Info",64)