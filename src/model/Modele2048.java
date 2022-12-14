/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Modele2048 {
    private Grille3D grille3D = new Grille3D(); 
    

    public Modele2048(){
        
        this.grille3D.nouvellesCases();
        
        System.out.println(this.grille3D);

        /*Random ra = new Random();
        grille[ra.nextInt(3)][ra.nextInt(3)]=(1+ra.nextInt(2))*2; 
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel déplacement souhaitez-vous ?");
        String s = sc.nextLine();
        if (s.equals("q")) {
            System.out.println("Déplacement vers la gauche");
        }*/
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
