package com.phonerastreador.backend.config;

import java.util.Arrays;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqttCliente implements MqttCallbackExtended {

    private static final Logger log = LoggerFactory.getLogger(MqttClient.class);

    private final String serverUri;
    private MqttClient client;
    private final MqttConnectOptions mqttOptions;

    public MqttCliente(String serverUri, String usuario, String senha) {
        this.serverUri = serverUri;

        mqttOptions = new MqttConnectOptions();
        mqttOptions.setAutomaticReconnect(true);
        mqttOptions.setCleanSession(true);
        mqttOptions.setConnectionTimeout(10);
        mqttOptions.setUserName(usuario);
        mqttOptions.setPassword(senha.toCharArray());
    }

    public IMqttToken subscribe(int qos, IMqttMessageListener gestorMensagemMQTT, String... topicos) {
        if (client == null || topicos.length == 0) {
            return null;
        }
        int tamanho = topicos.length;
        int[] qoss = new int[tamanho];
        IMqttMessageListener[] listners = new IMqttMessageListener[tamanho];

        for (int i = 0; i < tamanho; i++) {
            qoss[i] = qos;
            listners[i] = gestorMensagemMQTT;
        }

        try {
            return client.subscribeWithResponse(topicos, qoss, listners);
        } catch (MqttException ex) {
            System.out.println(String.format("Erro ao se inscrever nos tópicos %s - %s", Arrays.asList(topicos), ex));
            return null;
        }
    }

    public void unsubscribe(String... topicos) {

        if (client == null || !client.isConnected() || topicos.length == 0) {
            return;
        }
        try {
            client.unsubscribe(topicos);
        } catch (MqttException ex) {
            System.out.println(String.format("Erro ao se desinscrever no tópico %s - %s", Arrays.asList(topicos), ex));
        }
    }

    public void iniciar() {
        try {
            String clientId = MqttClient.generateClientId();
            log.info("Conectando no MQTT Broker em '{}'. ClientId: {}", serverUri, clientId);
            client = new MqttClient(serverUri, clientId,
                    new MqttDefaultFilePersistence(System.getProperty("java.io.tmpdir")));
            client.setCallback(this);
            client.connect(mqttOptions);
            log.info("Conectado");
        } catch (MqttException ex) {
            log.error("Erro ao conectar no Broker MQTT: {}", serverUri);
            ex.printStackTrace();
        }
    }

    public void finalizar() {
        if (client == null || !client.isConnected()) {
            return;
        }
        try {
            client.disconnect();
            client.close();
        } catch (MqttException ex) {
            log.error("Erro a desconectar do Broker MQTT", ex);
        }
    }

    public void publicar(String topic, byte[] payload, int qos) {
        publicar(topic, payload, qos, false);
    }

    public synchronized void publicar(String topic, byte[] payload, int qos, boolean retained) {
        try {
            if (client.isConnected()) {
                client.publish(topic, payload, qos, retained);
                log.info("Topico {} publicado.", topic);
            } else {
                log.warn("Cliente desconectado, topico nao publicado", topic);
            }
        } catch (MqttException ex) {
            log.error("Erro ao publicar {}", topic, ex);
        }
    }

    @Override
    public void connectionLost(Throwable thrwbl) {
        log.error("Conexão com o broker perdida", thrwbl);
    }

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        log.info("Cliente MQTT {} com broker {}", (reconnect ? "reconectado" : "conectado"), serverURI);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {
    }

    @Override
    public void messageArrived(String topic, MqttMessage mm) throws Exception {
    }

}
