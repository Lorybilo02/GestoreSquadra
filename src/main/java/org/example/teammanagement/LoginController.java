package org.example.teammanagement;

import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class LoginController {
    public TextField usernameField;
    public PasswordField passwordField;
    Model model = new Model();
    private final Alert errorAlert = new Alert(Alert.AlertType.ERROR);

    public void changeCursor(MouseEvent mouseEvent) {
        ((javafx.scene.Node) mouseEvent.getSource()).setCursor(Cursor.HAND);
    }

    public void restoreCursor(MouseEvent mouseEvent) {
        ((javafx.scene.Node) mouseEvent.getSource()).setCursor(Cursor.DEFAULT);
    }

    public void send(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
            if(model.authenticateUser(usernameField.getText(), passwordField.getText()) == -1){
                errorAlert.setTitle("ERRORE");
                errorAlert.setHeaderText("ERRORE!!!");
                errorAlert.setContentText("UTENTE NON TROVATO!");
                errorAlert.show();
            }
        }
    }
}
