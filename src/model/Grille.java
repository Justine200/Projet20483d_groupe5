
package model;

import java.util.*;

public class Grille implements Parametres, java.io.Serializable {

    private final HashSet<Case> grille;
    private int valeurMax = 0;
    private boolean deplacement;
    private int score;
    private int grilleNum;

    public Grille() {
        this.grille = new HashSet<>();
        this.score = 0;
        this.grilleNum = 0;
    }

    public Grille(Grille g) {
        HashSet<Case> copyG = new HashSet<Case>();
        g.getListCase().forEach(Case -> copyG.add((Case.copy())));
        this.grille = copyG;
        this.valeurMax = g.valeurMax;
        this.deplacement = g.deplacement;
        this.score = g.score;
    }

    public HashSet<Case> getListCase() {
        return grille;
    }

    public Grille copy() {
        return new Grille(this);
    }

    @Override
    public String toString() {
        int[][] tableau = new int[TAILLE][TAILLE];
        for (Case c : this.grille) {
            tableau[c.getY()][c.getX()] = c.getValeur();
        }
        String result = "";
        for (int i = 0; i < tableau.length; i++) {
            result += Arrays.toString(tableau[i]) + "\n";
        }
        return result;
    }

    public String toHTML() {
        int[][] tableau = new int[TAILLE][TAILLE];
        for (Case c : this.grille) {
            tableau[c.getY()][c.getX()] = c.getValeur();
        }
        String result = "<html>";
        for (int i = 0; i < tableau.length; i++) {
            result += Arrays.toString(tableau[i]) + "<br/>";
        }
        result += "</html>";
        return result;
    }

    public HashSet<Case> getGrille() {
        return grille;
    }

    public void setGrille(Case c) {
        this.grille.add(c);
    }

    public int getValeurMax() {
        return valeurMax;
    }

