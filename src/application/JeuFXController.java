package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Case;
import model.ConnexionBDD;
import model.Modele2048;
import static model.Parametres.BAS;
import static model.Parametres.DROITE;
import static model.Parametres.GAUCHE;
import static model.Parametres.GRILLEDROITE;
import static model.Parametres.GRILLEGAUCHE;
import static model.Parametres.HAUT;
import static model.Parametres.GRILLEGAUCHE;
import static model.Parametres.GRILLEDROITE;

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
    //List qui garde les panes de la grille3D
    private ArrayList<ArrayList<Pane>> panes = new ArrayList<>();

    //Lists qui gardent les panes des 3 grilles 
    private ArrayList<Pane> panes0 = new ArrayList<>();
    private ArrayList<Pane> panes1 = new ArrayList<>();
    private ArrayList<Pane> panes2 = new ArrayList<>();

    // True s'il y a au moins une case qui s'est déplacée
    private boolean uneCaseDeplace = false;

    /**
     * taille de la fenêtre, //hauteur de la fenêtre, //taille d'une case
     */
    public static final int taillePane = 1300, hauteurPane = 650, tailleCase = 397 / 3;

    public static final int x = 29, y = 173;//tuile de la 1ere grille
    public static final int x1 = 452, y1 = 173;//tuile de la 2ere grille
    public static final int x2 = 875, y2 = 173;//tuile de la 3ere grille

    /**
     * le modele du jeu
     */
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
            m.getGrille3D().getGrille(i).setGrilleNum(i);
            Iterator value = m.getGrille3D().getGrilles()[i].getGrille().iterator();

            while (value.hasNext()) {

                Case cas = (Case) value.next();

                Pane p = new Pane();
                Label c = new Label("" + cas.getValeur());

                p.getStyleClass().add("pane");
                c.getStyleClass().add("tuile");

                if (cas.getValeur() >= 10) {
                    c.getStyleClass().add("tuile10");
                } else if (cas.getValeur() >= 63) {
                    c.getStyleClass().add("tuile64");
                } else if (cas.getValeur() >= 99) {
                    c.getStyleClass().add("tuile100");
                } else if (cas.getValeur() >= 511) {
                    c.getStyleClass().add("tuile512");
                } else if (cas.getValeur() >= 999) {
                    c.getStyleClass().add("tuile1000");
                }

                //GridPane.setHalignment(c, HPos.CENTER);
                fond.getChildren().add(p);
                p.getChildren().add(c);

                switch (i) {
                    case 0:

                        //this.grille0.add(p, cas.getX(), cas.getY());
                        switch (cas.getX()) {

                            case 0:
                                p.setLayoutX(this.x);
                                cas.setxInterface(this.x);
                                cas.setObjectifx(this.x);
                                break;
                            case 1:
                                p.setLayoutX(this.x + this.tailleCase);
                                cas.setxInterface(this.x + this.tailleCase);
                                cas.setObjectifx(this.x + this.tailleCase);
                                break;
                            default:
                                p.setLayoutX(this.x + this.tailleCase * 2);
                                cas.setxInterface(this.x + this.tailleCase * 2);
                                cas.setObjectifx(this.x + this.tailleCase * 2);
                                break;
                        }
                        switch (cas.getY()) {
                            case 0:
                                p.setLayoutY(this.y);
                                cas.setyInterface(this.y);
                                cas.setObjectify(this.y);
                                break;
                            case 1:
                                p.setLayoutY(this.y + this.tailleCase);
                                cas.setyInterface(this.y + this.tailleCase);
                                cas.setObjectify(this.y + this.tailleCase);
                                break;
                            default:
                                p.setLayoutY(this.y + this.tailleCase * 2);
                                cas.setyInterface(this.y + this.tailleCase * 2);
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
                                cas.setxInterface(this.x1);
                                cas.setObjectifx(this.x1);
                                break;
                            case 1:
                                p.setLayoutX(this.x1 + this.tailleCase);
                                cas.setxInterface(this.x1 + this.tailleCase);
                                cas.setObjectifx(this.x1 + this.tailleCase);
                                break;
                            default:
                                p.setLayoutX(this.x1 + this.tailleCase * 2);
                                cas.setxInterface(this.x1 + this.tailleCase * 2);
                                cas.setObjectifx(this.x1 + this.tailleCase * 2);
                                break;
                        }

                        switch (cas.getY()) {
                            case 0:
                                p.setLayoutY(this.y1);
                                cas.setyInterface(this.y1);
                                cas.setObjectify(this.y1);
                                break;
                            case 1:
                                p.setLayoutY(this.y1 + this.tailleCase);
                                cas.setyInterface(this.y1 + this.tailleCase);
                                cas.setObjectify(this.y1 + this.tailleCase);
                                break;
                            default:
                                p.setLayoutY(this.y1 + this.tailleCase * 2);
                                cas.setyInterface(this.y1 + this.tailleCase * 2);
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
                                cas.setxInterface(this.x2);
                                cas.setObjectifx(this.x2);
                                break;
                            case 1:
                                p.setLayoutX(this.x2 + this.tailleCase);
                                cas.setxInterface(this.x2 + this.tailleCase);
                                cas.setObjectifx(this.x2 + this.tailleCase);
                                break;
                            default:
                                p.setLayoutX(this.x2 + this.tailleCase * 2);
                                cas.setxInterface(this.x2 + this.tailleCase * 2);
                                cas.setObjectifx(this.x2 + this.tailleCase * 2);
                                break;
                        }

                        switch (cas.getY()) {
                            case 0:
                                p.setLayoutY(this.y2);
                                cas.setyInterface(this.y2);
                                cas.setObjectify(this.y2);
                                break;
                            case 1:
                                p.setLayoutY(this.y2 + this.tailleCase);
                                cas.setyInterface(this.y2 + this.tailleCase);
                                cas.setObjectify(this.y2 + this.tailleCase);
                                break;
                            default:
                                p.setLayoutY(this.y2 + this.tailleCase * 2);
                                cas.setyInterface(this.y2 + this.tailleCase * 2);
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
        //this.nouvelleCase(this.m);
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

    /*
    *Méthodes pour changer le style de la fenetre
     */
    @FXML
    private void cssClassique() {
        this.fond.getScene().getStylesheets().remove("/application/style/Dark.css");
        this.fond.getScene().getStylesheets().add("/application/style/JeuStyle.css");
    }

    @FXML
    private void cssDark() {
        this.fond.getScene().getStylesheets().remove("/application/style/JeuStyle.css");
        this.fond.getScene().getStylesheets().add("/application/style/Dark.css");
    }

    /*
    * Méthodes qui lancent des fenetres nouvelles
     */
    @FXML
    private void howToPlay() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/application/HowToPlay.fxml"));

        Scene sceneJeu = new Scene(root);

        Stage stage = new Stage(); //stage du jeu

        boolean add = sceneJeu.getStylesheets().add("/application/style/JeuStyle.css");

        stage.setScene(sceneJeu);

        stage.getIcons().add(new Image("/application/style/icon.png"));

        stage.setTitle("HOW TO PLAY?");

        stage.resizableProperty().setValue(false);

        stage.show();
    }

    @FXML
    private void aboutUs() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/application/aboutUs.fxml"));

        Scene sceneJeu = new Scene(root);

        Stage stage = new Stage(); //stage du jeu

        boolean add = sceneJeu.getStylesheets().add("/application/style/JeuStyle.css");

        stage.setScene(sceneJeu);

        stage.getIcons().add(new Image("/application/style/icon.png"));

        stage.setTitle("About Us :)");

        stage.resizableProperty().setValue(false);

        stage.show();
    }

    /**
     * Méthode pour ajouter une nouvelle case sur la grille3D et mettre
     * l'interface à jour
     */
    private void nouvelleCase(Modele2048 m) {

        m.getGrille3D().nouvellesCases();
        initModele(this.m);
        System.out.println(this.m.getGrille3D());

    }

    /**
     *
     * La méthode qui déplace les cases sur l'interface et le modèle2048 après
     * appuyer sur Q,D,Z,S
     */
    @FXML
    private void keyPressed(KeyEvent ke) throws InterruptedException {

        System.out.println("touche appuyée");

        String touche = ke.getText();

        if (touche.compareTo("q") == 0) { // utilisateur appuie sur "q" pour envoyer la tuile vers la gauche
            for (int i = 0; i < 3; i++) {// boucle qui parcours les 3 grilles
                if (this.m.getGrille3D().getGrille(i).lanceurDeplacerCases(GAUCHE)) { //Déplacer les cases sur le modele2048 (sur le console)

                    Iterator value = m.getGrille3D().getGrilles()[i].getGrille().iterator();

                    while (value.hasNext()) {

                        Case cas = (Case) value.next();

                        cas.updateObjectif(i); //changer l'objectif de chaque case dans l'HashSet après deplacer les cases sur le console
                    }

                    this.uneCaseDeplace = true; // True s'il y a au moins une case qui s'est déplacée
                }

            }
            if (this.uneCaseDeplace) {
                score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1)); // s'il y a au moins une case qui s'est déplacée ==> score =+ 1
                this.uneCaseDeplace = false;
            }

        } else if (touche.compareTo("d") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite
            for (int i = 0; i < 3; i++) {
                if (this.m.getGrille3D().getGrille(i).lanceurDeplacerCases(DROITE)) {

                    Iterator value = m.getGrille3D().getGrilles()[i].getGrille().iterator();

                    while (value.hasNext()) {

                        Case cas = (Case) value.next();

                        cas.updateObjectif(i);
                    }
                    this.uneCaseDeplace = true;
                }
            }
            if (this.uneCaseDeplace) {
                score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
                this.uneCaseDeplace = false;
            }
        } else if (touche.compareTo("z") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile en haut
            for (int i = 0; i < 3; i++) {
                if (this.m.getGrille3D().getGrille(i).lanceurDeplacerCases(HAUT)) {

                    Iterator value = m.getGrille3D().getGrilles()[i].getGrille().iterator();

                    while (value.hasNext()) {

                        Case cas = (Case) value.next();

                        cas.updateObjectif(i);

                    }
                    this.uneCaseDeplace = true;
                }
            }
            if (this.uneCaseDeplace) {
                score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
                this.uneCaseDeplace = false;
            }
        } else if (touche.compareTo("s") == 0) { // utilisateur appuie sur "s" pour envoyer la tuile en bas
            for (int i = 0; i < 3; i++) {
                if (this.m.getGrille3D().getGrille(i).lanceurDeplacerCases(BAS)) {

                    Iterator value = m.getGrille3D().getGrilles()[i].getGrille().iterator();

                    while (value.hasNext()) {

                        Case cas = (Case) value.next();

                        cas.updateObjectif(i);

                    }
                    this.uneCaseDeplace = true;
                }
            }
            if (this.uneCaseDeplace) {
                score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
                this.uneCaseDeplace = false;
            }
        } else if (touche.compareTo("a") == 0) { // utilisateur appuie sur "s" pour envoyer la tuile en bas
<<<<<<< Updated upstream
                this.m.getGrille3D().deplacementCaseGrille3D(GRILLEGAUCHE);

                for(int i=0;i<3;i++){
                    Iterator value = m.getGrille3D().getGrilles()[i].getGrille().iterator();
                    while (value.hasNext()) {

                        Case cas = (Case) value.next();

                        cas.updateObjectif(i);

                    }
                }
        } else if (touche.compareTo("e") == 0) { // utilisateur appuie sur "s" pour envoyer la tuile en bas
                this.m.getGrille3D().deplacementCaseGrille3D(GRILLEDROITE);

                for(int i=0;i<3;i++){
                    Iterator value = m.getGrille3D().getGrilles()[i].getGrille().iterator();
                    while (value.hasNext()) {

                        Case cas = (Case) value.next();

                        cas.updateObjectif(i);

                    }
                }
=======
            this.m.getGrille3D().deplacementCaseGrille3D(GRILLEGAUCHE);

            for (int i = 0; i < 3; i++) {
                Iterator value = m.getGrille3D().getGrilles()[i].getGrille().iterator();
                while (value.hasNext()) {

                    Case cas = (Case) value.next();

                    cas.updateObjectif(i);

                }
            }
        } else if (touche.compareTo("e") == 0) { // utilisateur appuie sur "s" pour envoyer la tuile en bas
            this.m.getGrille3D().deplacementCaseGrille3D(GRILLEDROITE);

            for (int i = 0; i < 3; i++) {
                Iterator value = m.getGrille3D().getGrilles()[i].getGrille().iterator();
                while (value.hasNext()) {

                    Case cas = (Case) value.next();

                    cas.updateObjectif(i);

                }
            }
>>>>>>> Stashed changes
        }

        Task task0 = new Task<Void>() { // on définit une tâche parallèle pour mettre à jour la vue

            @Override
            public Void call() throws Exception { // implémentation de la méthode protected abstract V call() dans la classe Task

                Iterator value = m.getGrille3D().getGrilles()[0].getGrille().iterator();

                while (value.hasNext()) {

                    Case cas = (Case) value.next();

                    boolean allmoved = false;

                    for (int i = 0; i < panes0.size(); i++) {

                        if (cas.getxInterface() == panes0.get(i).getLayoutX() && cas.getyInterface() == panes0.get(i).getLayoutY()) {

                            while (cas.getxInterface() != cas.getObjectifx() || cas.getyInterface() != cas.getObjectify()) { // si les tuiles n'est pas à la place qu'on souhaite attendre en abscisse
                                if (cas.getxInterface() < cas.getObjectifx()) {
                                    cas.setxInterface(cas.getxInterface() + 1); // si on va vers la droite, on modifie la position des tuiles pixel par pixel vers la droite
                                } else if (cas.getyInterface() < cas.getObjectify()) {
                                    cas.setyInterface(cas.getyInterface() + 1);
                                } else if (cas.getxInterface() > cas.getObjectifx()) {
                                    cas.setxInterface(cas.getxInterface() - 1);
                                } else if (cas.getyInterface() > cas.getObjectify()) {
                                    cas.setyInterface(cas.getyInterface() - 1);
                                }

                                int index = i;

                                Platform.runLater(new Runnable() { // classe anonyme
                                    @Override
                                    public void run() {
                                        panes0.get(index).relocate(cas.getxInterface(), cas.getyInterface()); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
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

                        if (cas.getxInterface() == panes1.get(i).getLayoutX() && cas.getyInterface() == panes1.get(i).getLayoutY()) {

                            while (cas.getxInterface() != cas.getObjectifx() || cas.getyInterface() != cas.getObjectify()) { // si les tuiles n'est pas à la place qu'on souhaite attendre en abscisse
                                if (cas.getxInterface() < cas.getObjectifx()) {
                                    cas.setxInterface(cas.getxInterface() + 1);// si on va vers la droite, on modifie la position des tuiles pixel par pixel vers la droite
                                } else if (cas.getyInterface() < cas.getObjectify()) {
                                    cas.setyInterface(cas.getyInterface() + 1);
                                } else if (cas.getxInterface() > cas.getObjectifx()) {
                                    cas.setxInterface(cas.getxInterface() - 1);
                                } else if (cas.getyInterface() > cas.getObjectify()) {
                                    cas.setyInterface(cas.getyInterface() - 1);
                                }

                                int index = i;

                                Platform.runLater(new Runnable() { // classe anonyme
                                    @Override
                                    public void run() {
                                        panes1.get(index).relocate(cas.getxInterface(), cas.getyInterface()); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
                                        panes1.get(index).setVisible(true);

                                        if (cas.getValeur() >= 10) {
                                            panes1.get(index).getChildren().get(index).getStyleClass().add("tuile10");
                                        } else if (cas.getValeur() >= 63) {
                                            panes1.get(index).getChildren().get(index).getStyleClass().add("tuile64");
                                        } else if (cas.getValeur() >= 99) {
                                            panes1.get(index).getChildren().get(index).getStyleClass().add("tuile100");
                                        } else if (cas.getValeur() >= 511) {
                                            panes1.get(index).getChildren().get(index).getStyleClass().add("tuile512");
                                        } else if (cas.getValeur() >= 999) {
                                            panes1.get(index).getChildren().get(index).getStyleClass().add("tuile1000");
                                        }
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

                        if (cas.getxInterface() == panes2.get(i).getLayoutX() && cas.getyInterface() == panes2.get(i).getLayoutY()) {

                            while (cas.getxInterface() != cas.getObjectifx() || cas.getyInterface() != cas.getObjectify()) { // si les tuiles n'est pas à la place qu'on souhaite attendre en abscisse
                                if (cas.getxInterface() < cas.getObjectifx()) {
                                    cas.setxInterface(cas.getxInterface() + 1);// si on va vers la droite, on modifie la position des tuiles pixel par pixel vers la droite
                                } else if (cas.getyInterface() < cas.getObjectify()) {
                                    cas.setyInterface(cas.getyInterface() + 1);
                                } else if (cas.getxInterface() > cas.getObjectifx()) {
                                    cas.setxInterface(cas.getxInterface() - 1);
                                } else if (cas.getyInterface() > cas.getObjectify()) {
                                    cas.setyInterface(cas.getyInterface() - 1);
                                }

                                int index = i;

                                Platform.runLater(new Runnable() { // classe anonyme
                                    @Override
                                    public void run() {
                                        panes2.get(index).relocate(cas.getxInterface(), cas.getyInterface()); // on déplace la tuile d'un pixel sur la vue, on attend 5ms et on recommence jusqu'à atteindre l'objectif
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
        initModele(m);
        nouvelleCase(m);
    }
}
