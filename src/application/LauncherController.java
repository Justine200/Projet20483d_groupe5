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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class LauncherController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void exitClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void startClicked(MouseEvent event) throws IOException {

        Parent rootJeu = FXMLLoader.load(getClass().getResource("/application/FXMLDocument.fxml"));

        Stage stage = new Stage();

        Scene sceneJeu = new Scene(rootJeu);

        boolean add = sceneJeu.getStylesheets().add("style/JeuStyle.css");

        stage.show();

    }

}
