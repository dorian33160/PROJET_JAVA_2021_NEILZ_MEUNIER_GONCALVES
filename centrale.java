import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.UUID;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class centrale implements MqttCallback {

MqttClient client;

public centrale() {
}

public static void main(String[] args) {
    new centrale().doDemo();
}

public void doDemo() {
    try {
        String uri = "tcp://calixte.ovh:1883";
        String clientID = UUID.randomUUID().toString();
        MemoryPersistence persistence = new MemoryPersistence();
        System.out.println("*** uri = "+uri);
        System.out.println("*** UUID = "+clientID);
        client = new MqttClient(uri, clientID, persistence);

        client.connect();
        client.setCallback(this);
        client.subscribe("capteur1");
        client.subscribe("capteur2");
        client.subscribe("capteur3");
        client.subscribe("capteur4");
        client.subscribe("capteur5");
        client.subscribe("capteur6");
        client.subscribe("capteur7");
        client.subscribe("capteur8");
        client.subscribe("capteur9");
        client.subscribe("capteur10");
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
    //message.setPayload("A single message from my computer".getBytes());
    // System.out.println("*** msgId = "+message.getId());
    String canal = topic.substring(topic.length() - 1);
    client.publish("afficheur"+canal, message);
}

@Override
public void deliveryComplete(IMqttDeliveryToken token) {
    // System.out.println("Delivery complete...");
}

}
