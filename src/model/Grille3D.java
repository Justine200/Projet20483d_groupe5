/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;

import static model.Parametres.OBJECTIF;
import static model.Parametres.TAILLE;

/**
 *
 * @author justineherolt
 */

public class Grille3D implements java.io.Serializable{

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

    public Grille[] getGrilles() {
        return grilles;
    }


    public Grille3D(Grille3D g){
        Grille[] copyGrille = new Grille[3];
        for (int i = 0; i<3;i++){
            copyGrille[i]=g.grilles[i].copy();
        }
        this.valeurMax=g.valeurMax;
        this.score=g.score;

    }

    public Grille3D copy(){
        return new Grille3D(this);
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
        return result;
    }

    public boolean nouvellesCases(){
        boolean x,y,z,b;
        if (!grilles[0].partieFinie())
        x = (int)(Math.random()*100)>=50;
        else x = false;
        if (!grilles[1].partieFinie())
        y = (int)(Math.random()*100)>=50;
        else y = false;
        if (!grilles[2].partieFinie())
        z = (int)(Math.random()*100)>=50;
        else z=false;

        if (x){
            b = this.grilles[0].nouvelleCase();
        }
        if (y){
            b = this.grilles[1].nouvelleCase();
        }
        if (z){
            b = this.grilles[2].nouvelleCase();
        }
        if (!x&&!y&&!z){
            int k=this.grilleDispo();
            if (k==-1) {
                this.conditionFin();
                return false;
            }
            else
            b=this.grilles[k].nouvelleCase();
        }
        return true;
    }

    /**
     *
     * @return le numéro d'une grille ayant encore de la place
     */
    public int grilleDispo(){
        if (!this.grilles[0].partieFinie())
            return 0;
        if (!this.grilles[2].partieFinie())
            return 2;
        if (!this.grilles[1].partieFinie())
            return 1;
        else return -1;
    }
    public boolean conditionFin() {//mets fin à la partie si les 3 Grilles sont rempli et qu'il n'y a plus de possibilité de jouer
        return (this.grilles[0].partieFinie() && this.grilles[1].partieFinie() && this.grilles[2].partieFinie());
    }




    public boolean victoire() {//Renvoie vrai si les score est à 2048
        if (this.getValeurMax() == OBJECTIF) {
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

    /**
     * @return the valeurMax
     */
    public int getValeurMax() {
        return Math.max( Math.max(this.grilles[0].getValeurMax(), this.grilles[1].getValeurMax()) , this.grilles[2].getValeurMax());
    }

    /**
     * @param valeurMax the valeurMax to set
     */
    public void setValeurMax(int valeurMax) {
        this.valeurMax = valeurMax;
    }

    public ArrayList<Grille3D> addListGrille(ArrayList<Grille3D> grilles3,Grille3D g){
        grilles3.add(g);
        if (grilles3.size()==5)
            grilles3.remove(0);
        return grilles3;
    }
    public ArrayList<Grille3D> removeListGrille(ArrayList<Grille3D> grilles3){
        grilles3.remove(grilles3.size()-1);
        return grilles3;
    }

    public Grille3D dernierCoup(ArrayList<Grille3D> grilles3){
        if (grilles3.size()==0){
            System.out.println("Retour impossible");
            return grilles3.get(0);
        }
        return grilles3.get(grilles3.size()-1);
    }

}
