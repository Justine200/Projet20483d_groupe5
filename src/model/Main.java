/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
        Grille3D oldGrille = new Grille3D();
        grilleTest.nouvellesCases();
        System.out.println(grilleTest);
        System.out.println("Score : " + grilleTest.score());

        Scanner sc=new Scanner(System.in);

        oldGrille=grilleTest.copy();
        //ArrayList<Grille3D> grilles3d = new ArrayList<Grille3D>();
        //grilles3d.add(oldGrille);
        while(!grilleTest.conditionFin()){
            System.out.println("Voulez-vous déplacer les cases vers le haut (h), vers le bas (b), vers la droite dans la même grille (d) ou vers la gauche dans la même grille (g)");
            System.out.println("Ou bien déplacer les tuiles dans la grille à droite (f) ou dans la grille à gauche (r) ?");
            System.out.println("Ecrire \"retour\" pour annuler le dernier coup, \"save\" pour sauvegarder et quitter, \"load\" pour charger une ancienne partie");
            String s = sc.nextLine();
            s.toLowerCase();
            if( ! (s.equals("b") || s.equals("bas") || s.equals("h") || s.equals("haut") || s.equals("d") || s.equals("droite") || s.equals("g") || s.equals("gauche") || s.equals("retour")|| s.equals("save")||s.equals("load")||s.equals("f")||s.equals("r"))){
                System.out.println("Veuillez écrire \"d\" pour droite, \"h\" pour haut, \"b\" pour bas et \"g\" pour gauche");
                System.out.println("Ecrire \"retour\" pour annuler le dernier coup, \"save\" pour sauvegarder et quitter, \"load\" pour charger une ancienne partie");
            }else if (s.equals("retour")|| s.equals("save")||s.equals("load")) {
                switch (s){
                    case "retour" :
                        //grilles3d = grilleTest.removeListGrille(grilles3d);
                        grilleTest = oldGrille;
                        System.out.println(grilleTest);
                        break;
                    case "load" :
                        grilleTest = new DeserGrilles3D().main();
                        break;
                    default:
                        SerializerGrilles3D sg3 = new SerializerGrilles3D(grilleTest);
                        sg3.main();
                        break;
                }
            } else {
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
                    case "f" :
                        direction = GRILLEDROITE;
                        break;
                    case "r" :
                        direction = GRILLEGAUCHE;
                        break;
                    default :
                        direction = GAUCHE;
                        break;
                }
                if(direction==GRILLEGAUCHE || direction==GRILLEDROITE){
                    grilleTest.deplacementCaseGrille3D(direction);
                }else{
                    grilleTest.deplacement(direction);
                }
                //grilles3d.add(grilleTest);
                System.out.println("Score : " + grilleTest.score());
                System.out.println("Vmax : " + grilleTest.getValeurMax());
                oldGrille=grilleTest.copy();
                //grilles3d = grilleTest.addListGrille(grilles3d,oldGrille);
                System.out.println(grilleTest);
                if(grilleTest.victoire()){
                    System.out.println("Félicitation, vous avez gagné ! ");
                    System.exit(0);
                }
            }
        }
    }
}
