
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage pStage; //stage statique pour le cacher depuis le controlleur

    @Override
    public void start(Stage stage) throws Exception {

        setPrimaryStage(stage);

        Parent root = FXMLLoader.load(getClass().getResource("/application/Launcher.fxml"));

        Scene scene = new Scene(root);

        boolean add = scene.getStylesheets().add("/application/style/JeuStyle.css");

        stage.setScene(scene);

        stage.resizableProperty().setValue(false); // pour que l'utilisateur ne pourrai pas changer la taille de la fenetre
        
        stage.getIcons().add(new Image("/application/style/icon.png"));
        
        stage.setTitle("2048 3D");

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
