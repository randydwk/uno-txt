package uno.txt;

import java.util.concurrent.TimeUnit;

import java.util.ArrayList;

public class JoueurIA extends Joueur{

	private static int nbIA=0;
	private int nb = 0;

	public JoueurIA() {
		nbIA++;
		nb = nbIA;
	}

	public String getNom() {
		return "Bot"+nb;
	}

	void jouerCarte(boolean reflechie) {
		if (reflechie) {
			Carte c;
			ArrayList<Carte> possib = new ArrayList<>();
			Uno.setCouleurDefausse();
			if (Uno.defausse.get(Uno.defausse.size() - 1).getSymbole() == Symbole.PLUSDEUX && Uno.nbpioche != 0) {
				for (int i = 0; i < 2; i++) {
					this.piocherCarte();
				}
				Uno.nbpioche = 0;
				Uno.defausse.add(new Carte(Symbole.DEUX, Uno.defausse.get(Uno.defausse.size() - 1).getCouleur()));
				return;
			} else if (Uno.defausse.get(Uno.defausse.size() - 1).getSymbole() == Symbole.PLUSQUATRE && Uno.nbpioche != 0) {
				for (int i = 0; i < 4; i++) {
					this.piocherCarte();
				}
				Uno.nbpioche = 0;
				return;
			} else if (Uno.defausse.get(Uno.defausse.size()-1).getSymbole()==Symbole.PASSE) {
				Uno.defausse.add(new Carte(Symbole.ZERO, Uno.defausse.get(Uno.defausse.size() - 1).getCouleur()));
				return;
			}
			for (int j = 0; j < getMain().size(); j++) {
				c = getMain().get(j);
				if (c.empilable(Uno.defausse.get(Uno.defausse.size() - 1))) {
					possib.add(c);
				}
			}
			if (possib.size() >= 1) {
				Couleur most = Utils.choisirCoulJokerIA(getMain());
				for (int i = 0; i < possib.size(); i++) {
					if (possib.get(i).getCouleur().equals(most)) {
						Uno.defausse.add(possib.get(i));
						this.getMain().remove(possib.get(i));
						return;
					}
				}
				if (possib.size() >= 1) {
					Couleur most = Utils.choisirCoulJokerIA(getMain());
					for (int i = 0; i < possib.size(); i++) {
						if (possib.get(i).getCouleur().equals(most)) {
							Uno.defausse.add(possib.get(i));
							this.getMain().remove(possib.get(i));
							return;
						}
						// Actions des cartes
						Symbole symboleJoue = possib.get(0).getSymbole();
						if (symboleJoue == Symbole.SENS) {
							Uno.updateCouleurDefausse();
							Uno.changementSens();
						} else if (symboleJoue == Symbole.PASSE) {
							Uno.updateCouleurDefausse();
							Uno.finishPlayerTour();
						} else if (symboleJoue == Symbole.PLUSDEUX) {
							Uno.updateCouleurDefausse();
							Uno.aPiocher += 2;
						} else if (symboleJoue == Symbole.CHANGECOULEUR) {
							Uno.setCouleurDefausse(Utils.choisirCoulJokerIA(getMain()));
						} else if (possib.get(0).getSymbole().equals(Symbole.PLUSQUATRE)) {
							Uno.setCouleurDefausse(Utils.choisirCoulJokerIA(getMain()));
							Uno.aPiocher += 4;
						}else {
							Uno.updateCouleurDefausse();
						}
						Uno.defausse.add(possib.get(0));
						this.getMain().remove(possib.get(0));
					}
					System.out.println("Le bot a joué");
				} else {
					this.piocherCarte();
					Uno.updateCouleurDefausse();
					System.out.println("Le bot pioche");
					
				}
				if(Uno.defausse.get(Uno.defausse.size()-1).getSymbole().equals(Symbole.PLUSQUATRE)){
					Uno.nbpioche = 4;
				}
				if(Uno.defausse.get(Uno.defausse.size()-1).getSymbole().equals(Symbole.PLUSDEUX)){
					Uno.nbpioche = 2;
				}
			}else {
				this.piocherCarte();
				Uno.setCouleurDefausse();
				System.out.println("Il lui reste " + this.getMain().size() + " carte en mains." + Couleur.RESET);
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}else { // IA RANDOM
			Carte c;
			int i=0;
			if(Uno.defausse.get(Uno.defausse.size()-1).getSymbole()==Symbole.PLUSDEUX){
				for(int h=0;i<2;h++)this.piocherCarte();
			}else{
				for(int h=0;i<5;h++)this.piocherCarte();
				Uno.defausse.add(new Carte(Symbole.ZERO, Couleur.BLEU));
			}
			do {
				c = getMain().get(i);
				i++;
			} while (!c.empilable(Uno.defausse.get(Uno.defausse.size()-1)) && i<this.getMain().size());
			if (i < this.getMain().size()-1) {
				Uno.defausse.add(c);
				this.getMain().remove(c);
			}
			else this.piocherCarte();
			// Changer la couleur de la defausse
			Uno.updateCouleurDefausse();

			System.out.println("Il lui reste " + this.getMain().size() + " carte en mains."+Couleur.RESET);

			//Utils.getStringInput();
		}
	}

	void piocherCarte() {
		this.getMain().add(Uno.pioche.remove(0));
	}
}
