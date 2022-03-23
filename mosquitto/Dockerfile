FROM ubuntu:20.04

RUN apt-get update && \
    apt-get install -y software-properties-common && \
    apt-add-repository ppa:mosquitto-dev/mosquitto-ppa && \
    apt-get install -y mosquitto && \
    apt-get install -y mosquitto-clients && \
    apt-get install -y libmysqlclient-dev libmysqlclient21 && \
    apt clean

CMD ["/usr/sbin/mosquitto", "-c", "/mosquitto/config/mosquitto.conf"]
