//permet de créer les capteurs sous forme d'objets

class mesures{
  public String numero;
  public String valeur;
  public String date;

  public mesures(String numero, String valeur, String date){
    this.numero=numero;
    this.valeur=valeur;
    this.date=date;
  }
  public String getNumero(){
    return numero;
  }
  public String getValeur(){
    return valeur;
  }
  public String getDate(){
    return date;
  }
}
