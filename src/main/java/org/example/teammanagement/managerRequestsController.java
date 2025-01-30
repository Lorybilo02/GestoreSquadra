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

public class managerRequestsController implements Initializable {

    public Label nameLabel;
    public Label salaryLabel;
    public Label surnameLabel;
    public Label yearsLabel;
    public TextField salaryField;
    public TextField yearsField;
    public Button sendButton;
    public TableView<RichiesteAllenatori> requestsTable;
    public TableColumn<RichiesteAllenatori, Integer> idColumn;
    public TableColumn<RichiesteAllenatori, Integer> salaryColumn;
    public TableColumn<RichiesteAllenatori, Integer> yearsColumn;
    public TableColumn<RichiesteAllenatori, String> teamColumn;
    public String username;  // Variabile per l'username
    public String team;
    public Model model = new Model();
    public Alert errorAlert = new Alert(Alert.AlertType.ERROR);

    List<RichiesteAllenatori> requests;
    //todo chiedere a Lorenzo se quando un allenatore invia una richiesta senza specificare lo stipendio è
    // meglio lasciare lo stipendio invariato anzichè 0
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            teamColumn.setCellValueFactory(new PropertyValueFactory<>("squadra"));
            salaryColumn.setCellValueFactory(new PropertyValueFactory<>("nuovoStipendio"));
            salaryColumn.setCellFactory(column -> new TableCell<RichiesteAllenatori, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(""); // lascia la cella vuota
                    } else if (item == 0) {
                        setText("-"); // mostra il trattino per 0
                    } else {
                        setText(item.toString()); // mostra il valore normalmente
                    }
                }
            });
            yearsColumn.setCellValueFactory(new PropertyValueFactory<>("nuoviAnniContratto"));
            yearsColumn.setCellFactory(column -> new TableCell<RichiesteAllenatori, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(""); // lascia la cella vuota
                    } else if (item == 0) {
                        setText("-"); // mostra il trattino per 0
                    } else {
                        setText(item.toString()); // mostra il valore normalmente
                    }
                }
            });
            try {
                requests = model.getAllRichiesteAllenatori(team);
            } catch (Exception e) {
                e.printStackTrace();
            }
            requestsTable.getItems().addAll(requests);
            errorAlert.setTitle("ERRORE!!!");
            errorAlert.setHeaderText("SI E' VERIFICATO UN ERRORE!!!");
            Stage stage = (Stage) nameLabel.getScene().getWindow();
            stage.setResizable(false);
        });
    }

    // Aggiungi questo metodo per ricevere l'username
    public void setUsername(String username) {
        this.username = username;
        team = model.getSquadraByAllenatore(username);
        Allenatore a = model.getAllenatoreBySquadra(team);
        nameLabel.setText(a.getNome());
        surnameLabel.setText(a.getCognome());
        salaryLabel.setText("Stipendio: " + a.getStipendio());
        yearsLabel.setText("Anni di contratto: " + a.getAnniContratto());
    }

    public void hoverButton(MouseEvent mouseEvent) {
        sendButton.setStyle("-fx-background-color: DarkGreen; -fx-border-color: White;");
    }

    public void removeHoverButton(MouseEvent mouseEvent) {
        sendButton.setStyle("-fx-background-color: Green; -fx-border-color: black;");
    }
    public void send(ActionEvent actionEvent) {
        if (!(Objects.equals(salaryField.getText(), "")) && !(Objects.equals(yearsField.getText(), ""))) {
            if (salaryField.getText().matches("^\\d+$") && yearsField.getText().matches("^\\d+$")) {
                if (model.addRichiestaAllenatore(username, Integer.parseInt(salaryField.getText()), Integer.parseInt(yearsField.getText()), team)) {
                    refreshIt();
                } else {
                    errorAlert.setContentText("QUALCOSA E' ANDATO STORTO... RIPROVA!");
                    errorAlert.show();
                }
            } else {
                errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI!!!");
                errorAlert.show();
            }
        } else if((Objects.equals(salaryField.getText(), ""))) {
            if (yearsField.getText().matches("^\\d+$")) {
                if (model.addRichiestaAllenatore(username, null, Integer.parseInt(yearsField.getText()), team)) {
                    refreshIt();
                } else {
                    errorAlert.setContentText("QUALCOSA E' ANDATO STORTO... RIPROVA!");
                    errorAlert.show();
                }
            } else {
                errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI!!!");
                errorAlert.show();
            }
        } else if((Objects.equals(yearsField.getText(), ""))) {
            if (salaryField.getText().matches("^\\d+$")) {
                if (model.addRichiestaAllenatore(username, Integer.parseInt(salaryField.getText()), null, team)) {
                    refreshIt();
                } else {
                    errorAlert.setContentText("QUALCOSA E' ANDATO STORTO... RIPROVA!");
                    errorAlert.show();
                }
            } else {
                errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI!!!");
                errorAlert.show();
            }

        } else{
            errorAlert.setContentText("DEVI COMPILARE ALMENO UN CAMPO!!!");
            errorAlert.show();
        }
    }
    public void refreshIt(){
        requests = model.getAllRichiesteAllenatori(team);
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
    public void keyboardActions(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
            send(null);
        }
        else if(event.getCode().equals(KeyCode.UP) || event.getCode().equals(KeyCode.DOWN)){
            if(salaryField.isFocused()){
                yearsField.requestFocus();
            }
            else{
                salaryField.requestFocus();
            }
        }
    }
}
