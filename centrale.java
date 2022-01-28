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
Tableau tableau= new Tableau();
csv obj_csv = new csv();

public void centrale() {
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
        // La centrale demande aux capteurs déjà démarrés de s'annoncer à nouveau
        MqttMessage centraleEnLigne = new MqttMessage();
        String demarrage = "disponible";
        centraleEnLigne.setPayload(demarrage.getBytes());
        client.subscribe("annonce");
        client.subscribe("sauvegarde");
        client.publish("annonce", centraleEnLigne);
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
    //Récupération de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    Calendar calendar = Calendar.getInstance();
    String date = String.valueOf(format.format(calendar.getTime()));

    if (topic.toString().equals("annonce")&&!message.toString().equals("disponible")) {
        client.subscribe(message.toString());
        System.out.println(message+" OK");
    }

    if (topic.toString().equals("stop")) {
        obj_csv.creation_csv();
        System.out.println("Fermeture de la centrale");
        System.exit(0);
    }

    String canal = topic.toString().replaceAll("[^0-9]", "");
    client.publish("afficheur"+canal, message);
    String texte=this.tableau.totext(canal);
    MqttMessage historique = new MqttMessage();
    historique.setPayload(texte.getBytes());
    client.publish("historique"+canal, historique);
    if (message != null&&!topic.equals("annonce")){
        String info = message.toString();
        String test= canal.toString();
        this.tableau.trier(canal,info);
        obj_csv.objets(topic,message.toString(),date);
    }
}

@Override
public void deliveryComplete(IMqttDeliveryToken token) {}

}
