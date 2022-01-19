import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.UUID;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class centrale implements MqttCallback {
private static ArrayList<String[]> valeurs = new ArrayList<>();
MqttClient client;

public void centrale() {
}

public static void main(String[] args) {
    new centrale().doDemo();
    
}

public void stocker(String topic,String x){
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        Calendar calendar = Calendar.getInstance();
        String[] ajout= {topic,x,String.valueOf(format.format(calendar.getTime()))};
        this.valeurs.add(ajout); 
        int a= this.valeurs.size();
        if (a==10){
            afficher();
            a=0;
        }        
        }

public static void afficher(){
    for (String[] tab: valeurs) {
        for (String s: tab) {
            System.out.print(s + "\t");
        }
        System.out.println("\n");
        }
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
    if (message != null){
        String info = message.toString();
        String test= canal.toString();
        stocker(canal,info);
    }
}

@Override
public void deliveryComplete(IMqttDeliveryToken token) {
    // System.out.println("Delivery complete...");
}

}
