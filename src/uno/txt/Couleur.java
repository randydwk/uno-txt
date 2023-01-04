package uno.txt;

public enum Couleur {
	VERT(0,Couleur.GREEN),ROUGE(1,Couleur.RED),JAUNE(2,Couleur.YELLOW),BLEU(3,Couleur.BLUE),NOIR(4,Couleur.WHITE);
	private int nb;
	private String drawColor;
	
	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String WHITE = "\u001B[37m";
	
	private Couleur(int nb, String drawColor) {
		this.nb = nb;
		this.drawColor = drawColor;
	}
	
	public String getDrawColor() {
		return this.drawColor;
	}
	
	public String toString() {
		switch (this)
		{
		case BLEU:
			return Couleur.BLUE+"bleue"+Couleur.RESET;
		case JAUNE:
			return Couleur.YELLOW+"jaune"+Couleur.RESET;
		case NOIR:
			return Couleur.WHITE+"noire"+Couleur.RESET;
		case ROUGE:
			return Couleur.RED+"rouge"+Couleur.RESET;
		case VERT:
			return Couleur.GREEN+"verte"+Couleur.RESET;
		default:
			return "";
		}
	}
	
	public static Couleur getCouleur (int nb) {
		for (Couleur c : Couleur.values()) {
			if (c.nb == nb) {
				return c;
			}
		}
		return null;
	}
}
