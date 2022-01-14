public class Choix_capteurs(){
    private int choix=0;
    private int choix_capteurs1=0;
    private int choix_capteurs2=0;
    private int choix_capteurs3=0;

    public void Choix_capteurs(){

    }

    public void choix_capteurs(){

		while (choix==0) {
            this.choix_capteurs1=0;
            this.choix_capteurs2=0;
            this.choix_capteurs3=0;
            System.out.println("Quel capteurs vous voulez prendre en compte?"); // liste à récupérer de la bdd
            System.out.println("1: Capteur 1");
            System.out.println("2: Capteur 2");
            System.out.println("3: Tous");
            boolean vrai=true;
            while(vrai) {
                try {
                    choix = Integer.parseInt(Lecteur.readLine());
                    vrai=false;
                } catch (NumberFormatException | IOException e) {
                    System.out.println("Mauvais saisie");
                    this.choix_capteurs();
                }
                }
            if (choix==1) {
                this.choix_capteurs1=1;
            }
            else if (choix==2) {
                this.choix_capteurs2=1;
            }
            else if (choix==3) {
                this.choix_capteurs3=1;
            }
        }
    }
    
    public void autres(){
        while (choix==0) {
            System.out.println("Voulez vous en choisir un autre?"); // liste à récupérer de la bdd
            System.out.println("1: Oui");
            System.out.println("2: Non");
            boolean vrai=true;
            while(vrai) {
                try {
                    choix = Integer.parseInt(Lecteur.readLine());
                    vrai=false;
                } catch (NumberFormatException | IOException e) {
                    System.out.println("Mauvais saisie");
                    this.autres();
                }
                }
            if (choix==1) {
                this.choix_capteurs();
            }
            else if (choix==2) {
                
            }
            else{
                System.out.println("Mauvais saisie");
                    this.autres();
            }
        }
    }

    public int getchoix_capteurs2(){
        return this.choix_capteurs2;
    }

    public int getchoix_capteurs2(){
        return this.choix_capteurs2;
    }

    public int getchoix_capteurs3(){
        return this.choix_capteurs3;
    }

    public void setchoix_capteurs1(int set1){
        this.choix_capteurs1 = set1;    
    }

    public void setchoix_capteurs2(int set2){
        this.choix_capteurs2 = set2;    
    }

    public void setchoix_capteurs3(int set3){
        choix_capteurs3 = set3;    
    }
}