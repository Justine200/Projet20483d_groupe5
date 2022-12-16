
package model;

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
