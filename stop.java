import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class stop implements MqttCallback {

MqttClient client;
private MqttMessage annonce = new MqttMessage();

public void stop(){}

public static void main(String[] args) throws Exception {
    new stop().doDemo();
}

public void doDemo() throws Exception {
    try {
        // Se connecte au broker
        String uri = "tcp://calixte.ovh:1883";
        String clientID = UUID.randomUUID().toString();
        MemoryPersistence persistence = new MemoryPersistence();
        System.out.println("Sauvegarde des donnees et extinction de la centrale en cours...");
        client = new MqttClient(uri, clientID, persistence);
        client.connect();
        client.setCallback(this);

        // Envoie un message "stop"
        MqttMessage stop = new MqttMessage();
        String off = "off";
        stop.setPayload(off.getBytes());
        client.publish("stop", stop);
        System.exit(0);
    } catch (MqttException e) {
        e.printStackTrace();
    }
}

@Override
public void connectionLost(Throwable cause) {
    cause.printStackTrace();
}

@Override
public void messageArrived(String topic, MqttMessage message) throws Exception {}

@Override
public void deliveryComplete(IMqttDeliveryToken token) {}

}