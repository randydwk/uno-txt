package uno.txt;

import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    public static String getStringInput(){
        Scanner keyboard = new Scanner(System.in);
        String res = keyboard.next();
        return res;
    }

    public static int getIntInput(){
        Scanner keyboard = new Scanner(System.in);
        int res = keyboard.nextInt();
        return res;

    }

    public static Couleur choisirCoulJokerIA(ArrayList<Carte> deck) {
        int comptRouge = 0;
        int comptBleue = 0;
        int comptVert = 0;
        int comptJaune = 0;
        for (int i = 0; i < deck.size(); i++) {
            if(deck.get(i).getCouleur() == Couleur.BLEU){
                comptBleue++;
            }else if(deck.get(i).getCouleur() == Couleur.ROUGE){
                comptRouge++;
            }else if(deck.get(i).getCouleur() == Couleur.VERT){
                comptVert++;
            }else{
                comptJaune++;
            }
        }
        if (comptRouge >= comptBleue) {
            if (comptRouge >= comptVert) {
                if (comptRouge >= comptJaune) {
                    return Couleur.ROUGE;
                } else {
                    return Couleur.JAUNE;
                }
            } else {
                if (comptVert >= comptJaune) {
                    return Couleur.VERT;
                } else {
                    return  Couleur.JAUNE;
                }
            }
        } else {
            if (comptBleue >= comptJaune) {
                if (comptBleue >= comptVert) {
                    return Couleur.BLEU;
                } else {
                    return Couleur.VERT;
                }
            }
            else
            {
                if (comptJaune >= comptVert) {
                    return Couleur.JAUNE;
                } else {
                    return Couleur.VERT;
                }
            }
        }
    }

    public Carte jouerCartePlusCouleur(ArrayList<Carte> deck) {
        Carte c = deck.get(0);
        if(jouerCartePlusCouleur(deck).equals(Couleur.BLEU)){
            for (Carte carte : deck) {
                if (carte.getCouleur().equals(Couleur.BLEU)) {
                    return carte;
                }
            }
        }else if(jouerCartePlusCouleur(deck).equals(Couleur.ROUGE)) {
            for (Carte carte : deck) {
                if (carte.getCouleur().equals(Couleur.ROUGE)) {
                    return carte;
                }
            }
        }else if(jouerCartePlusCouleur(deck).equals(Couleur.VERT)) {
            for (Carte carte : deck) {
                if (carte.getCouleur().equals(Couleur.VERT)) {
                    return carte;
                }
            }
        }else {
            for (Carte carte : deck) {
                if (carte.getCouleur().equals(Couleur.JAUNE)) {
                    return carte;
                }
            }
        }
        return c;
    }
}
