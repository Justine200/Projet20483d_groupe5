/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class Main extends Application {

    private static Stage pStage; //stage statique pour le cacher depuis le controlleur

    @Override
    public void start(Stage stage) throws Exception {

        setPrimaryStage(stage);

        Parent root = FXMLLoader.load(getClass().getResource("/application/Launcher.fxml"));

        Scene scene = new Scene(root);

        // boolean add = scene.getStylesheets().add("css/styles.css");
        
        stage.setScene(scene);

        pStage = stage;

        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /*
    *Getters et Setters
     */
    public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        Main.pStage = pStage;
    }

}
