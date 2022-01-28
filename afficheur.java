import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.UUID;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class afficheur implements MqttCallback {

MqttClient client;
int i =0;
int test= 1; 

public afficheur() {
}

public static void main(String[] args) {
    new afficheur().doDemo(args);
}

public void doDemo(String[] args) {
    try {
        String uri = "tcp://calixte.ovh:1883";
        String clientID = UUID.randomUUID().toString();
        MemoryPersistence persistence = new MemoryPersistence();
        System.out.println("*** uri = "+uri);
        System.out.println("*** UUID = "+clientID);
        System.out.println("*** Nom Afficheur = "+args[1]);

        String canalaff = "afficheur"+args[1];
        String canalhist = "historique"+args[1];

        client = new MqttClient(uri, clientID, persistence);

        client.connect();
        client.setCallback(this);
        if(args[0].equals("valeur")){
            client.subscribe(canalaff);
        }
        if(args[0].equals("historique")){
            client.subscribe(canalhist);
        }
        
        
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
    String canal = topic.toString().replaceAll("[0-9]", "");
    if(canal.equals("historique")&&this.test==1){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("["+topic+"] "+message);
        this.i=10;
        this.test=0;
    }
    if (canal.equals("historique")){
        if (this.i<=3){
            System.out.print("Prochaines donnes dans: "+this.i);
            System.out.print("\n");
        }
        if(this.i!=0){
            this.i=this.i-1;
        }
        else{
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("["+topic+"] "+message);
            this.i=10;
        }
        
    }
    if (canal.equals("afficheur")){
        System.out.println("["+topic+"] "+message);
    }
    
    
    
}

@Override
public void deliveryComplete(IMqttDeliveryToken token) {
    System.out.println("Delivery complete...");
}

}
