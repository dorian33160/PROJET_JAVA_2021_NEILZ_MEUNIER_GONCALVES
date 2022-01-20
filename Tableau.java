import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tableau{
    private static ArrayList<String[]> valeurs = new ArrayList<>();
    private String capteur, valeur;
    public Tableau(){

    }
    public void stocker(String topic,String x){
        this.capteur=topic;
        this.valeur=x;
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        Calendar calendar = Calendar.getInstance();
        String[] ajout= {this.capteur,this.valeur,String.valueOf(format.format(calendar.getTime()))};
        this.valeurs.add(ajout); 
        int a= this.valeurs.size();
        if (a==10){
            System.out.println("Objet");
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
}