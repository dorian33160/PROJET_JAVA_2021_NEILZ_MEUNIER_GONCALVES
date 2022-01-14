// !!!!!!! Mettre la libraire Paho MQTT pour java (org.eclipse.paho.client.mqttv3-1.2.5.jar) dans le même répertoire que vos classes de test.

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.UUID;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class capteur implements MqttCallback {

MqttClient client;

public capteur() {
}

public static void main(String[] args) {
    new capteur().doDemo();
}

public void doDemo() {
    try {
        String uri = "tcp://test.mosquitto.org:1883";
        String clientID = UUID.randomUUID().toString();
        MemoryPersistence persistence = new MemoryPersistence();
        System.out.println("*** uri = "+uri);
        System.out.println("*** UUID = "+clientID);
        client = new MqttClient(uri, clientID, persistence);

        client.connect();
        client.setCallback(this);

        MqttMessage message = new MqttMessage();
        message.setPayload("A single message from my computer".getBytes());
        System.out.println("*** msgId = "+message.getId());
        client.publish("foo", message);

        client.disconnect();
    } catch (MqttException e) {
        e.printStackTrace();
    }
}

@Override
public void connectionLost(Throwable cause) {
    cause.printStackTrace();
}

@Override
public void messageArrived(String topic, MqttMessage message) throws Exception {
    System.out.println("["+topic+"] "+message);   
}

@Override
public void deliveryComplete(IMqttDeliveryToken token) {
    System.out.println("Delivery complete...");
}

}