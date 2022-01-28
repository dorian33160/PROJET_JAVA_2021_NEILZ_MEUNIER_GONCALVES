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

    public Tableau(){
    }


    public void stocker(String nom_capteur,String valeur){
        this.capteur=nom_capteur;
        this.valeur=valeur;
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        Calendar calendar = Calendar.getInstance();
        String date= String.valueOf(format.format(calendar.getTime()));
        String[] carre= {this.capteur,this.valeur,date};
        this.valeurs.add(carre);

        }


    public void trier(String nom_capteur, String valeur){
        this.capteur=nom_capteur;
        this.valeur=valeur;
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        Calendar calendar = Calendar.getInstance();
        String date= String.valueOf(format.format(calendar.getTime()));
        String[] care= {this.valeur,date};
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
        }
        else{
            ArrayList<ArrayList<String>> lignes = new ArrayList<>();
            lignes=this.donnees.get(emplacement);
            if (lignes.size()>=5){
                lignes.remove(0);
                lignes.add(carre);
                this.donnees.set(emplacement, lignes);
            }
            else{
                lignes.add(carre);
                this.donnees.set(emplacement, lignes);
            }
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

    public String totext (String valeur){
        String contenu="\n";
        int index= this.capteurs.indexOf(valeur);
        if (index==-1){
            valeur="Aucune capteur trouvé à ce nom";
        }
        else{
            ArrayList<ArrayList<String>> lignes = new ArrayList<>();
            lignes=this.donnees.get(index);
            for(int i=0;i<lignes.size();i++){
                ArrayList<String> cube = new ArrayList<>();
                cube=lignes.get(i);
                for(int x=0;x<cube.size();x++){
                    String mot;
                    mot=cube.get(x);
                    contenu=contenu+" "+mot+ " ";
                }
                contenu=contenu + "\n";
            }
        }
        return contenu; 
    }
    

}
