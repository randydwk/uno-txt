package uno.txt;

public enum Symbole {	
	ZERO(0,		new String[]{"|0 ___  |","| // \\\\ |","| || || |","| || || |","| \\\\_// |","|      0|"}),
	UN(1,		new String[]{"|1  __  |","|  //|| |","| // || |","|    || |","|    || |","|      1|"}),
	DEUX(2,		new String[]{"|2 ___  |","| // \\\\ |","|    // |","|  //   |","| //___ |","|      2|"}),
	TROIS(3,	new String[]{"|3 ___  |","| // \\\\ |","|    // |","|    \\\\ |","| \\\\_// |","|      3|"}),
	QUATRE(4,	new String[]{"|4  ___ |","|  //|| |","| //_||_|","| ===||=|","|    || |","|      4|"}),
	CINQ(5,		new String[]{"|5_____ |","| ||    |","| ===\\  |","|    \\\\ |","| ___// |","|      5|"}),
	SIX(6,		new String[]{"|6 ___  |","| // \\\\ |","| ||__  |","| || \\\\ |","| \\\\_// |","|      6|"}),
	SEPT(7,		new String[]{"|7_____ |","| ---// |","|   //  |","|  //   |","| //    |","|      7|"}),
	HUIT(8,		new String[]{"|8 ___  |","| // \\\\ |","| \\\\ // |","| // \\\\ |","| \\\\_// |","|      8|"}),
	NEUF(9,		new String[]{"|9 ___  |","| // \\\\ |","| \\\\_|| |","|    || |","| \\\\_// |","|      9|"}),
	SENS(10,	new String[]{"|/   ___|","|    \\ ||","|   //\\||","||\\//   |","||_\\    |","|      /|"}),
	PASSE(11,	new String[]{"|o ___  |","| // \\\\ |","|// //\\\\|","|\\\\// //|","| \\\\_// |","|      o|"}),
	PLUSDEUX(12,new String[]{"|+2 ___ |","|  /  / |","| /__/_ |","|  /  / |","| /__/  |","|     +2|"}),
	PLUSQUATRE(13,new String[]{"|+4"+Couleur.RED+"___  "+Couleur.WHITE+"|","| "+Couleur.RED+"/  /"+Couleur.BLUE+"__"+Couleur.WHITE+"|","|"+Couleur.RED+"/__/"+Couleur.GREEN+"__"+Couleur.BLUE+"/"+Couleur.WHITE+"|","|"+Couleur.YELLOW+"/__"+Couleur.GREEN+"/  /"+Couleur.WHITE+"|","|  "+Couleur.GREEN+"/__/ "+Couleur.WHITE+"|","|     +4|"}),
	CHANGECOULEUR(14,new String[]{"|O "+Couleur.RED+"__"+Couleur.BLUE+"_  "+Couleur.WHITE+"|","| "+Couleur.RED+"/ |"+Couleur.BLUE+" \\ "+Couleur.WHITE+"|","|"+Couleur.RED+"/"+Couleur.YELLOW+"__"+Couleur.RED+"|"+Couleur.BLUE+"__\\"+Couleur.WHITE+"|","|"+Couleur.YELLOW+"\\  "+Couleur.GREEN+"|  /"+Couleur.WHITE+"|","| "+Couleur.YELLOW+"\\_"+Couleur.GREEN+"|_/ "+Couleur.WHITE+"|","|      O|"});

	private int nb;
	private String[] sprite;
	
	private Symbole(int nb, String[] sprite) {
		this.nb = nb;
		this.sprite = sprite;
	}
	
	public String[] getSprite() {
		return this.sprite;
	}
	
	public static Symbole getSymbole(int nb) {
		for (Symbole s : Symbole.values()) {
			if (s.nb == nb) {
				return s;
			}
		}
		return null;
	}
}