    public boolean partieFinie() {
        if (this.grille.size() < TAILLE * TAILLE) {
            return false;
        } else {
            for (Case c : this.grille) {
                for (int i = 1; i <= 2; i++) {
                    if (c.getVoisinDirect(i) != null) {
                        if (c.valeurEgale(c.getVoisinDirect(i))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean lanceurDeplacerCases(int direction) {
        Case[] extremites = this.getCasesExtremites(direction);
        deplacement = false; // pour vérifier si on a bougé au moins une case après le déplacement, avant d'en rajouter une nouvelle
        for (int i = 0; i < TAILLE; i++) {
            switch (direction) {
                case HAUT:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                case BAS:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                case GAUCHE:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                default:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
            }
        }

        return deplacement;
    }

    private void fusion(Case c) {
        c.setValeur(c.getValeur() * 2);
        if (this.valeurMax < c.getValeur()) {
            this.valeurMax = c.getValeur();
        }
        deplacement = true;
        this.score = c.getValeur() + this.score; // Score
    }

    public int getScore() {
        return this.score;
    }

    private void deplacerCasesRecursif(Case[] extremites, int rangee, int direction, int compteur) {
        if (extremites[rangee] != null) {
            if ((direction == HAUT && extremites[rangee].getY() != compteur)
                    || (direction == BAS && extremites[rangee].getY() != TAILLE - 1 - compteur)
                    || (direction == GAUCHE && extremites[rangee].getX() != compteur)
                    || (direction == DROITE && extremites[rangee].getX() != TAILLE - 1 - compteur)) {
                this.grille.remove(extremites[rangee]);
                switch (direction) {
                    case HAUT:
                        extremites[rangee].setY(compteur);
                        break;
                    case BAS:
                        extremites[rangee].setY(TAILLE - 1 - compteur);
                        break;
                    case GAUCHE:
                        extremites[rangee].setX(compteur);
                        break;
                    default:
                        extremites[rangee].setX(TAILLE - 1 - compteur);
                        break;
                }
                this.grille.add(extremites[rangee]);
                deplacement = true;
            }
            Case voisin = extremites[rangee].getVoisinDirect(-direction);
            if (voisin != null) {
                if (extremites[rangee].valeurEgale(voisin)) {
                    this.fusion(extremites[rangee]);
                    extremites[rangee] = voisin.getVoisinDirect(-direction);
                    this.grille.remove(voisin);
                    this.deplacerCasesRecursif(extremites, rangee, direction, compteur + 1);
                } else {
                    extremites[rangee] = voisin;
                    this.deplacerCasesRecursif(extremites, rangee, direction, compteur + 1);
                }
            }
        }
    }

    /*
    * Si direction = HAUT : retourne les 4 cases qui sont le plus en haut (une pour chaque colonne)
    * Si direction = DROITE : retourne les 4 cases qui sont le plus à droite (une pour chaque ligne)
    * Si direction = BAS : retourne les 4 cases qui sont le plus en bas (une pour chaque colonne)
    * Si direction = GAUCHE : retourne les 4 cases qui sont le plus à gauche (une pour chaque ligne)
    * Attention : le tableau retourné peut contenir des null si les lignes/colonnes sont vides
     */
    public Case[] getCasesExtremites(int direction) {
        Case[] result = new Case[TAILLE];
        for (Case c : this.grille) {
            switch (direction) {
                case HAUT:
                    if ((result[c.getX()] == null) || (result[c.getX()].getY() > c.getY())) { // si on n'avait pas encore de case pour cette rangée ou si on a trouvé un meilleur candidat
                        result[c.getX()] = c;

                        c.updateInterface(this);

                    }
                    break;
                case BAS:
                    if ((result[c.getX()] == null) || (result[c.getX()].getY() < c.getY())) {
                        result[c.getX()] = c;

                        c.updateInterface(this);

                    }
                    break;
                case GAUCHE:
                    if ((result[c.getY()] == null) || (result[c.getY()].getX() > c.getX())) {
                        result[c.getY()] = c;
                        c.updateInterface(this);
                    }
                    break;
                default:
                    if ((result[c.getY()] == null) || (result[c.getY()].getX() < c.getX())) {
                        result[c.getY()] = c;
                        c.updateInterface(this);
                    }
                    break;
            }
        }
        return result;
    }

    public void victory() {
        System.out.println("Bravo ! Vous avez atteint " + this.valeurMax);
        System.exit(0);
    }

    public void gameOver() {
        System.out.println("La partie est finie. Votre score est " + this.valeurMax);
        System.exit(1);
    }

    public boolean nouvelleCase() {
        if (this.grille.size() < TAILLE * TAILLE) {
            ArrayList<Case> casesLibres = new ArrayList<>();
            Random ra = new Random();
            int valeur = (1 + ra.nextInt(2)) * 2;
            // on crée toutes les cases encore libres
            for (int x = 0; x < TAILLE; x++) {
                for (int y = 0; y < TAILLE; y++) {
                    Case c = new Case(x, y, valeur, this);
                    if (!this.grille.contains(c)) { // contains utilise la méthode equals dans Case
                        casesLibres.add(c);
                    }
                }
            }
            // on en choisit une au hasard et on l'ajoute à la grille
            Case ajout = casesLibres.get(ra.nextInt(casesLibres.size()));
            ajout.setGrille(this);
            System.out.println(ajout);
            this.grille.add(ajout);
            if ((this.grille.size() == 1) || (this.valeurMax == 2 && ajout.getValeur() == 4)) { // Mise à jour de la valeur maximale présente dans la grille si c'est la première case ajoutée ou si on ajoute un 4 et que l'ancien max était 2
                this.valeurMax = ajout.getValeur();
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean caseOccupe(int x, int y) {
        for (Case c : this.grille) {
            if (c.getY() == y && c.getX() == x) {
                return true;
            }
        }
        return false;
    }

    /*public int valeurCase(int x, int y){
        for(Case c:this.grille){
            if(c.getX()==x && c.getY()==y){
                return c.getValeur();
            }
        }
        return (-1);
    }*/
    /**
     * @return the grilleNum
     */
    public int getGrilleNum() {
        return grilleNum;
    }

    /**
     * @param grilleNum the grilleNum to set
     */
    public void setGrilleNum(int grilleNum) {
        this.grilleNum = grilleNum;
    }
}
