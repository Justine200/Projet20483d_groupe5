/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import application.JeuFXController;
import javafx.scene.layout.GridPane;

/**
 *
 * @author justineherolt
 */
public class Case implements Parametres, java.io.Serializable {

    private int x, y, valeur;
    private Grille grille;
    private int xInterface, yInterface;
    private int objectifx, objectify;

    public Case(int abs, int ord, int v, Grille g) {
        this.x = abs;
        this.y = ord;
        this.valeur = v;
        this.grille = g;

        updateInterface(g);
        
        this.objectifx = this.xInterface;
        this.objectify = this.yInterface;

    }

    public void updateInterface(Grille grille) {

        switch (grille.getGrilleNum()) {
            case 0:
                switch (x) {
                    case 0:
                        this.xInterface = JeuFXController.x;
                        break;
                    case 1:
                        this.xInterface = JeuFXController.x + JeuFXController.tailleCase;
                        break;
                    default:
                        this.xInterface = JeuFXController.x + JeuFXController.tailleCase * 2;
                        break;
                }   switch (y) {
                    case 0:
                        this.yInterface = JeuFXController.y;
                        break;
                    case 1:
                        this.yInterface = JeuFXController.y + JeuFXController.tailleCase;
                        break;
                    default:
                        this.yInterface = JeuFXController.y + JeuFXController.tailleCase * 2;
                        break;
                }   break;
            case 1:
                switch (x) {
                    case 0:
                        this.xInterface = JeuFXController.x1;
                        break;
                    case 1:
                        this.xInterface = JeuFXController.x1 + JeuFXController.tailleCase;
                        break;
                    default:
                        this.xInterface = JeuFXController.x1 + JeuFXController.tailleCase * 2;
                        break;
                }   switch (y) {
                    case 0:
                        this.yInterface = JeuFXController.y1;
                        break;
                    case 1:
                        this.yInterface = JeuFXController.y1 + JeuFXController.tailleCase;
                        break;
                    default:
                        this.yInterface = JeuFXController.y1 + JeuFXController.tailleCase * 2;
                        break;
                }   break;
            default:
                switch (x) {
                    case 0:
                        this.xInterface = JeuFXController.x2;
                        break;
                    case 1:
                        this.xInterface = JeuFXController.x2 + JeuFXController.tailleCase;
                        break;
                    default:
                        this.xInterface = JeuFXController.x2+ JeuFXController.tailleCase * 2;
                        break;
                }   switch (y) {
                    case 0:
                        this.yInterface = JeuFXController.y2;
                        break;
                    case 1:
                        this.yInterface = JeuFXController.y2 + JeuFXController.tailleCase;
                        break;
                    default:
                        this.yInterface = JeuFXController.y2 + JeuFXController.tailleCase * 2;
                        break;
                }   break;
        }

    }

    public void updateObjectif(int grilleNum) {

        switch (grilleNum) {
            case 0:
                switch (x) {
                    case 0:
                        this.objectifx = JeuFXController.x;
                        break;
                    case 1:
                        this.objectifx = JeuFXController.x + JeuFXController.tailleCase;
                        break;
                    default:
                        this.objectifx = JeuFXController.x + JeuFXController.tailleCase * 2;
                        break;
                }   switch (y) {
                    case 0:
                        this.objectify = JeuFXController.y;
                        break;
                    case 1:
                        this.objectify = JeuFXController.y + JeuFXController.tailleCase;
                        break;
                    default:
                        this.objectify = JeuFXController.y + JeuFXController.tailleCase * 2;
                        break;
                }   break;
            case 1:
                switch (x) {
                    case 0:
                        this.objectifx = JeuFXController.x1;
                        break;
                    case 1:
                        this.objectifx = JeuFXController.x1 + JeuFXController.tailleCase;
                        break;
                    default:
                        this.objectifx = JeuFXController.x1 + JeuFXController.tailleCase * 2;
                        break;
                }   switch (y) {
                    case 0:
                        this.objectify = JeuFXController.y1;
                        break;
                    case 1:
                        this.objectify = JeuFXController.y1 + JeuFXController.tailleCase;
                        break;
                    default:
                        this.objectify = JeuFXController.y1 + JeuFXController.tailleCase * 2;
                        break;
                }   break;
            default:
                switch (x) {
                    case 0:
                        this.objectifx = JeuFXController.x2;
                        break;
                    case 1:
                        this.objectifx = JeuFXController.x2 + JeuFXController.tailleCase;
                        break;
                    default:
                        this.objectifx = JeuFXController.x2+ JeuFXController.tailleCase * 2;
                        break;
                }   switch (y) {
                    case 0:
                        this.objectify = JeuFXController.y2;
                        break;
                    case 1:
                        this.objectify = JeuFXController.y2 + JeuFXController.tailleCase;
                        break;
                    default:
                        this.objectify = JeuFXController.y2 + JeuFXController.tailleCase * 2;
                        break;
                }   break;
        }

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
    public void ObjectifToCoord() {
        this.x = this.objectifx;
        this.y = this.objectify;
    }

    /**
     * @return the xInterface
     */
    public int getxInterface() {
        return xInterface;
    }

    /**
     * @param xInterface the xInterface to set
     */
    public void setxInterface(int xInterface) {
        this.xInterface = xInterface;
    }

    /**
     * @return the yInterface
     */
    public int getyInterface() {
        return yInterface;
    }

    /**
     * @param yInterface the yInterface to set
     */
    public void setyInterface(int yInterface) {
        this.yInterface = yInterface;
    }

}
