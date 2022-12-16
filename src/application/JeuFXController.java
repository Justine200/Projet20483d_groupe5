/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.Case;
import model.ConnexionBDD;
import model.Grille;
import model.Grille3D;
import model.Modele2048;
import static model.Parametres.BAS;
import static model.Parametres.DROITE;
import static model.Parametres.GAUCHE;
import static model.Parametres.HAUT;
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
    @FXML
    private Button startButton; // boutons
    @FXML
    private Pane movesPane;  // boutons
    @FXML
    private Text topScore;

    //variables globales non définies dans la vue (fichier .fxml)
    private ArrayList<ArrayList<Pane>> panes = new ArrayList<>();

    private ArrayList<Pane> panes0 = new ArrayList<>();
    private ArrayList<Pane> panes1 = new ArrayList<>();
    private ArrayList<Pane> panes2 = new ArrayList<>();

    private final int taillePane = 1300, hauteurPane = 650, tailleCase = 397 / 3; //taille de la fenêtre, //hauteur de la fenêtre, //taille d'une case

    private int x = 29, y = 173;//tuile de la 1ere grille
    private int x1 = 452, y1 = 173;//tuile de la 2ere grille
    private int x2 = 875, y2 = 173;//tuile de la 3ere grille

    private Modele2048 m;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //CSS
        fond.getStyleClass().add("fond");
        movesPane.getStyleClass().add("fond");
        startButton.getStyleClass().add("button");
        grille0.getStyleClass().add("gridpane");
        grille1.getStyleClass().add("gridpane");
        grille2.getStyleClass().add("gridpane");

        // Connextion BDD
        ConnexionBDD con = new ConnexionBDD();
        System.out.println(con.meilleurScore());
        topScore.setText(con.meilleurScore());

        // création du modèle
        m = new Modele2048();

        initModele(m);

    }

    /*
    *Méthode pour init une partie à partir d'un modele2048
     */
    private void initModele(Modele2048 m) {

        supprimerPanes0();
        supprimerPanes1();
        supprimerPanes2();

        //Afficher les tuiles du modele sur le view
        for (int i = 0; i < 3; i++) {

            Iterator value = m.getGrille3D().getGrilles()[i].getGrille().iterator();

            while (value.hasNext()) {

                Case cas = (Case) value.next();

                Pane p = new Pane();
                Label c = new Label("" + cas.getValeur());

                p.getStyleClass().add("pane");
                c.getStyleClass().add("tuile");
                GridPane.setHalignment(c, HPos.CENTER);
                fond.getChildren().add(p);
                p.getChildren().add(c);

                switch (i) {
                    case 0:

                        //this.grille0.add(p, cas.getX(), cas.getY());
                        switch (cas.getX()) {

                            case 0:
                                p.setLayoutX(this.x);
                                cas.setX(this.x);
                                cas.setObjectifx(this.x);
                                break;
                            case 1:
                                p.setLayoutX(this.x + this.tailleCase);
                                cas.setX(this.x + this.tailleCase);
                                cas.setObjectifx(this.x + this.tailleCase);
                                break;
                            default:
                                p.setLayoutX(this.x + this.tailleCase * 2);
                                cas.setX(this.x + this.tailleCase * 2);
                                cas.setObjectifx(this.x + this.tailleCase * 2);
                                break;
                        }
                        switch (cas.getY()) {
                            case 0:
                                p.setLayoutY(this.y);
                                cas.setY(this.y);
                                cas.setObjectify(this.y);
                                break;
                            case 1:
                                p.setLayoutY(this.y + this.tailleCase);
                                cas.setY(this.y + this.tailleCase);
                                cas.setObjectify(this.y + this.tailleCase);
                                break;
                            default:
                                p.setLayoutY(this.y + this.tailleCase * 2);
                                cas.setY(this.y + this.tailleCase * 2);
                                cas.setObjectify(this.y + this.tailleCase * 2);
                                break;
                        }
                        this.panes0.add(p);
                        break;

                    case 1:

                        //this.grille1.add(p, cas.getX(), cas.getY());
                        switch (cas.getX()) {

                            case 0:
                                p.setLayoutX(this.x1);
                                cas.setX(this.x1);
                                cas.setObjectifx(this.x1);
                                break;
                            case 1:
                                p.setLayoutX(this.x1 + this.tailleCase);
                                cas.setX(this.x1 + this.tailleCase);
                                cas.setObjectifx(this.x1 + this.tailleCase);
                                break;
                            default:
                                p.setLayoutX(this.x1 + this.tailleCase * 2);
                                cas.setX(this.x1 + this.tailleCase * 2);
                                cas.setObjectifx(this.x1 + this.tailleCase * 2);
                                break;
                        }

                        switch (cas.getY()) {
                            case 0:
                                p.setLayoutY(this.y1);
                                cas.setY(this.y1);
                                cas.setObjectify(this.y1);
                                break;
                            case 1:
                                p.setLayoutY(this.y1 + this.tailleCase);
                                cas.setY(this.y1 + this.tailleCase);
                                cas.setObjectify(this.y1 + this.tailleCase);
                                break;
                            default:
                                p.setLayoutY(this.y1 + this.tailleCase * 2);
                                cas.setY(this.y1 + this.tailleCase * 2);
                                cas.setObjectify(this.y1 + this.tailleCase * 2);
                                break;
                        }
                        this.panes1.add(p);
                        break;
                    default:

                        // this.grille2.add(p, cas.getX(), cas.getY());
                        switch (cas.getX()) {

                            case 0:
                                p.setLayoutX(this.x2);
                                cas.setX(this.x2);
                                cas.setObjectifx(this.x2);
                                break;
                            case 1:
                                p.setLayoutX(this.x2 + this.tailleCase);
                                cas.setX(this.x2 + this.tailleCase);
                                cas.setObjectifx(this.x2 + this.tailleCase);
                                break;
                            default:
                                p.setLayoutX(this.x2 + this.tailleCase * 2);
                                cas.setX(this.x2 + this.tailleCase * 2);
                                cas.setObjectifx(this.x2 + this.tailleCase * 2);
                                break;
                        }

                        switch (cas.getY()) {
                            case 0:
                                p.setLayoutY(this.y2);
                                cas.setY(this.y2);
                                cas.setObjectify(this.y2);
                                break;
                            case 1:
                                p.setLayoutY(this.y2 + this.tailleCase);
                                cas.setY(this.y2 + this.tailleCase);
                                cas.setObjectify(this.y2 + this.tailleCase);
                                break;
                            default:
                                p.setLayoutY(this.y2 + this.tailleCase * 2);
                                cas.setY(this.y2 + this.tailleCase * 2);
                                cas.setObjectify(this.y2 + this.tailleCase * 2);
                                break;
                        }
                        this.panes2.add(p);
                        break;
                }

                //cas.ObjectifToCoord();
                p.setVisible(true);
                c.setVisible(true);

            }

        }
    }

    /*
    * Méthodes pour supprimer les panes sur chaque grille.
     */
    private void supprimerPanes0() {
        fond.getChildren().removeAll(panes0);
        for (int i = 0; i < panes0.size(); i++) {
            panes0.remove(i);
        }
    }

    private void supprimerPanes1() {
        fond.getChildren().removeAll(panes1);
        for (int i = 0; i < panes1.size(); i++) {
            panes1.remove(i);
        }
    }

    private void supprimerPanes2() {
        fond.getChildren().removeAll(panes2);
        for (int i = 0; i < panes2.size(); i++) {
            panes2.remove(i);
        }
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

                Node tuile = grille0.getChildren().get(i);
                if (tuile != null) {
                    int rowIndex = GridPane.getRowIndex(tuile);
                    int rowEnd = GridPane.getRowIndex(tuile);
                }
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
    private void start(MouseEvent event) {
        System.out.println("start");

    }

    @FXML
    private void stop(MouseEvent event) {
        System.out.println("stop");

    }

    @FXML
    private void newGame(MouseEvent event) {
        System.out.println("newGame");
        this.score.setText("0");

        m = new Modele2048();
        initModele(m);
    }

    @FXML
    private void undo(MouseEvent event) {
        System.out.println("undo");
    }

    //Méthodes de MenuBar
    @FXML
    private void newGame0() {
        System.out.println("newGame0");
        this.score.setText("0");

        m = new Modele2048();
        initModele(m);
    }

    @FXML
    private void save() {
        System.out.println("save");
    }

    @FXML
    private void load() {
        System.out.println("load");
    }

    @FXML
    private void close() {
        System.exit(0);
    }

    @FXML
    private void cssClassique() {
        System.out.println("cssClassique");
    }

    @FXML
    private void cssDark() {
        System.out.println("cssDark");
    }

    @FXML
    private void howToPlay() {
        System.out.println("howToPlay");
    }

    @FXML
    private void aboutUs() {
        System.out.println("aboutUs");
    }

    private void nouvelleCase() {
        System.out.println("start");

        Random ra1 = new Random();
        Random ra2 = new Random();

        int x = ra1.nextInt(3);
        int y = ra2.nextInt(3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == x && j == y) {

                    Pane a = new Pane();
                    Label z = new Label(x + "," + y);

                    a.getStyleClass().add("pane");
                    z.getStyleClass().add("tuile");
                    GridPane.setHalignment(z, HPos.CENTER);
                    fond.getChildren().add(a);
                    a.getChildren().add(z);

                    a.setLayoutX(x);
                    a.setLayoutY(y);

                    a.setVisible(true);
                    z.setVisible(true);

                    this.grille0.add(a, i, j);
                    System.out.println("X : " + a.getLayoutX());
                    System.out.println("Y : " + a.getLayoutY());
                }
            }
        }
        System.out.println(x);
        System.out.println(y);

    }

    // Apparition tuile début    
    private void apparitionTuiles(KeyEvent but) {
        System.out.println("ApparitionTuiles");

        Random r1 = new Random();
        Random r2 = new Random();
        Random r3 = new Random();
        Random r4 = new Random();

        int x = r1.nextInt(3);// variable pour choisir aléatoirement à quelle coordonées de x la tuile va apparaitre
        int y = r2.nextInt(3);// variable pour choisir aléatoirement à quelle coordonées de y la tuile va apparaitre
        int numGrille = r3.nextInt(3);// variable pour choisir aléatoirement dans quelle grille la tuille va apparaitre

        int nbSpawnChoisi = (1 + r4.nextInt(2)) * 2; // variable qui vient choisir aléatoirement 2 ou 4 dans la liste nbspawn
        String nbSpawnChoisi2 = String.valueOf(nbSpawnChoisi); // On convertie nbSpawnChoisi en String pour qu'il puisse être accepté pour le Label 

        switch (numGrille) {
            case 1: // la tuile va apparaitre dans la grille 0
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (i == x && j == y) {
                            Pane a = new Pane();
                            Label z = new Label(nbSpawnChoisi2);

                            a.getStyleClass().add("pane");
                            z.getStyleClass().add("tuile");
                            GridPane.setHalignment(z, HPos.CENTER);
                            fond.getChildren().add(a);
                            a.getChildren().add(z);

                            a.setLayoutX(x);
                            a.setLayoutY(y);

                            a.setVisible(true);
                            z.setVisible(true);

                            this.grille0.add(a, i, j);
                        }
                    }
                }
                break;
            case 2: // La tuile va apparaitre dans la grille 1
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (i == x && j == y) {
                            Pane a = new Pane();
                            Label z = new Label(nbSpawnChoisi2);

                            a.getStyleClass().add("pane");
                            z.getStyleClass().add("tuile");
                            GridPane.setHalignment(z, HPos.CENTER);
                            fond.getChildren().add(a);
                            a.getChildren().add(z);

                            a.setLayoutX(x);
                            a.setLayoutY(y);

                            a.setVisible(true);
                            z.setVisible(true);

                            this.grille1.add(a, i, j);
                        }
                    }
                }
                break;
            case 3: // La tuile va appaitre dans la grille 2
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (i == x && j == y) {
                            Pane a = new Pane();
                            Label z = new Label(nbSpawnChoisi2);

                            a.getStyleClass().add("pane");
                            z.getStyleClass().add("tuile");
                            GridPane.setHalignment(z, HPos.CENTER);
                            fond.getChildren().add(a);
                            a.getChildren().add(z);

                            a.setLayoutX(x);
                            a.setLayoutY(y);

                            a.setVisible(true);
                            z.setVisible(true);

                            this.grille2.add(a, i, j);
                        }
                    }
                }

        }

    }
