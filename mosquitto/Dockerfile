FROM eclipse-mosquitto:2.0.14

RUN apk add libldap

RUN mosquitto_passwd -b -c /mosquitto/config/pwfile owntracks 123456


CMD ["/usr/sbin/mosquitto", "-c", "/mosquitto/config/mosquitto.conf"]
