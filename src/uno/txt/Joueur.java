package uno.txt;

import java.util.ArrayList;

public class Joueur {

	private ArrayList<Carte> main = new ArrayList<Carte>();

	ArrayList<Carte> getMain(){
		return main;
	}

	void piocherCarte() {
		if (Uno.pioche.isEmpty()) {
			Uno.initCartes();
		}
		this.getMain().add(Uno.pioche.remove(0));
	}

	void jouerCarte() {
		// Piocher cartes +2 et +4
		Carte carteDessus = Uno.defausse.get(Uno.defausse.size()-1);
		if (carteDessus.getSymbole()==Symbole.PLUSDEUX||
				carteDessus.getSymbole()==Symbole.PLUSQUATRE && Uno.nbpioche != 0){
			System.out.println("\nVous piochez à cause de la derniere carte jouée !");
			Uno.nbpioche = 0;
			if(carteDessus.getSymbole()==Symbole.PLUSDEUX){
				for(int i=0;i<2;i++)this.piocherCarte();
			}else{
				for(int i=0;i<4;i++)this.piocherCarte();
				
			}
			this.printMain();
		}
		// Jouer normalement
		else{
			// Affichage deck
			for (int i = 0; i < this.getMain().size(); i++) {
				if (i == 0) System.out.print(Couleur.RESET+"    ");
				System.out.print(i+"         ");
			}
			System.out.println("\nChoisissez une carte à jouer et appuyez sur entrée pour jouer.");
			// Selection carte
			boolean action=false;
			String input;
			int inputInt=0;
			do{
				input = Utils.getStringInput();
				// Pioche
				if (input.toLowerCase().contentEquals("p")) {
					this.piocherCarte();
					action=true;
					return;
				// Sélectionner carte
				} else {
					inputInt = Integer.parseInt(input);
					action=true;
					if(inputInt >= this.main.size() || inputInt < 0 || !this.getMain().get(inputInt).empilable(Uno.defausse.get(Uno.defausse.size()-1)) ) {
						System.out.println("Carte non plaçable");
						action=false;
					}
				}
			}while(!action);
			// Actions des cartes
			Symbole symboleJoue = this.main.get(inputInt).getSymbole();
			if(symboleJoue == Symbole.SENS) {
				Uno.updateCouleurDefausse();
				Uno.changementSens();
			} else if (symboleJoue == Symbole.PASSE) {
				Uno.updateCouleurDefausse();
				Uno.finishPlayerTour();
			} else {
				Uno.updateCouleurDefausse();
			}
			// Ajouter la carte a la defausse
			Uno.defausse.add(this.main.remove(Integer.parseInt(input)));
			if(Uno.defausse.get(Uno.defausse.size()-1).getSymbole().equals(Symbole.PLUSQUATRE)){
				Uno.nbpioche = 4;
			}
			if(Uno.defausse.get(Uno.defausse.size()-1).getSymbole().equals(Symbole.PLUSDEUX)){
				Uno.nbpioche = 2;
			}
			if (symboleJoue == Symbole.CHANGECOULEUR || symboleJoue == Symbole.PLUSQUATRE) {
				demanderCouleur();
			}
		}
	}

	public void demanderCouleur() {
		String prompt="0";
		do {
			System.out.println("Quelle couleur souhaitez vous ? \n"
					+ "1. Vert \n"
					+ "2. Rouge \n"
					+ "3. Jaune \n"
					+ "4. Bleu \n votre choix : ");
			prompt = Utils.getStringInput();
		}
		while (!prompt.equals("1") && !prompt.equals("2") && !prompt.equals("3") && !prompt.equals("4"));
		Carte c = Uno.defausse.get(Uno.defausse.size()-1);
		Uno.defausse.remove(Uno.defausse.size()-1);
		Uno.defausse.add(new Carte(c.getSymbole(), Couleur.getCouleur(Integer.parseInt(prompt)-1)));
	}

	public void printMain() {
		for (int s = 0; s < 8; s++) {
			for (Carte c : main) {
				System.out.print(c.getCouleur().getDrawColor());
				if (s < 1 || s > 6) {
					System.out.print("o-------o");
				} else {
					System.out.print(c.getSymbole().getSprite()[s-1]);
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}