//Apparition tuile fin

    /**
     *
     * @param ke
     */
    @FXML
    private void keyPressed(KeyEvent ke) throws InterruptedException {

        System.out.println("touche appuyée");

        String touche = ke.getText();

        //Grille 0
        if (touche.compareTo("q") == 0) { // utilisateur appuie sur "q" pour envoyer la tuile vers la gauche

            Iterator value = m.getGrille3D().getGrilles()[0].getGrille().iterator();

            while (value.hasNext()) {

                Case cas = (Case) value.next();

                if (cas.getObjectifx() > 29) { // possible uniquement si on est pas dans la colonne la plus à gauche
                    cas.setObjectifx(cas.getObjectifx() - this.tailleCase); // on définit la position que devra atteindre la tuile en abscisse (modèle). Le thread se chargera de mettre la vue à jour
                }

                System.out.println(cas.toString() + ".getObjectifx() : " + cas.getObjectifx());
            }

        } else if (touche.compareTo("d") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite

            Iterator value = m.getGrille3D().getGrilles()[0].getGrille().iterator();

            while (value.hasNext()) {

                Case cas = (Case) value.next();

                if (cas.getObjectifx() < (int) this.taillePane - 8 * this.tailleCase - 24 * 3) { // possible uniquement si on est pas dans la colonne la plus à droite (taille de la fenêtre - 10*taille d'une case - taille entre la grille et le bord de la fenêtre*3)
                    cas.setObjectifx(cas.getObjectifx() + (int) this.tailleCase);

                }
                score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
            }

        } else if (touche.compareTo("z") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile en haut

            Iterator value = m.getGrille3D().getGrilles()[0].getGrille().iterator();

            while (value.hasNext()) {

                Case cas = (Case) value.next();

                if (cas.getObjectify() > 173) {
                    cas.setObjectify(cas.getObjectify() - (int) this.tailleCase);
                }
                score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
            }

        } else if (touche.compareTo("s") == 0) { // utilisateur appuie sur "s" pour envoyer la tuile en bas

            Iterator value = m.getGrille3D().getGrilles()[0].getGrille().iterator();

            while (value.hasNext()) {

                Case cas = (Case) value.next();

                if (cas.getObjectify() < (int) this.taillePane - 6 * this.tailleCase - 24 * 3) {
                    cas.setObjectify(cas.getObjectify() + (int) this.tailleCase);
                }
                score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
            }

        }

        //Grille 1
        if (touche.compareTo("q") == 0) { // utilisateur appuie sur "q" pour envoyer la tuile vers la gauche

            Iterator value = m.getGrille3D().getGrilles()[1].getGrille().iterator();

            while (value.hasNext()) {

                Case cas = (Case) value.next();

                if (cas.getObjectifx() > 452) { // possible uniquement si on est pas dans la colonne la plus à gauche
                    cas.setObjectifx(cas.getObjectifx() - this.tailleCase); // on définit la position que devra atteindre la tuile en abscisse (modèle). Le thread se chargera de mettre la vue à jour
                }

                System.out.println(cas.toString() + ".getObjectifx() : " + cas.getObjectifx());
            }

        } else if (touche.compareTo("d") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite

            Iterator value = m.getGrille3D().getGrilles()[1].getGrille().iterator();

            while (value.hasNext()) {

                Case cas = (Case) value.next();

                if (cas.getObjectifx() < (int) this.taillePane - 5 * this.tailleCase - 24 * 2) { // possible uniquement si on est pas dans la colonne la plus à droite (taille de la fenêtre - 10*taille d'une case - taille entre la grille et le bord de la fenêtre*3)
                    cas.setObjectifx(cas.getObjectifx() + (int) this.tailleCase);

                }
                score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
            }

        } else if (touche.compareTo("z") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile en haut

            Iterator value = m.getGrille3D().getGrilles()[1].getGrille().iterator();

            while (value.hasNext()) {

                Case cas = (Case) value.next();

                if (cas.getObjectify() > 173) {
                    cas.setObjectify(cas.getObjectify() - (int) this.tailleCase);
                }
                score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
            }

        } else if (touche.compareTo("s") == 0) { // utilisateur appuie sur "s" pour envoyer la tuile en bas

            Iterator value = m.getGrille3D().getGrilles()[1].getGrille().iterator();

            while (value.hasNext()) {

                Case cas = (Case) value.next();

                if (cas.getObjectify() < (int) this.taillePane - 6 * this.tailleCase - 24 * 3) {
                    cas.setObjectify(cas.getObjectify() + (int) this.tailleCase);
                }
                score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
            }

        }

        //Grille 2
        if (touche.compareTo("q") == 0) { // utilisateur appuie sur "q" pour envoyer la tuile vers la gauche

            Iterator value = m.getGrille3D().getGrilles()[2].getGrille().iterator();

            while (value.hasNext()) {

                Case cas = (Case) value.next();

                if (cas.getObjectifx() > 875) { // possible uniquement si on est pas dans la colonne la plus à gauche
                    cas.setObjectifx(cas.getObjectifx() - this.tailleCase); // on définit la position que devra atteindre la tuile en abscisse (modèle). Le thread se chargera de mettre la vue à jour
                }

                System.out.println(cas.toString() + ".getObjectifx() : " + cas.getObjectifx());
            }

        } else if (touche.compareTo("d") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite

            Iterator value = m.getGrille3D().getGrilles()[2].getGrille().iterator();

            while (value.hasNext()) {

                Case cas = (Case) value.next();

                if (cas.getObjectifx() < (int) this.taillePane - 2 * this.tailleCase - 24) { // possible uniquement si on est pas dans la colonne la plus à droite (taille de la fenêtre - 10*taille d'une case - taille entre la grille et le bord de la fenêtre*3)
                    cas.setObjectifx(cas.getObjectifx() + (int) this.tailleCase);

                }
                score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
            }

        } else if (touche.compareTo("z") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile en haut

            Iterator value = m.getGrille3D().getGrilles()[2].getGrille().iterator();

            while (value.hasNext()) {

                Case cas = (Case) value.next();

                if (cas.getObjectify() > 173) {
                    cas.setObjectify(cas.getObjectify() - (int) this.tailleCase);
                }
                score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
            }

        } else if (touche.compareTo("s") == 0) { // utilisateur appuie sur "s" pour envoyer la tuile en bas

            Iterator value = m.getGrille3D().getGrilles()[2].getGrille().iterator();

            while (value.hasNext()) {

                Case cas = (Case) value.next();

                if (cas.getObjectify() < (int) this.taillePane - 6 * this.tailleCase - 24 * 3) {
                    cas.setObjectify(cas.getObjectify() + (int) this.tailleCase);
                }
                score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
            }

        }

        Task task0 = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue

            @Override
            public Void call() throws Exception { // implémentation de la méthode protected abstract V call() dans la classe Task

                Iterator value = m.getGrille3D().getGrilles()[0].getGrille().iterator();

                while (value.hasNext()) {

                    Case cas = (Case) value.next();

                    boolean allmoved = false;

                    for (int i = 0; i < panes0.size(); i++) {

                        if (cas.getX() == panes0.get(i).getLayoutX() && cas.getY() == panes0.get(i).getLayoutY()) {

                            while (cas.getX() != cas.getObjectifx() || cas.getY() != cas.getObjectify()) { // si les tuiles n'est pas à la place qu'on souhaite attendre en abscisse
                                if (cas.getX() < cas.getObjectifx()) {
                                    cas.setX(cas.getX() + 1);// si on va vers la droite, on modifie la position des tuiles pixel par pixel vers la droite
                                } else if (cas.getY() < cas.getObjectify()) {
                                    cas.setY(cas.getY() + 1);
                                } else if (cas.getX() > cas.getObjectifx()) {
                                    cas.setX(cas.getX() - 1);
                                } else if (cas.getY() > cas.getObjectify()) {
                                    cas.setY(cas.getY() - 1);
                                }

                                int index = i;

                                Platform.runLater(new Runnable() { // classe anonyme
                                    @Override
                                    public void run() {
                                        panes0.get(index).relocate(cas.getX(), cas.getY()); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                                        panes0.get(index).setVisible(true);
                                    }
                                }
                                );

                                Thread.sleep(2);

                            }
                        }

                    }

                }

                return null;
            }

        };
        Task task1 = new Task<Void>() {

            // on définit une tâche parallèle pour mettre à jour la vue
            // CountDownLatch latch = new CountDownLatch(panes0.size());
            @Override
            public Void call() throws Exception { // implémentation de la méthode protected abstract V call() dans la classe Task

                Iterator value = m.getGrille3D().getGrilles()[1].getGrille().iterator();

                while (value.hasNext()) {

                    Case cas = (Case) value.next();

                    boolean allmoved = false;

                    for (int i = 0; i < panes1.size(); i++) {

                        if (cas.getX() == panes1.get(i).getLayoutX() && cas.getY() == panes1.get(i).getLayoutY()) {

                            while (cas.getX() != cas.getObjectifx() || cas.getY() != cas.getObjectify()) { // si les tuiles n'est pas à la place qu'on souhaite attendre en abscisse
                                if (cas.getX() < cas.getObjectifx()) {
                                    cas.setX(cas.getX() + 1);// si on va vers la droite, on modifie la position des tuiles pixel par pixel vers la droite
                                } else if (cas.getY() < cas.getObjectify()) {
                                    cas.setY(cas.getY() + 1);
                                } else if (cas.getX() > cas.getObjectifx()) {
                                    cas.setX(cas.getX() - 1);
                                } else if (cas.getY() > cas.getObjectify()) {
                                    cas.setY(cas.getY() - 1);
                                }

                                int index = i;

                                Platform.runLater(new Runnable() { // classe anonyme
                                    @Override
                                    public void run() {
                                        panes1.get(index).relocate(cas.getX(), cas.getY()); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                                        panes1.get(index).setVisible(true);
                                    }
                                }
                                );

                                Thread.sleep(2);

                            }
                        }

                    }

                }

                return null;

            }

        };

        Task task2 = new Task<Void>() {
            @Override
            public Void call() throws Exception { // implémentation de la méthode protected abstract V call() dans la classe Task

                Iterator value = m.getGrille3D().getGrilles()[2].getGrille().iterator();

                while (value.hasNext()) {

                    Case cas = (Case) value.next();

                    boolean allmoved = false;

                    for (int i = 0; i < panes2.size(); i++) {

                        if (cas.getX() == panes2.get(i).getLayoutX() && cas.getY() == panes2.get(i).getLayoutY()) {

                            while (cas.getX() != cas.getObjectifx() || cas.getY() != cas.getObjectify()) { // si les tuiles n'est pas à la place qu'on souhaite attendre en abscisse
                                if (cas.getX() < cas.getObjectifx()) {
                                    cas.setX(cas.getX() + 1);// si on va vers la droite, on modifie la position des tuiles pixel par pixel vers la droite
                                } else if (cas.getY() < cas.getObjectify()) {
                                    cas.setY(cas.getY() + 1);
                                } else if (cas.getX() > cas.getObjectifx()) {
                                    cas.setX(cas.getX() - 1);
                                } else if (cas.getY() > cas.getObjectify()) {
                                    cas.setY(cas.getY() - 1);
                                }

                                int index = i;

                                Platform.runLater(new Runnable() { // classe anonyme
                                    @Override
                                    public void run() {
                                        panes2.get(index).relocate(cas.getX(), cas.getY()); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                                        panes2.get(index).setVisible(true);
                                    }
                                }
                                );

                                Thread.sleep(2);

                            }
                        }

                    }

                }

                return null;

            }

        };

        Thread th0 = new Thread(task0); // on crée un contrôleur de Thread
        Thread th1 = new Thread(task1);
        Thread th2 = new Thread(task2);

        th0.setDaemon(true);// le Thread s'exécutera en arrière-plan (démon informatique)
        th1.setDaemon(true);
        th2.setDaemon(true);

        th0.start(); // et on exécute le Thread pour mettre à jour la vue (déplacement continu de la tuile horizontalement)
        th1.start();
        th2.start();

    }
}
