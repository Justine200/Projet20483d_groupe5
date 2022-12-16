
package model;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Modele2048 {
    private Grille3D grille3D = new Grille3D(); 
    

    public Modele2048(){      
        this.grille3D.nouvellesCases();      
        System.out.println(this.grille3D);
    }

    /**
     * @return the grille3D
     */
    public Grille3D getGrille3D() {
        return grille3D;
    }

    /**
     * @param grille3D the grille3D to set
     */
    public void setGrille3D(Grille3D grille3D) {
        this.grille3D = grille3D;
    }
    
}
