// !!!!!!! Mettre la libraire Paho MQTT pour java (org.eclipse.paho.client.mqttv3-1.2.5.jar) dans le même répertoire que vos classes de test.

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class capteur implements MqttCallback {

MqttClient client;
private MqttMessage annonce = new MqttMessage();

public static void main(String[] args) throws Exception {
    new capteur().doDemo(args);

}

public void doDemo(String[] args) throws Exception {
    try {
        String uri = "tcp://calixte.ovh:1883";
        String clientID = UUID.randomUUID().toString();
        MemoryPersistence persistence = new MemoryPersistence();
        // System.out.println("*** uri = "+uri);    // DEBUG MQTT
        // System.out.println("*** UUID = "+clientID);  // DEBUG MQTT
        client = new MqttClient(uri, clientID, persistence);
        client.connect();
        client.setCallback(this);

        // Le client s'abonne au canal d'anonce au cas où une centrale démarre après lui
        client.subscribe("annonce");

        // Le capteur s'annonce à la centrale sur le canal "annonce"
        String canalcapt = "capteur"+args[0];
        annonce.setPayload(canalcapt.getBytes());
        client.publish("annonce", annonce);
        System.out.println("[Capteur] Bonjour");

        // Une fois annoncé, il commence à envoyer ses données
        int min=-20;
        int max=40;
        int valeur = ThreadLocalRandom.current().nextInt(min, max + 1);     // Valeur aléatoire du capteur
        int proba;
        while(true) {
            System.out.println(valeur);
            String strval = Integer.toString(valeur);                       // Envoi de la valeur aléatoire
            MqttMessage message = new MqttMessage();
            message.setPayload(strval.getBytes());
            client.publish(canalcapt, message);
            proba = ThreadLocalRandom.current().nextInt(0, 100);            // Détermination de la valeur suivante
            if(proba<25) {
                valeur--;
            }
            else if(proba>=75) {
                valeur++;
            }
            TimeUnit.SECONDS.sleep(2);
        }

        // client.disconnect();
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
    if(topic.toString().equals("annonce")&&message.toString().equals("disponible")) {   // Regarde si le message reçu est l'annonce de démarrage d'une centrale
        client.publish("annonce", annonce);                                             // Si c'est le cas, le capteur s'annonce à nouveau
        System.out.println("Annonce ok");
    }
}

@Override
public void deliveryComplete(IMqttDeliveryToken token) {}

}