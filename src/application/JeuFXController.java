/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Case;
import model.Grille;
import model.Modele2048;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;

/**
 *
 * @author Admin
 */
public class JeuFXController implements Initializable {

    /*
     * Variables globales correspondant à des objets définis dans la vue (fichier .fxml)
     * Ces variables sont ajoutées à la main et portent le même nom que les fx:id dans Scene Builder
     */
    @FXML
    private Label score; // value will be injected by the FXMLLoader
    @FXML
    private GridPane grille0; //permiere grille 
    @FXML
    private GridPane grille1; //2eme grille 
    @FXML
    private GridPane grille2; //3eme grille 
    @FXML
    private Pane fond; // panneau recouvrant toute la fenêtre

    //variables globales non définies dans la vue (fichier .fxml)
    private final Pane p = new Pane(); // panneau utilisé pour dessiner une tuile "2"
    private final Label c = new Label("0");

    private final Pane p1 = new Pane(); // panneau utilisé pour dessiner une tuile "2"
    private final Label c1 = new Label("1");

    private final Pane p2 = new Pane(); // panneau utilisé pour dessiner une tuile "2"
    private final Label c2 = new Label("2");

    private int x = 29, y = 173;//tuile de la 1ere grille
    private int x1 = 452, y1 = 173;//tuile de la 2ere grille
    private int x2 = 875, y2 = 173;//tuile de la 3ere grille

    private final int taillePane = 1300, hauteurPanes = 650, tailleCase = 397 / 4; //taille de la fenêtre, //hauteur de la fenêtre, //taille d'une case

    private int objectifx = 29, objectify = 173;
    private int objectifx1 = 452, objectify1 = 173;
    private int objectifx2 = 875, objectify2 = 173;

    private Modele2048 m;

    private final GridPane[] grille3D = new GridPane[]{ //grille3D (array contient les 3 grilles)
        grille0,
        grille1,
        grille2
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        System.out.println("le contrôleur initialise la vue");
        fond.getStyleClass().add("fond");

        // utilisation de styles pour la grille et la tuile (voir styles.css)
        p.getStyleClass().add("pane");
        c.getStyleClass().add("tuile");
        grille0.getStyleClass().add("gridpane");
        GridPane.setHalignment(c, HPos.CENTER);
        fond.getChildren().add(p);
        p.getChildren().add(c);
        // on place la tuile en précisant les coordonnées (x,y) du coin supérieur gauche
        p.setLayoutX(x);
        p.setLayoutY(y);
        p.setVisible(true);
        c.setVisible(true);

        // utilisation de styles pour la grille et la tuile (voir styles.css)
        p1.getStyleClass().add("pane");
        c1.getStyleClass().add("tuile");
        grille1.getStyleClass().add("gridpane");
        GridPane.setHalignment(c1, HPos.CENTER);
        fond.getChildren().add(p1);
        p1.getChildren().add(c1);
        // on place la tuile en précisant les coordonnées (x,y) du coin supérieur gauche
        p1.setLayoutX(x1);
        p1.setLayoutY(y1);
        p1.setVisible(true);
        c1.setVisible(true);

        // utilisation de styles pour la grille et la tuile (voir styles.css)
        p2.getStyleClass().add("pane");
        c2.getStyleClass().add("tuile");
        grille2.getStyleClass().add("gridpane");
        GridPane.setHalignment(c2, HPos.CENTER);
        fond.getChildren().add(p2);
        p2.getChildren().add(c2);
        // on place la tuile en précisant les coordonnées (x,y) du coin supérieur gauche
        p2.setLayoutX(x2);
        p2.setLayoutY(y2);
        p2.setVisible(true);
        c2.setVisible(true);

        //fond.getScene().setOnKeyPressed(this::keyPressed);
        // création du modèle
        m = new Modele2048();
    }

