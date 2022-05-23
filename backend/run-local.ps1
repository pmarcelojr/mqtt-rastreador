echo INICIALIZANDO BACKEND
$ENV:MYSQL_HOST="localhost"
$ENV:MOSQUITTO_HOST="localhost"

mvn spring-boot:run
