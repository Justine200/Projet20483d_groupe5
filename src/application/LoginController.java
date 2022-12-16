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
import javafx.scene.control.TextArea;
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
public class LoginController implements Initializable {

    @FXML
    private TextArea usernameField, pwdField;

    @FXML
    private Text message;

    @FXML
    private Button login;

    private ConnexionBDD con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        con = new ConnexionBDD();

    }

    @FXML
    private void login(MouseEvent event) throws IOException { //bouton Start

        if (this.usernameField.getText().isEmpty()) {

            message.setText("Enter your username : ");

        } else if (!this.usernameField.getText().isEmpty() && this.pwdField.getText().isEmpty()) {

            message.setText("Enter your password : ");

        } else {

            boolean connect = con.connect(this.usernameField.getText(), this.pwdField.getText());

            if (connect) {
                message.setText("Vous êtes connectés !");

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
            } else {
                message.setText("You're not connected!");
            }
        }

    }
}