    /*
     * Méthodes listeners pour gérer les événements (portent les mêmes noms que
     * dans Scene Builder
     */
    @FXML
    private void handleDragAction(MouseEvent event) {
        System.out.println("Glisser/déposer sur la grille avec la souris");
        double x = event.getX();//translation en abscisse
        double y = event.getY();//translation en ordonnée
        if (x > y) {
            for (int i = 0; i < grille0.getChildren().size(); i++) { //pour chaque colonne
                //for (int j = 0; j < grille.getRowConstraints().size(); j++) { //pour chaque ligne
                System.out.println("ok1");
                grille0.getChildren().remove(i);
                System.out.println(" X : " + grille0.getChildren().get(i).getLayoutX());
                System.out.println(" Y : " + grille0.getChildren().get(i).getLayoutY());

                /*Node tuile = grille.getChildren().get(i);
                 if (tuile != null) {
                 int rowIndex = GridPane.getRowIndex(tuile);
                 int rowEnd = GridPane.getRowIndex(tuile);
                 }*/
                // }
            }
        } else if (x < y) {
            System.out.println("ok2");
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Pane p = new Pane();
                    p.getStyleClass().add("pane");
                    grille0.add(p, i, j);
                    p.setVisible(true);
                    grille0.getStyleClass().add("gridpane");
                }
            }
        }
    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
        System.out.println("Clic de souris sur le bouton menu");
    }

    /**
     *
     * @param ke
     */
    @FXML
    private void keyPressed(KeyEvent ke) {

        System.out.println("touche appuyée");

        String touche = ke.getText();
        //Grille 0
        if (touche.compareTo("q") == 0) { // utilisateur appuie sur "q" pour envoyer la tuile vers la gauche
            if (objectifx > 29) { // possible uniquement si on est pas dans la colonne la plus à gauche
                objectifx -= (int) this.tailleCase; // on définit la position que devra atteindre la tuile en abscisse (modèle). Le thread se chargera de mettre la vue à jour
                score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1)); // mise à jour du compteur de mouvement
            }
        } else if (touche.compareTo("d") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite
            if (objectifx < (int) this.taillePane - 10 * this.tailleCase - 24 * 3) { // possible uniquement si on est pas dans la colonne la plus à droite (taille de la fenêtre - 10*taille d'une case - taille entre la grille et le bord de la fenêtre*3)
                objectifx += (int) this.tailleCase;
                score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
            }
        }

        //Grille 1
        if (touche.compareTo("q") == 0) { // utilisateur appuie sur "q" pour envoyer la tuile vers la gauche
            if (objectifx1 > 452) { // 452 : x de la grille1
                objectifx1 -= (int) this.tailleCase; 
            }
        } else if (touche.compareTo("d") == 0) { //vers la droite
            if (objectifx1 < (int) this.taillePane - 6 * this.tailleCase - 24 * 2) { // possible uniquement si on est pas dans la colonne la plus à droite (taille de la fenêtre - 6*taille d'une case - taille entre la grille et le bord de la fenêtre*2)
                objectifx1 += (int) this.tailleCase;
            }
        }

        //Grille 2
        if (touche.compareTo("q") == 0) { // utilisateur appuie sur "q" pour envoyer la tuile vers la gauche
            if (objectifx2 > 875) { // 875 : x de la grille2
                objectifx2 -= (int) this.tailleCase;
            }
        } else if (touche.compareTo("d") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite
            if (objectifx2 < (int) this.taillePane - 2 * this.tailleCase - 24) { // possible uniquement si on est pas dans la colonne la plus à droite (taille de la fenêtre - 2*taille d'une case - taille entre la grille et le bord de la fenêtre)
                objectifx2 += (int) this.tailleCase;
            }
        }

        System.out.println("objectifx=" + objectifx);
        System.out.println("objectifx1=" + objectifx1);
        System.out.println("objectifx2=" + objectifx2);

        Task task = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue
            @Override
            public Void call() throws Exception { // implémentation de la méthode protected abstract V call() dans la classe Task
                while (x != objectifx && x1 != objectifx1 && x2 != objectifx2) { // si les tuiles n'est pas à la place qu'on souhaite attendre en abscisse
                    if (x < objectifx && x1 < objectifx1 && x2 < objectifx2) {
                        x += 1; // si on va vers la droite, on modifie la position des tuiles pixel par pixel vers la droite
                        x1 += 1;
                        x2 += 1;
                    } else {
                        x -= 1; // si on va vers la gauche, idem en décrémentant la valeur de x
                        x1 -= 1;
                        x2 -= 1;
                    }
                    // Platform.runLater est nécessaire en JavaFX car la GUI ne peut être modifiée que par le Thread courant, contrairement à Swing où on peut utiliser un autre Thread pour ça
                    Platform.runLater(new Runnable() { // classe anonyme
                        @Override
                        public void run() {
                            //javaFX operations should go here
                            //System.out.println("déplacement en cours");

                            p.relocate(x, y); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                            p1.relocate(x1, y1);
                            p2.relocate(x2, y2);

                            p.setVisible(true);
                            p1.setVisible(true);
                            p2.setVisible(true);
                        }
                    }
                    );
                    //Thread.sleep(5);  **Decalage aux positions des tuiles après le thread!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                } // end while
                return null; // la méthode call doit obligatoirement retourner un objet. Ici on n'a rien de particulier à retourner. Du coup, on utilise le type Void (avec un V majuscule) : c'est un type spécial en Java auquel on ne peut assigner que la valeur null
            } // end call

        };
        Thread th = new Thread(task); // on crée un contrôleur de Thread
        th.setDaemon(true); // le Thread s'exécutera en arrière-plan (démon informatique)
        th.start(); // et on exécute le Thread pour mettre à jour la vue (déplacement continu de la tuile horizontalement)
    }
}
