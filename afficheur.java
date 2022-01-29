import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.UUID;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class afficheur implements MqttCallback {

MqttClient client;
int i =0;                           //Permet de créer un timer
int test= 1;                        //Permet de lancer une commande qu'une fois

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

        String canalaff = "afficheur"+args[1];    //Permet de s'abonner au bon cannal afin de recevoir les bonnes informations
        String canalhist = "historique"+args[1];  //Permet de s'abonner au bon cannal afin de recevoir les bonnes informations

        client = new MqttClient(uri, clientID, persistence);

        client.connect();
        client.setCallback(this);
        if(args[0].equals("valeur")){   //Si le mot "valeur" est passé en parametres
            client.subscribe(canalaff); //On s'abonne au canal dédié
        }
        if(args[0].equals("historique")){ //Si le mot "historique" est passé en parametres
            client.subscribe(canalhist); //On s'abonne au canal dédié
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
    if(canal.equals("historique")&&this.test==1){       //Si on c'est abonné à l'historique, au départ:
        System.out.print("\033[H\033[2J");              //On efface le terminal
        System.out.flush();
        System.out.println("["+topic+"] "+message);     //On affiche les valeurs
        this.i=10;                                      //On lance un timer
        this.test=0;                                    //Permet de lancer la fonction qu'une fois
    }
    if (canal.equals("historique")){                    //si on c'est abbonné à l'historique
        if (this.i<=3){                                 //Permet d'afficher un decompte 
            System.out.print("Prochaines donnes dans: "+this.i);
            System.out.print("\n");
        }
        if(this.i!=0){                                   //Si le timer n'est pas à 0
            this.i=this.i-1;                             //On enleve 1 au timer
        }
        else{                                            //Si le timer est à 0
            System.out.print("\033[H\033[2J");           //On efface l'historique
            System.out.flush();
            System.out.println("["+topic+"] "+message);  // On affiche les valeurs
            this.i=10;
        }
        
    }
    if (canal.equals("afficheur")){ //Si on est abbonné à l'afficheur
        System.out.println("["+topic+"] "+message); //On affiche les valeurs en direct
    }
    
    
    
}

@Override
public void deliveryComplete(IMqttDeliveryToken token) {
    System.out.println("Delivery complete...");
}

}
