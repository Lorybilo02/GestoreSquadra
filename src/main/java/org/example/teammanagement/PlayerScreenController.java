package org.example.teammanagement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class PlayerScreenController implements Initializable {


    public Label usernameLabel;
    public Label nameLabel;
    public Label surnameLabel;
    public Label salaryLabel;
    public Label yearsLabel;
    public Label minutesLabel;
    public Label goalsLabel;
    public Label assistLabel;
    public TableView requestsTable;
    public TextField salaryField;
    public TextField yearsField;
    public Button sendRequestButton;
    public Label nationalityLabel;
    public String username;
    public String team;
    public Model model = new Model();
    public TableColumn idColumn;
    public TableColumn salaryColumn;
    public TableColumn yearsColumn;
    public List<RichiesteGiocatori> requests;
    public Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Gestione personalizzata per salaryColumn
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("nuovoStipendio"));
        salaryColumn.setCellFactory(column -> new TableCell<RichiesteAllenatori, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("");
                } else if (item == 0) {
                    setText("-");
                } else {
                    setText(item.toString());
                }
            }
        });

        // Gestione personalizzata per yearsColumn
        yearsColumn.setCellValueFactory(new PropertyValueFactory<>("nuoviAnniContratto"));
        yearsColumn.setCellFactory(column -> new TableCell<RichiesteAllenatori, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("");
                } else if (item == 0) {
                    setText("-");
                } else {
                    setText(item.toString());
                }
            }
        });
        Platform.runLater(() -> {
            Stage stage = (Stage) nameLabel.getScene().getWindow();
            stage.setResizable(false);
        });

        errorAlert.setTitle("ERRORE!!!");
        errorAlert.setHeaderText("SI E' VERIFICATO UN ERRORE!");
    }
    public void setUsername(String currentUsername) {
        this.username = currentUsername;
        usernameLabel.setText(currentUsername);
        Giocatore g = model.getGiocatoreByUsername(this.username);
        nameLabel.setText(g.getNome());
        surnameLabel.setText(g.getCognome());
        salaryLabel.setText(String.valueOf(g.getStipendio()));
        yearsLabel.setText(String.valueOf(g.getAnniContratto()));
        minutesLabel.setText(String.valueOf(g.getMinutiGiocati()));
        goalsLabel.setText(String.valueOf(g.getGoal()));
        assistLabel.setText(String.valueOf(g.getAssist()));
        nationalityLabel.setText(g.getNazionalita());
        requests = model.getRichiesteGiocatoreByUsername(username);
        requestsTable.getItems().addAll(requests);
        requestsTable.refresh();
    }

    public void sendRequest(ActionEvent actionEvent) {
        if(salaryField.getText().isEmpty() && yearsField.getText().isEmpty()){
            errorAlert.setContentText("DEVI COMPILARE ALMENO UN CAMPO!!!");
            errorAlert.show();
        }
        else{
            team = model.getSquadraByGiocatore(usernameLabel.getText());
            if(salaryField.getText().isEmpty()){
                if(yearsField.getText().matches("^\\d+$")){
                    model.addRichiestaGiocatore(usernameLabel.getText(), null, Integer.parseInt(yearsField.getText()), team);
                    refreshIt();
                }
                else{
                    errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI!!!");
                    errorAlert.show();
                }
            } else if (yearsField.getText().isEmpty()) {
                if(salaryField.getText().matches("^\\d+$")){
                    model.addRichiestaGiocatore(usernameLabel.getText(), Integer.parseInt(salaryField.getText()), null , team);
                    refreshIt();
                }
                else{
                    errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI!!!");
                    errorAlert.show();
                }
            }
            else{
                if(salaryField.getText().matches("^\\d+$") && yearsField.getText().matches("^\\d+$")){
                    model.addRichiestaGiocatore(usernameLabel.getText(), Integer.parseInt(salaryField.getText()), Integer.parseInt(yearsField.getText()) , team);
                    refreshIt();
                }
                else{
                    errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI!!!");
                    errorAlert.show();
                }
            }
        }
    }
    public void refreshIt(){
        requests = model.getRichiesteGiocatoreByUsername(usernameLabel.getText());
        requestsTable.getItems().clear();
        requestsTable.getItems().addAll(requests);
        requestsTable.refresh();
        if(!(Objects.equals(salaryField.getText(), ""))){
            salaryField.setText("");
        }
        if(!(Objects.equals(yearsField.getText(), ""))){
            yearsField.setText("");
        }
    }

    public void removeHover(MouseEvent mouseEvent) {
        sendRequestButton.setStyle("-fx-background-color: #00C8A3; -fx-border-color: black;");
    }

    public void hover(MouseEvent mouseEvent) {
        sendRequestButton.setStyle("-fx-background-color: #00A287; -fx-border-color: black;");
    }

    public void keyboardActions(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)){
            sendRequest(null);
        } else if (keyEvent.getCode().equals(KeyCode.UP) || keyEvent.getCode().equals(KeyCode.DOWN)) {
            if(salaryField.isFocused()) yearsField.requestFocus();
            else salaryField.requestFocus();
        }
    }
}
