/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author justineherolt
 */
public class Main implements Parametres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic hereb
        Grille3D grilleTest=new Grille3D();
        Grille3D oldGrille = new Grille3D();
        grilleTest.nouvellesCases();
        System.out.println("Score : " + grilleTest.score());

        Scanner sc=new Scanner(System.in);

        boolean estconnecte = false;
        int nbdeplacements=0;
        ConnexionBDD con = new ConnexionBDD();
        System.out.println("Voulez-vous créer un compte ? Cela permettra d'enregistrer votre score et de vous classer parmis les autres joueurs (o pour oui et n pour non)");
        System.out.println("Déjà un compte ? Connectez-vous (c pour se connecter)");
        String pseudo = sc.nextLine();
        pseudo.toLowerCase();
        while(!(pseudo.equals("oui") || pseudo.equals("o") || pseudo.equals("non") || pseudo.equals("n") || pseudo.equals("c"))){
            System.out.println("Voulez-vous créer un compte ? Cela permettra d'enregistrer votre score et de vous classer parmis les autres joueurs (o pour oui et n pour non");
            System.out.println("Déjà un compte ? Connectez-vous (c pour se connecter)");
            pseudo = sc.nextLine();
            pseudo.toLowerCase();
        }
        if(pseudo.equals("oui") || pseudo.equals("o")){
            System.out.println("Quel peudo voulez-vous prendre ? ");
            pseudo=sc.nextLine();
            while(!con.availableUsername(pseudo)){
                System.out.println("Ce pseudo est déjà utilisé, veuillez en choisir un autre");
                pseudo=sc.nextLine();
            }
            if(con.availableUsername(pseudo)){
                System.out.println("Ce pseudo est disponible ! Veuillez entrer votre mot de passe : ");
                String password=sc.nextLine();
                con.nouveauJoueur(pseudo, password);
                estconnecte=true;
            }
        }
        if(pseudo.equals("c")){
            System.out.println("Entrez votre pseudonyme : ");
            pseudo=sc.nextLine();
            System.out.println("Entrez votre mot de passe : ");
            String password = sc.nextLine();
            boolean connect = con.connect(pseudo, password);
            if(connect){
                System.out.println("Vous êtes connectés !");
            }
        }

        System.out.println(grilleTest);
        oldGrille=grilleTest.copy();
        //ArrayList<Grille3D> grilles3d = new ArrayList<Grille3D>();
        //grilles3d.add(oldGrille);

        ArrayList<Grille3D> grilles3d = new ArrayList<Grille3D>();
        while(!grilleTest.conditionFin()){
            System.out.println("Voulez-vous déplacer les cases vers le haut (h), vers le bas (b), vers la droite dans la même grille (d) ou vers la gauche dans la même grille (g)");
            System.out.println("Ou bien déplacer les tuiles dans la grille à droite (f) ou dans la grille à gauche (r) ?");
            System.out.println("Ecrire \"retour\" pour annuler le dernier coup, \"save\" pour sauvegarder et quitter, \"load\" pour charger une ancienne partie");
            String s = sc.nextLine();
            s.toLowerCase();
            if( ! (s.equals("b") || s.equals("bas") || s.equals("h") || s.equals("haut") || s.equals("d") || s.equals("droite") || s.equals("g") || s.equals("gauche") || s.equals("retour")|| s.equals("save")||s.equals("load")||s.equals("f")||s.equals("r")||s.equals("a"))){
                System.out.println("Veuillez écrire \"d\" pour droite, \"h\" pour haut, \"b\" pour bas \"g\" pour gauche, \"f\" pour aller vers la grille de droite ou \"r\" pour aller vers la grille de gauche ou \"a\" pour un coup aléatoire");
                System.out.println("Ecrire \"retour\" pour annuler le dernier coup, \"save\" pour sauvegarder et quitter, \"load\" pour charger une ancienne partie");
            }else if (s.equals("retour")|| s.equals("save")||s.equals("load")) {
                switch (s){
                    case "retour" :
                        if (grilles3d.size()!=0) {
                            grilleTest = grilles3d.get(grilles3d.size() - 1);
                            grilles3d.remove(grilles3d.size() - 1);
                            System.out.println(grilleTest);
                        }else{
                            System.out.println("retour impossible");
                        }
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
                if(s.equals("a")){
                    int random = (int) (Math.random() * 6);
                    System.out.println(random);
                    switch ((int) (random)) {
                        case 1 -> {
                            s = "h";
                            System.out.println("coup choisis : haut");
                        }
                        case 2 -> {
                            s = "b";
                            System.out.println("coup choisis : bas");
                        }
                        case 3 -> {
                            s = "d";
                            System.out.println("coup choisis : droite");
                        }
                        case 4 -> {
                            s = "g";
                            System.out.println("coup choisis : gauche");
                        }
                        case 5 -> {
                            s = "f";
                            System.out.println("coup choisis : grille à droite");
                        }
                        default -> {
                            s = "r";
                            System.out.println("coup choisis : grille à gauche");
                        }
                    }
                }
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
                oldGrille=new Grille3D(grilleTest);
                grilles3d = grilleTest.addListGrille(grilles3d,oldGrille);
                grilleTest.deplacement(direction);
                grilleTest.nouvellesCases();
                if(direction==GRILLEGAUCHE || direction==GRILLEDROITE){
                    grilleTest.deplacementCaseGrille3D(direction);
                }else{
                    grilleTest.deplacement(direction);
                }
                if(estconnecte){
                    nbdeplacements++;
                }
                //grilles3d.add(grilleTest);
                System.out.println("Score : " + grilleTest.score());
                System.out.println("Vmax : " + grilleTest.getValeurMax());
                System.out.println(grilleTest);
                if(grilleTest.victoire()){
                    System.out.println("Félicitation, vous avez gagné ! ");
                    if(estconnecte){
                        con.updateNbDeplacements(pseudo, nbdeplacements);
                        con.updateScore(pseudo, grilleTest.getScore());
                    }
                    System.out.println("Classement : ");
                    con.affichageClassement();
                    System.exit(0);
                }else{
                    grilleTest.nouvellesCases();
                }
            }
        }
    }
}
