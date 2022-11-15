/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet20483d_groupe5;

import java.util.Scanner;

/**
 *
 * @author justineherolt
 */
public class Main implements Parametres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grille3D grilleTest=new Grille3D();
        grilleTest.nouvellesCases();
        System.out.println(grilleTest);
        System.out.println("Score : " + grilleTest.score());

        Scanner sc=new Scanner(System.in);
        while(!grilleTest.conditionFin()){
            System.out.println("Voulez-vous déplacer les cases vers le haut (h), vers le bas (b), vers la droite (d) ou vers la gauche (g)");
            String s = sc.nextLine();
            s.toLowerCase();
            if( ! (s.equals("b") || s.equals("bas") || s.equals("h") || s.equals("haut") || s.equals("d") || s.equals("droite") || s.equals("g") || s.equals("gauche"))){
                System.out.println("Veuillez écrire \"d\" pour droite, \"h\" pour haut, \"b\" pour bas et \"g\" pour gauche");
            }else{
                int direction;
                switch (s){
                    case "h" :
                    case "haut" :
                        direction = HAUT;
                        break;
                    case "b" :
                    case "bas" :
                        direction = BAS;
                        break;
                    case "d" :
                    case "droite" :
                        direction = DROITE;
                        break;
                    default :
                        direction = GAUCHE;
                        break;
                }
                grilleTest.deplacement(direction);
                System.out.println("Score : " + grilleTest.score());
                System.out.println("Vmax : " + grilleTest.getValeurMax());
                grilleTest.nouvellesCases();
                System.out.println(grilleTest);
                if(grilleTest.victoire()){
                    System.out.println("Félicitation, vous avez gagné ! ");
                    System.exit(0);
                }
            }
        }
    }
}
