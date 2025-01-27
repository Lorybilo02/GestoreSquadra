package org.example.teammanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    public TextField usernameField;
    public PasswordField passwordField;
    public Button LogInButton;
    Model model = new Model();
    private final Alert errorAlert = new Alert(Alert.AlertType.ERROR);

    public void changeCursor(MouseEvent mouseEvent) {
        ((javafx.scene.Node) mouseEvent.getSource()).setCursor(Cursor.HAND);
    }

    public void restoreCursor(MouseEvent mouseEvent) {
        ((javafx.scene.Node) mouseEvent.getSource()).setCursor(Cursor.DEFAULT);
    }

    public void send() {
        if(model.authenticateUser(usernameField.getText(), passwordField.getText()) == -1){
                errorAlert.setTitle("ERRORE");
                errorAlert.setHeaderText("ERRORE!!!");
                errorAlert.setContentText("UTENTE NON TROVATO!");
                errorAlert.show();
        }
        else if(model.authenticateUser(usernameField.getText(), passwordField.getText()) == 0){} //ROBA ADMIN
        else if(model.authenticateUser(usernameField.getText(), passwordField.getText()) == 1) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("LineUp.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setWidth(630);
                Scene scene = new Scene(root);
                stage.setTitle(usernameField.getText());
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {

            }
        }
        else if(model.authenticateUser(usernameField.getText(), passwordField.getText()) == 2) {//ROBA GIOCATORE
            }
    }
    public void sendWithEnter(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            send();
        }
    }

    public void sendWithClick(ActionEvent event){
        send();
    }


}
