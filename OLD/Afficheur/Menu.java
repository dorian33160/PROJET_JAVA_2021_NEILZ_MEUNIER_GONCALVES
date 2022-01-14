public class Menus {

    public Menus(){

    }
    public void MenuPrincipal() {
		int choix=0;
		while (choix==0) {
		System.out.println("Que voulez vous faire? Veuillez entrer un chiffre");
		System.out.println("1: Afficher les données en temps réel");
		System.out.println("2: ");
		System.out.println("3: ");
		System.out.println("4: ");
		System.out.println("5: ");
		boolean vrai=true;
		while(vrai) {
		try {
			choix = Integer.parseInt(Lecteur.readLine());
			vrai=false;
		} catch (NumberFormatException | IOException e) {
			System.out.println("Mauvaise saisie, réessayez");
			this.MenuPrincipal();
		}
		}
		if (choix==1) {
            Choix_capteurs choix_capteurs = new Choix_capteurs();
            choix_capteurs.choix_capteurs(); 
		}
		else if (choix==2) {
	
		}
		else if (choix==3) {

		}
		else if (choix==4) {

		}
		else if (choix==5) {

		}
		else if(choix>5) {
		this.MenuPrincipal();
        }
	}

    
}