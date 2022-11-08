/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet20483d_groupe5;

import java.util.Arrays;
import static projet20483d_groupe5.Parametres.TAILLE;

/**
 *
 * @author justineherolt
 */

public class Grille3D {

    private Grille[] grilles = new Grille[3];
    private int valeurMax = 0;
    private int score;

    //Constructeur
    public Grille3D() {
        this.score = 0;
        for (int i = 0; i < 3; i++) {
            this.grilles[i] = new Grille();
        }
    }

    //Méthode qui permet l'affichage des grilles côtes à côtes dans la console
    @Override
    public String toString() {
        String result = "";
        int[][] tableau0 = new int[TAILLE][TAILLE];
        int[][] tableau1 = new int[TAILLE][TAILLE];
        int[][] tableau2 = new int[TAILLE][TAILLE];
        for (Case c : this.grilles[0].getGrille()) {
            tableau0[c.getY()][c.getX()] = c.getValeur();
        }
        for (Case c : this.grilles[1].getGrille()) {
            tableau1[c.getY()][c.getX()] = c.getValeur();
        }
        for (Case c : this.grilles[2].getGrille()) {
            tableau2[c.getY()][c.getX()] = c.getValeur();
        }
        for (int i = 0; i < tableau0.length; i++) {
            result += Arrays.toString(tableau0[i]) + "\t" + Arrays.toString(tableau1[i]) + "\t" + Arrays.toString(tableau2[i]) + "\n";
        }
        this.grilles[0].nouvelleCase();
        return result;
    }

    public boolean conditionFin() {//mets fin à la partie si les 3 Grilles sont rempli et qu'il n'y a plus de possibilité de jouer
        return (this.grilles[0].partieFinie() && this.grilles[1].partieFinie() && this.grilles[2].partieFinie());
    }

    public boolean victoire() {//Renvoie vrai si les score est à 2048
        if (this.valeurMax == 2048) {
            return true;
        } else {
            return false;
        }
    }

    public int score() { // score des 3 grilles
        return (this.grilles[0].getScore() + this.grilles[1].getScore() + this.grilles[2].getScore());
    }

    public void deplacement(int direction){
        for(int i=0; i<3; i++){
            this.grilles[i].lanceurDeplacerCases(direction);
        }
    }
}
