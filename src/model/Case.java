/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.layout.GridPane;

/**
 *
 * @author justineherolt
 */
public class Case implements Parametres, java.io.Serializable {

    private int x, y, valeur;
    private Grille grille;
    private int objectifx, objectify;

    public Case(int abs, int ord, int v, Grille g) {
        this.x = abs;
        this.y = ord;
        this.valeur = v;
        this.grille = g;
        this.objectifx = abs;
        this.objectify = ord;
    }

    public Case(Case c) {
        this.x = c.x;
        this.y = c.y;
        this.valeur = c.valeur;
        this.grille = c.grille;

    }

    public Case(int row, int col, int i, GridPane grille0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Case copy() {
        return new Case(this);
    }

    public void setGrille(Grille g) {
        this.grille = g;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() {
        return this.valeur;
    }

    @Override
    public boolean equals(Object obj) { // la méthode equals est utilisée lors de l'ajout d'une case à un ensemble pour vérifier qu'il n'y a pas de doublons (teste parmi tous les candidats qui ont le même hashcode)
        if (obj instanceof Case) {
            Case c = (Case) obj;
            return (this.x == c.x && this.y == c.y);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() { // détermine le hashcode
        return this.x * 7 + this.y * 13;
    }

    public boolean valeurEgale(Case c) {
        if (c != null) {
            return this.valeur == c.valeur;
        } else {
            return false;
        }
    }

    public Case getVoisinDirect(int direction) {
        if (direction == HAUT) {
            for (int i = this.y - 1; i >= 0; i--) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == this.x && c.getY() == i) {
                        return c;
                    }
                }
            }
        } else if (direction == BAS) {
            for (int i = this.y + 1; i < TAILLE; i++) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == this.x && c.getY() == i) {
                        return c;
                    }
                }
            }
        } else if (direction == GAUCHE) {
            for (int i = this.x - 1; i >= 0; i--) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == i && c.getY() == this.y) {
                        return c;
                    }
                }
            }
        } else if (direction == DROITE) {
            for (int i = this.x + 1; i < TAILLE; i++) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == i && c.getY() == this.y) {
                        return c;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Case(" + this.x + "," + this.y + "," + this.valeur + ")";
    }

    /**
     * @return the objectifx
     */
    public int getObjectifx() {
        return objectifx;
    }

    /**
     * @param objectifx the objectifx to set
     */
    public void setObjectifx(int objectifx) {
        this.objectifx = objectifx;
    }

    /**
     * @return the objectify
     */
    public int getObjectify() {
        return objectify;
    }

    /**
     * @param objectify the objectify to set
     */
    public void setObjectify(int objectify) {
        this.objectify = objectify;
    }
    
    /**
     * Methode pour mise à jour les coordonnées
     */
    public void ObjectifToCoord(){
        this.x = this.objectifx;
        this.y = this.objectify;
    }

}
