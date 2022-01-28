//Création du fichier CSV
import java.io.*;
import java.util.*;

public class csv
{
//Délimiteurs qui doivent être dans le fichier CSV
private static final String DELIMITER = ",";
private static final String SEPARATOR = "\n";
private static List<mesures> list_objets = new ArrayList<>();

//En-tête de fichier
private static final String HEADER = "numéro du capteur,valeur,date";

public void objets(String numero,String valeur,String date){
  //Fonction qui permet de créer l'objet mesures avec les paramètres numéro / valeur et date à partir des arguments présentes dans centrale
  mesures tmp = new mesures(numero,valeur,date);
  list_objets.add(tmp);
  }

  public void creation_csv()
  {

    FileWriter file = null;

    try
    {
      file = new FileWriter("donnees.csv");
      //Ajouter l'en-tête
      file.append(HEADER);
      //Ajouter une nouvelle ligne après l'en-tête
      file.append(SEPARATOR);
      //Itérer bookList
      Iterator it = list_objets.iterator();
      while(it.hasNext())
      {
        mesures m = (mesures) it.next();
        file.append(m.getNumero());
        file.append(DELIMITER);
        file.append(m.getValeur());
        file.append(DELIMITER);
        file.append(m.getDate());
        file.append(SEPARATOR);
      }

      file.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}