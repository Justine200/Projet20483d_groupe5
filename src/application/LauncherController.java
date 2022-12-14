/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.ConnexionBDD;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class LauncherController implements Initializable {

    @FXML
    private Button startButton; // boutons
    @FXML
    private Text topScore;
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        startButton.getStyleClass().add("button");
        ConnexionBDD con=new ConnexionBDD();
        System.out.println(con.meilleurScore());
        topScore.setText(con.meilleurScore());
    }

    @FXML
    private void startClicked(MouseEvent event) throws IOException { //bouton Start

        Stage launcher = Main.getPrimaryStage(); //Stage du lanceur
        
        launcher.hide();//cacher le lanceur

        
        Parent root = FXMLLoader.load(getClass().getResource("/application/JeuFX.fxml")); 

        Scene sceneJeu = new Scene(root);
        
        Stage stage = new Stage(); //stage du jeu

        boolean add = sceneJeu.getStylesheets().add("/application/style/JeuStyle.css");

        stage.setScene(sceneJeu);
        
        stage.getIcons().add(new Image("/application/style/icon.png"));
        
        stage.setTitle("2048 3D");
        
        stage.resizableProperty().setValue(false);

        stage.show();

    }

}
