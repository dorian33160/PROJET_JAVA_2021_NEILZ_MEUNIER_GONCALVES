//Création du fichier CSV
import java.io.*;
import java.util.*;

public class csv
{
//Délimiteurs qui doivent être dans le fichier CSV
private static final String DELIMITER = ",";
private static final String SEPARATOR = "\n";
private static List<mesures> list_objets = new ArrayList<>(); //Permet de créer un tableau qui va stocker les objets

//Titre des colonnes fichier
private static final String HEADER = "numéro du capteur,valeur,date";

public void objets(String numero,String valeur,String date){
  //Fonction qui permet de créer l'objet mesures avec les paramètres numéro, valeur et date à partir des arguments présentes dans centrale
  mesures tmp = new mesures(numero,valeur,date);
  list_objets.add(tmp); // Ajout des objet dans le tableau
  }

  public void creation_csv() // Création du fichier CSV
  {

    FileWriter file = null;

    try
    {
      file = new FileWriter("donnees.csv"); // Création du fichier csv
      file.append(HEADER); //Ajouter l'en-tête
      file.append(SEPARATOR); //Ajouter une nouvelle ligne après l'en-tête
      Iterator it = list_objets.iterator(); // Itérer list_objets
      while(it.hasNext()) // Boucle qui permet de parcourir chaque valeur de chaque objet du tableau et de tout ajouter dans le fichier avec des virgules entre chaques.
      {
        mesures m = (mesures) it.next();
        file.append(m.getNumero());
        file.append(DELIMITER);
        file.append(m.getValeur());
        file.append(DELIMITER);
        file.append(m.getDate());
        file.append(SEPARATOR);
      }

      file.close(); // Une fois la boucle finie, le fichier se ferme.
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}
