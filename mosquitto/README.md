# Mosquitto - Inicializando

## Conectando ao servidor
```
docker-exec -it <nome-container-ou-id> /bin/sh
```

## Criando usuario e senha
Rodar o seguinte comando:
```
mosquitto_passwd -c /mosquitto/config/pwfile owntracks
```

Informe a senha e repita.

## Criando um sub
mosquitto_sub -h 127.0.0.1 -p 1883 -v -t 'owntracks/#' -u owntracks -P <senha>

## Criando um pub
``` 
mosquitto_pub -h 127.0.0.1 -p 1883 -m "Test" -t "owntracks/hi"
``` 
