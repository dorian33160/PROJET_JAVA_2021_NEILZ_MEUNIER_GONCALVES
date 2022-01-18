import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.time.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


class tests{
        public static List<String[]> Valeurs = new ArrayList<>();
    }

public class test{
    

    public static void main(String[] args) {
        int min=-20;
        int max=40;
        int i=0;
        while (i < 50){
            int valeur = ThreadLocalRandom.current().nextInt(min, max + 1);
            String valeur1= String.valueOf(valeur);
            System.out.println(valeur);
            stocker("topic",valeur1);
            i++;
        }
        afficher();
    }
    
    public static void stocker(String topic,String x){
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        Calendar calendar = Calendar.getInstance();
        String[] ajout= {topic,x,String.valueOf(format.format(calendar.getTime()))};
        tests.Valeurs.add(ajout);        
    }

    public static void afficher(){
        for (String[] tab: tests.Valeurs) {
            for (String s: tab) {
                System.out.print(s + "\t");
            }
            System.out.println("\n");
        }
    }
}