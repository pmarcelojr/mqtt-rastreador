echo INICIALIZANDO CLOUD
export MYSQL_HOST="168.138.148.14"
export MOSQUITTO_HOST="168.138.135.65"
ipAddr=$(hostname -I)
export SPRING_ALLOWED_ORIGINS="http://$ipAddr:4200"

ip addr | grep eth0
mvn spring-boot:run

