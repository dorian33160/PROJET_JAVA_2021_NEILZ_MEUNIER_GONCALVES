import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tableau{
    private static ArrayList<String[]> valeurs = new ArrayList<>();
    private static ArrayList<ArrayList<ArrayList<String>>> donnees = new ArrayList<>();
    private static ArrayList<String> capteurs = new ArrayList<>();
    private String capteur, valeur;

    private DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    private Calendar calendar = Calendar.getInstance();
    private String date= String.valueOf(format.format(calendar.getTime()));


    public Tableau(){
    }


    public void stocker(String nom_capteur,String valeur){
        this.capteur=nom_capteur;
        this.valeur=valeur;
        String[] carre= {this.capteur,this.valeur,this.date};
        this.valeurs.add(carre);

        }


    public void trier(String nom_capteur, String valeur){
        this.capteur=nom_capteur;
        this.valeur=valeur;
        String[] care= {this.valeur,this.date};
        ArrayList<String> carre = new ArrayList<String>(
            Arrays.asList(care));
        int taille_capteurs=this.capteurs.size();
        if (taille_capteurs==0){
            String[] infos= {"Valeur","Date"};
            ArrayList<String> Infos = new ArrayList<String>(
            Arrays.asList(infos));
            ArrayList<ArrayList<String>> lignes = new ArrayList<>();
            lignes.add(Infos);
            this.donnees.add(lignes);
            String info= "Capteur";
            this.capteurs.add(info);
        }
        int emplacement = (this.capteurs.indexOf(this.capteur));
        
        if (emplacement==-1){
            this.capteurs.add(this.capteur);
            ArrayList<ArrayList<String>> lignes = new ArrayList<>();
            lignes.add(carre);
            this.donnees.add(lignes);
            emplacement = (this.capteurs.indexOf(this.capteur));
            System.out.println("donnees emplacement" + this.donnees.get(emplacement));
            System.out.println("Capteur"+this.capteurs);
            System.out.println("Donnees"+this.donnees);
        }
        else{
            ArrayList<ArrayList<String>> lignes = new ArrayList<>();
            lignes=this.donnees.get(emplacement);
            lignes.add(carre);
            this.donnees.set(emplacement, lignes);
            System.out.println("donnees emplacement" + this.donnees.get(emplacement));
            System.out.println("Capteur"+this.capteurs);
            System.out.println("Donnees"+this.donnees);
        }
        System.out.println("");
        System.out.println("");


        

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
