import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tableau{
    private static ArrayList<ArrayList<ArrayList<String>>> donnees = new ArrayList<>(); //Arraylist 3D où on stockera toutes les valeurs des capteurs
    private static ArrayList<String> capteurs = new ArrayList<>();         //Arraylist où on stockera tous les capteurs
    private String capteur, valeur;                                        //variables qui permetron de stocker les valeurs dans les tableaux 

    public Tableau(){
    }

    public void trier(String nom_capteur, String valeur){                  //Fonction qui permet de trier et stocker les informations
        this.capteur=nom_capteur;                                          //On attribu les valeurs entrantes aux variables globales
        this.valeur=valeur;
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a"); //Permet de récupérer la date
        Calendar calendar = Calendar.getInstance();
        String date= String.valueOf(format.format(calendar.getTime()));
        String[] care= {this.valeur,date};                                 //Crée un Tableau avec les valeurs reçus
        ArrayList<String> carre = new ArrayList<String>(
            Arrays.asList(care));                                          //Transforme le tableau en ArrayList
        int taille_capteurs=this.capteurs.size();                          //Calcul le nombre de capteurs exixstants
        if (taille_capteurs==0){                                           //Si il n'y aucune valeur dans le Arraylist des capteurs
            String[] infos= {"Valeur","Date"};                             //On va créer un Tableau avec les Titres 
            ArrayList<String> Infos = new ArrayList<String>(               //On transforme le tableau en ArrayList
            Arrays.asList(infos));
            ArrayList<ArrayList<String>> lignes = new ArrayList<>();       //On crée une ArrayList 2D
            lignes.add(Infos);                                             //On ajoute les Titres mis dans la ArrayList Infos
            this.donnees.add(lignes);                                      //On ajoute l'ArrayList 2D lignes à l'ArrayList 3D donnees
            String info= "Capteur";                                        //On ajoute un titre à l'ArrayList capteurs
            this.capteurs.add(info);
        }
        int emplacement = (this.capteurs.indexOf(this.capteur));           //Détecte à quel emplacement est le capteur dans lequel on veut ajouter une donnéé
        
        if (emplacement==-1){                                              //Si le capteur n'existe pas, on va créer une nouvelle ligne pour ce capteur, et on va ajouter dans la même ligne, mais cette fois ci, dans le ArrayList des données, les valeurs du capteur
            this.capteurs.add(this.capteur);
            ArrayList<ArrayList<String>> lignes = new ArrayList<>();       //On crée une Arraylist 2D pour avoir une Liste avec toutes les informations du même capteur
            lignes.add(carre);
            this.donnees.add(lignes);
            emplacement = (this.capteurs.indexOf(this.capteur));
        }
        else{                                                               //Si le capteur existe déja:
            ArrayList<ArrayList<String>> lignes = new ArrayList<>();
            lignes=this.donnees.get(emplacement);                           //On copie toutes les valeurs précedentes dans une Arrayslist 2d
            if (lignes.size()>=10){                                         //Si le ArrayList 2D fait plus de 10 valeurs:
                lignes.remove(0);                                           //On enleve la plus vieille,
                lignes.add(carre);                                          //On ajoute la nouvelle
                this.donnees.set(emplacement, lignes);                      //On copie l'Arraylist 2d dans le ArrayList des données à l'emplacement du capteur afin d'actualiser les informations
            }
            else{                                                           //Si il y a moins de 10 informations
                lignes.add(carre);                                          //On ajoute la nouvelle valeur à l'ArrayList 2D
                this.donnees.set(emplacement, lignes);                      //On copie l'Arraylist 2d dans le Arraylist 3D des données à l'emplacement du capteur afin d'actualiser les informations
            }
        }     
    }

    public String totext (String valeur){                                   //Fonction qui permet de tranformer les valeurs d'un Arraylist 2D en une seule variable de type text
        String contenu="\n";                                                //On crée une variable qui va recevoir toutes les valeurs
        int index= this.capteurs.indexOf(valeur);                           //On récupere l'emplacement du capteur qu'on veut dans le Arraylist des capteurs
        if (index==-1){                                                     //Si le résultat est de -1, il n'y a pas de capteur dans la list
            valeur="Aucune capteur trouvé à ce nom";
        }
        else{                                                               //Si on trouve le capteur dans la liste
            ArrayList<ArrayList<String>> lignes = new ArrayList<>();        //On crée une ArrayList 2d qui récupére toutes les valeurs du capteur dans le Arraylist 3D des données
            lignes=this.donnees.get(index);
            for(int i=0;i<lignes.size();i++){                               //Pour tous les valeurs qui se trouve dans la Arraylist 2d, au bon emplacement
                ArrayList<String> cube = new ArrayList<>();                 //On crée un Arraylist pour récupérer les informations
                cube=lignes.get(i);
                for(int x=0;x<cube.size();x++){                             //Et va chercher toutes les informations de cette Arraylist
                    String mot;
                    mot=cube.get(x);
                    contenu=contenu+" "+mot+ " ";                           //Et on ajoute les informations à la variable définit au départ
                }
                contenu=contenu + "\n";
            }
        }
        return contenu;                                                     //On renvoie la variable avec toutes les informations demandés
    }
    

}
