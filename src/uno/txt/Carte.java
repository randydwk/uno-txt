package uno.txt;

public class Carte {
	private Couleur couleur;
	private Symbole symbole;
	
	public Carte (Symbole symbole, Couleur couleur) {
		this.symbole = symbole;
		this.couleur=couleur;
	}
	
	public Couleur getCouleur() {
		return couleur;
	}
	
	public void setCouleur(Couleur c) {
		this.couleur = c;
	}
	
	public Symbole getSymbole() {
		return symbole;
	}
	
	public boolean empilable(Carte autreCarte) {

		return (this.getSymbole()==Symbole.CHANGECOULEUR||this.getSymbole()==Symbole.PLUSQUATRE) || (this.getCouleur() == Uno.getCouleurDefausse() || this.getSymbole() == autreCarte.getSymbole());

	}
	
	public String toString() {
		String sprite = Couleur.RESET+this.getCouleur().getDrawColor()+"o-------o\n";
		for (int s = 0; s < 6; s++) {
			sprite += this.getSymbole().getSprite()[s]+"\n";
		}
		return sprite+"o-------o"+Couleur.RESET;
	}
}

