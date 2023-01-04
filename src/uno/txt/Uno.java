package uno.txt;

import uno.txt.Config.Config;
import uno.txt.Config.Options;

import java.util.ArrayList;
import java.util.Collections;

public class Uno {
	
	// Variables
	
	static ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	static boolean clockwise = true;
	static int tour = 0;
	static Couleur couleurDefausse = Couleur.BLEU;
	static int aPiocher = 0;
	
	static ArrayList<Carte> pioche = new ArrayList<Carte>();
	static ArrayList<Carte> defausse = new ArrayList<Carte>();

	static int nbpioche = 0;
	
	// Menu
	
    public static void main(String[] args) {
		Config.initConfig();
		mainMenu();
    }

	public static void mainMenu(){
		System.out.println("=================[ UNO ]=================");
		System.out.println("1 - Jouer au jeu");
		System.out.println("2 - Options et règles\n\n");
		System.out.println("Merci de choisir une option");
		int res = Utils.getIntInput();
		System.out.println("Option sélectionné : " + res);
		if(res == 1){
			launchGame();
		}else {
			Config.initConfigMenu();
		}
	}

	private static void launchGame(){
		// Joueurs
		joueurs.add(new Joueur());
		for(int b = 0; b<Integer.parseInt(Config.getStringProperties(Options.NOMBREIA)); b++){
			joueurs.add(new JoueurIA());
		}
		
		// Cartes
		initCartes();

		for (Joueur ia : joueurs) {
			distribuerCartes(ia);
		}
		
		// Defausse
		int p=0; 
		do {
			defausse.add(pioche.remove(p));
			
		} while (defausse.get(p).getCouleur() == Couleur.NOIR && defausse.get(p).getSymbole().ordinal() < 10);
		couleurDefausse = defausse.get(p).getCouleur();
		
		// Partie
		while(!checkWin()){
			// Affichage
			System.out.println("\n\n\n");
			
			// Tour
			if (tour == 0) {
				System.out.println(Couleur.RESET+"AU TOUR DE : VOUS");
			} else {
				System.out.println("AU TOUR DE : "+((JoueurIA) joueurs.get(tour)).getNom());
			}
			
			
			// Nombre de carte de chaque IA
			for (int i = 1; i < joueurs.size(); i++) {
				System.out.println(((JoueurIA) joueurs.get(i)).getNom()+" : "+joueurs.get(i).getMain().size()+" cartes ");
			}
			updateCouleurDefausse();
			
			// Defausse
			System.out.println("\nDefausse\n"+defausse.get(defausse.size()-1).toString()+"\n");
			
			if (couleurDefausse == Couleur.NOIR) {
				System.out.println("Placez une carte "+couleurDefausse.toString()+"\n");
			}
			
			// Action
			if (joueurs.get(tour) instanceof JoueurIA) {
				((JoueurIA) joueurs.get(tour)).jouerCarte(true);
			} else {
				// Main
				joueurs.get(0).printMain();
				joueurs.get(tour).jouerCarte();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			// Fin du tour
			finishPlayerTour();
		}
	}

	private static boolean checkWin(){
		for(Joueur j : joueurs){
			if(j.getMain().size() == 0){
				System.out.println("Le gagnant est : " + j.toString());
				return true;
			}
		}
		return false;
	}

	public static void finishPlayerTour() {
		if (clockwise) tour++;
		else tour--;
		
		if (tour >= joueurs.size()) {
			tour = 0;
		} else if (tour < 0) {
			tour = joueurs.size()-1;
		}
	}
	
    public static void initCartes() {
    	// Creation des cartes
    	
    	for (int i = 0; i < 15; i++) {
    		for(int j = 0; j < 4; j++) {
    			Couleur c = Couleur.getCouleur(j);
				if (i >= 13) c = Couleur.NOIR;
    			pioche.add(new Carte(Symbole.getSymbole(i),c));
    			if (i > 0 && i < 13) {
    				pioche.add(new Carte(Symbole.getSymbole(i),c));
    			}
    		}
    	}
    	
    	Collections.shuffle(pioche); // Mélange
    }
    
    public static void changementSens() {
    	clockwise = !clockwise;
    }
    
    public static void updateCouleurDefausse() {
		Couleur color = defausse.get(defausse.size() - 1).getCouleur();
    	couleurDefausse = color;
    }
    
    public static void setCouleurDefausse(Couleur c) {
    	couleurDefausse = c;
    }
      
    public static void setCouleurDefausse(int chiffrecouleur) {
    	//defausse.get(defausse.size()-1).setCouleur(Couleur.getCouleur(chiffrecouleur));
    	couleurDefausse = Couleur.getCouleur(chiffrecouleur);
    }
    
    public static Couleur getCouleurDefausse() {
    	return couleurDefausse;
    }
    
    public static void distribuerCartes(Joueur j) {
    	for (int i = 0; i < 7; i++) {
    		j.piocherCarte();
    	}
    	if(!(j instanceof JoueurIA)) {
    		for (int i = 0; i < Integer.parseInt(Config.getStringProperties(Options.NUMBRECARTEENPLUS)); i++) {
        		j.piocherCarte();
        	}
    	}
    }
}
