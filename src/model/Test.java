/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Administrator
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Grille x = new Grille();
        
        
        Grille3D grilleTest = new Grille3D(x,x,x);
        grilleTest.nouvellesCases();
        System.out.println(grilleTest);               

    }

}
