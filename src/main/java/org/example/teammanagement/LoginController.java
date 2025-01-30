package org.example.teammanagement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class LoginController {
    public TextField usernameField;
    public PasswordField passwordField;
    public TextField passwordTextField; // Campo per mostrare la password in chiaro
    public Button LogInButton;
    public Button toggleVisibilityButton; // Pulsante con l'occhio
    Model model = new Model();
    private final Alert errorAlert = new Alert(Alert.AlertType.ERROR);

    public void initialize() {
        Platform.runLater(() ->{
        // Sincronizza il contenuto tra passwordField e passwordTextField
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setResizable(false);
        });
        passwordTextField.textProperty().bindBidirectional(passwordField.textProperty());
        StackPane.setAlignment(toggleVisibilityButton, Pos.CENTER_RIGHT);
        StackPane.setMargin(toggleVisibilityButton, new Insets(0, 5, 0, 0)); // Margine destro di 5px
        // Imposta la visibilità iniziale
        passwordTextField.setManaged(false);
        passwordTextField.setVisible(false);

        // Imposta l'icona iniziale per il pulsante dell'occhio
        toggleVisibilityButton.setText("👁️");
        toggleVisibilityButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
    }

    public void togglePasswordVisibility(MouseEvent event) {
        // Alterna tra PasswordField e TextField
        if (passwordField.isVisible()) {
            passwordField.setVisible(false);
            passwordField.setManaged(false);
            passwordTextField.setVisible(true);
            passwordTextField.setManaged(true);
            toggleVisibilityButton.setText("🙈"); // Cambia l'icona al "chiuso"
        } else {
            passwordField.setVisible(true);
            passwordField.setManaged(true);
            passwordTextField.setVisible(false);
            passwordTextField.setManaged(false);
            toggleVisibilityButton.setText("👁️"); // Cambia l'icona all'"occhio aperto"
        }
    }

    public void send() {
        if(usernameField.getText().isEmpty() && passwordField.getText().isEmpty()){
            errorAlert.setContentText("NON HAI INSERITO I DATI, RIPROVA!!!");
            errorAlert.show();
        }
        else{
            if (model.authenticateUser(usernameField.getText(), passwordField.getText()) == -1) {
                errorAlert.setTitle("ERRORE");
                errorAlert.setHeaderText("ERRORE!!!");
                errorAlert.setContentText("UTENTE NON TROVATO! RIPROVA!");
                errorAlert.show();
            } else if (model.authenticateUser(usernameField.getText(), passwordField.getText()) == 0) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminScreen.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) usernameField.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setTitle(usernameField.getText());
                    stage.setScene(scene);
                    stage.setOnCloseRequest(windowEvent -> {
                        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
                        alertConfirm.setTitle("Conferma Chiusura");
                        alertConfirm.setHeaderText("Sei sicuro di voler chiudere la finestra?");
                        alertConfirm.setContentText("Tutte le modifiche non salvate andranno perse.");

                        // Crea i pulsanti per l'alert
                        ButtonType buttonTypeYes = new ButtonType("Sì");
                        ButtonType buttonTypeNo = new ButtonType("No");
                        alertConfirm.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                        // Mostra l'alert e aspetta la risposta dell'utente
                        Optional<ButtonType> result = alertConfirm.showAndWait();

                        // Se l'utente ha confermato la chiusura (ha premuto "Sì"), allora esegui il codice
                        if (result.isPresent() && result.get() == buttonTypeYes) {
                            try {
                                // Carica il file FXML per la schermata di login
                                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Login.fxml"));
                                Parent loginRoot = loader1.load();

                                // Crea un nuovo stage per la schermata di login
                                Stage loginStage = new Stage();
                                Scene loginScene = new Scene(loginRoot);
                                loginStage.setScene(loginScene);
                                loginStage.setTitle("Login");

                                // Mostra la schermata di login
                                loginStage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            stage.close(); // Chiudi la finestra corrente
                        } else {
                            windowEvent.consume(); // Annulla la chiusura della finestra
                        }
                    });
                    AdminScreenController controller = loader.getController();
                    controller.setUsername(usernameField.getText());
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (model.authenticateUser(usernameField.getText(), passwordField.getText()) == 1) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("LineUp.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) usernameField.getScene().getWindow();
                    stage.setWidth(630);
                    Scene scene = new Scene(root);
                    stage.setTitle(usernameField.getText());
                    stage.setScene(scene);
                    stage.setOnCloseRequest(windowEvent -> {
                        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
                        alertConfirm.setTitle("Conferma Chiusura");
                        alertConfirm.setHeaderText("Sei sicuro di voler chiudere la finestra?");
                        alertConfirm.setContentText("Tutte le modifiche non salvate andranno perse.");

                        // Crea i pulsanti per l'alert
                        ButtonType buttonTypeYes = new ButtonType("Sì");
                        ButtonType buttonTypeNo = new ButtonType("No");
                        alertConfirm.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                        // Mostra l'alert e aspetta la risposta dell'utente
                        Optional<ButtonType> result = alertConfirm.showAndWait();

                        // Se l'utente ha confermato la chiusura (ha premuto "Sì"), allora esegui il codice
                        if (result.isPresent() && result.get() == buttonTypeYes) {
                            try {
                                // Carica il file FXML per la schermata di login
                                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Login.fxml"));
                                Parent loginRoot = loader1.load();

                                // Crea un nuovo stage per la schermata di login
                                Stage loginStage = new Stage();
                                Scene loginScene = new Scene(loginRoot);
                                loginStage.setScene(loginScene);
                                loginStage.setTitle("Login");

                                // Mostra la schermata di login
                                loginStage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            stage.close(); // Chiudi la finestra corrente
                        } else {
                            windowEvent.consume(); // Annulla la chiusura della finestra
                        }
                    });
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (model.authenticateUser(usernameField.getText(), passwordField.getText()) == 2) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerScreen.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) usernameField.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setTitle(usernameField.getText());
                    stage.setScene(scene);
                    PlayerScreenController controller = loader.getController();
                    controller.setUsername(usernameField.getText());
                    stage.setOnCloseRequest(windowEvent -> {
                        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
                        alertConfirm.setTitle("Conferma Chiusura");
                        alertConfirm.setHeaderText("Sei sicuro di voler chiudere la finestra?");
                        alertConfirm.setContentText("Tutte le modifiche non salvate andranno perse.");

                        // Crea i pulsanti per l'alert
                        ButtonType buttonTypeYes = new ButtonType("Sì");
                        ButtonType buttonTypeNo = new ButtonType("No");
                        alertConfirm.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                        // Mostra l'alert e aspetta la risposta dell'utente
                        Optional<ButtonType> result = alertConfirm.showAndWait();

                        // Se l'utente ha confermato la chiusura (ha premuto "Sì"), allora esegui il codice
                        if (result.isPresent() && result.get() == buttonTypeYes) {
                            try {
                                // Carica il file FXML per la schermata di login
                                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Login.fxml"));
                                Parent loginRoot = loader1.load();

                                // Crea un nuovo stage per la schermata di login
                                Stage loginStage = new Stage();
                                Scene loginScene = new Scene(loginRoot);
                                loginStage.setScene(loginScene);
                                loginStage.setTitle("Login");

                                // Mostra la schermata di login
                                loginStage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            stage.close(); // Chiudi la finestra corrente
                        } else {
                            windowEvent.consume(); // Annulla la chiusura della finestra
                        }
                    });
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendWithEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            send();
        }
    }

    public void sendWithClick(ActionEvent event) {
        send();
    }

    public void arrows(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.UP) || keyEvent.getCode().equals(KeyCode.DOWN)) {
            if (usernameField.isFocused()) {
                passwordField.requestFocus();
            } else usernameField.requestFocus();
        }
    }
}