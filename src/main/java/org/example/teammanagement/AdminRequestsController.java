package org.example.teammanagement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminRequestsController implements Initializable {

    @FXML
    public TableView<RichiesteAllenatori> managersRequestsTable;
    @FXML
    public TableColumn<RichiesteAllenatori, Integer> managerRequestIdColumn;
    @FXML
    public TableColumn<RichiesteAllenatori, String> managerUsernameColumn;
    @FXML
    public TableColumn<RichiesteAllenatori, Integer> managerSalaryColumn;
    @FXML
    public TableColumn<RichiesteAllenatori, Integer> managerYearsColumn;

    @FXML
    public TableView<RichiesteGiocatori> playersRequestsTable;
    @FXML
    public TableColumn<RichiesteGiocatori, Integer> playerRequestIdColumn;
    @FXML
    public TableColumn<RichiesteGiocatori, String> playerUsernameColumn;
    @FXML
    public TableColumn<RichiesteGiocatori, Integer> playerSalaryColumn;
    @FXML
    public TableColumn<RichiesteGiocatori, Integer> playerYearsColumn;

    @FXML
    public Button acceptButton;
    @FXML
    public Button refuseButton;
    @FXML
    public Button acceptAllButton;
    @FXML
    public Button refuseAllButton;

    public String username;
    public String team;
    public Model model = new Model();

    private List<RichiesteGiocatori> playersRequests;
    @FXML
    public AnchorPane root;

    public Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    private List<RichiesteAllenatori> managersRequests;
    private boolean alertShowing = false;
    private AdminScreenController adminScreenController;

    private Stage stage; // Variabile per il riferimento al Stage

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inizializzazione delle tabelle
        managerRequestIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        managerUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        managerSalaryColumn.setCellValueFactory(new PropertyValueFactory<>("nuovoStipendio"));
        managerSalaryColumn.setCellFactory(column -> new TableCell<RichiesteAllenatori, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : (item == 0 ? "-" : item.toString()));
            }
        });

        managerYearsColumn.setCellValueFactory(new PropertyValueFactory<>("nuoviAnniContratto"));
        managerYearsColumn.setCellFactory(column -> new TableCell<RichiesteAllenatori, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : (item == 0 ? "-" : item.toString()));
            }
        });

        playerRequestIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        playerUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        playerSalaryColumn.setCellValueFactory(new PropertyValueFactory<>("nuovoStipendio"));
        playerSalaryColumn.setCellFactory(column -> new TableCell<RichiesteGiocatori, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : (item == 0 ? "-" : item.toString()));
            }
        });

        playerYearsColumn.setCellValueFactory(new PropertyValueFactory<>("nuoviAnniContratto"));
        playerYearsColumn.setCellFactory(column -> new TableCell<RichiesteGiocatori, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : (item == 0 ? "-" : item.toString()));
            }
        });

        // Utilizziamo Platform.runLater per accedere al Stage quando la scena è pronta
        Platform.runLater(() -> {
            stage = (Stage) root.getScene().getWindow();
            stage.setResizable(false);

            // Aggiungi un listener per la chiusura della finestra principale
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
                    if (alert != null && alert.isShowing()) {
                        System.out.println("Alert chiuso manualmente alla chiusura della finestra.");
                        alert.setResult(ButtonType.NO); // Chiudi l'alert se è aperto
                    }
                    adminScreenController.refreshIt(); // Esegui il refresh della schermata principale
                    stage.close(); // Chiudi la finestra
                } else {
                    // Se l'utente ha scelto "No", annulla la chiusura della finestra
                    windowEvent.consume(); // Annulla la chiusura della finestra
                }
                // Se l'alert è aperto, annulliamo la chiusura della finestra
            });
        });
    }

    public boolean showConfirmationDialog(String message) {
        disableControlsExceptCloseButton(true); // Disabilita i controlli

        // Crea l'alert
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma operazione");
        alert.setHeaderText("Sei sicuro di voler " + message + "?");
        alert.setContentText("Questa operazione è irreversibile.");

        ButtonType buttonTypeYes = new ButtonType("Sì");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        alert.initModality(Modality.NONE);

        // Mostriamo l'alert senza bloccare il thread


        // Aggiungiamo un listener per quando l'utente clicca un pulsante
        alert.setOnHidden(e -> {
            // Riabilita i controlli una volta che l'alert è chiuso
            disableControlsExceptCloseButton(false);
        });

        // Controlla quale pulsante è stato cliccato
        Optional<ButtonType> result = alert.showAndWait(); // Questo è necessario per monitorare la risposta dell'utente
        return result.isPresent() && result.get() == buttonTypeYes;
    }
    private void disableControlsExceptCloseButton(boolean disable) {
        for (Node node : root.getChildren()) {
            if (node instanceof Button && !((Button) node).getText().equals("X")) {
                node.setDisable(disable);
            }
        }
    }
    public void acceptRequest(ActionEvent actionEvent) {
        if(showConfirmationDialog("accettare questa richiesta")) {
            System.out.println("SIUM");
            if (playersRequestsTable.getSelectionModel().getSelectedItem() != null) {
                RichiesteGiocatori playerRequest = (RichiesteGiocatori) playersRequestsTable.getSelectionModel().getSelectedItem();
                model.accettaRichiesta(playerRequest.getId(), playerRequest.getUsername());
                playersRequestsTable.getItems().remove(playerRequest);
                playersRequestsTable.refresh();
            } else if (managersRequestsTable.getSelectionModel().getSelectedItem() != null) {
                RichiesteAllenatori managerRequest = (RichiesteAllenatori) managersRequestsTable.getSelectionModel().getSelectedItem();
                model.accettaRichiesta(managerRequest.getId(), managerRequest.getUsername());
                managersRequestsTable.getItems().remove(managerRequest);
                managersRequestsTable.refresh();
            }
        }
    }

    public void refuseRequest(ActionEvent actionEvent) {
        if (showConfirmationDialog("rifiutare questa richiesta")) {
            if (playersRequestsTable.getSelectionModel().getSelectedItem() != null) {
                RichiesteGiocatori playerRequest = (RichiesteGiocatori) playersRequestsTable.getSelectionModel().getSelectedItem();
                model.rifiutaRichiesta(playerRequest.getId(), playerRequest.getUsername());
                playersRequestsTable.getItems().remove(playerRequest);
                playersRequestsTable.refresh();
            } else if (managersRequestsTable.getSelectionModel().getSelectedItem() != null) {
                RichiesteAllenatori managerRequest = (RichiesteAllenatori) managersRequestsTable.getSelectionModel().getSelectedItem();
                model.rifiutaRichiesta(managerRequest.getId(), managerRequest.getUsername());
                managersRequestsTable.getItems().remove(managerRequest);
                managersRequestsTable.refresh();
            }
        }
    }

    public void acceptAllRequests(ActionEvent actionEvent) {
        if (showConfirmationDialog("accettare tutte le richieste")) {
            model.accettaTutteLeRichieste(team);
            managersRequestsTable.getItems().clear();
            playersRequestsTable.getItems().clear();
            managersRequestsTable.refresh();
            playersRequestsTable.refresh();
        }
    }

    public void refuseAllRequests(ActionEvent actionEvent) {
        if (showConfirmationDialog("rifiutare tutte le richieste")) {
            model.rifiutaTutteLeRichieste(team);
            managersRequestsTable.refresh();
            managersRequestsTable.getItems().clear();
            playersRequestsTable.getItems().clear();
            playersRequestsTable.refresh();
        }
    }

    public void setUsername(String currentUsername) {
        this.username = currentUsername;
        this.team = model.getSquadraByAdmin(username);
        List<RichiesteAllenatori> managersRequests = model.getAllRichiesteAllenatori(team);
        managersRequestsTable.getItems().addAll(managersRequests);
        List<RichiesteGiocatori> playersRequests = model.getAllRichiesteGiocatori(team);
        playersRequestsTable.getItems().addAll(playersRequests);
    }

    public void removePRSelection(MouseEvent mouseEvent) { //Remove Player Request Selection
        if(playersRequestsTable.getSelectionModel().getSelectedItem() != null){
            playersRequestsTable.getSelectionModel().clearSelection();
        }
    }

    public void removeMRSelection(MouseEvent mouseEvent) {
        if(managersRequestsTable.getSelectionModel().getSelectedItem() != null){
            managersRequestsTable.getSelectionModel().clearSelection();
        }
    }

    public void hover(MouseEvent mouseEvent) {
        Button enteredButton = (Button) mouseEvent.getSource();
        if(Objects.equals(enteredButton.getId(), "acceptButton")){
            ((Button) mouseEvent.getSource()).setStyle("-fx-background-color: #E6BE00; -fx-border-color: black;");
        }
        else if(Objects.equals(enteredButton.getId(), "acceptAllButton")){
            ((Button) mouseEvent.getSource()).setStyle("-fx-background-color: black; -fx-border-color: white;");
        }
        else if(Objects.equals(enteredButton.getId(), "refuseButton")){
            ((Button) mouseEvent.getSource()).setStyle("-fx-background-color: darkRed; -fx-border-color: Black;");
        }
        else if(Objects.equals(enteredButton.getId(), "refuseAllButton")){
            ((Button) mouseEvent.getSource()).setStyle("-fx-background-color: #006400; -fx-border-color: white;");
        }
    }

    public void removeHover(MouseEvent mouseEvent) {
        Button enteredButton = (Button) mouseEvent.getSource();
        if(Objects.equals(enteredButton.getId(), "acceptButton")){
            ((Button) mouseEvent.getSource()).setStyle("-fx-background-color: gold; -fx-border-color: black;");
        }
        else if(Objects.equals(enteredButton.getId(), "acceptAllButton")){
            ((Button) mouseEvent.getSource()).setStyle("-fx-background-color: DarkBlue; -fx-border-color: black;");
        }
        else if(Objects.equals(enteredButton.getId(), "refuseButton")){
            ((Button) mouseEvent.getSource()).setStyle("-fx-background-color: red; -fx-border-color: Black;");
        }
        else if(Objects.equals(enteredButton.getId(), "refuseAllButton")){
            ((Button) mouseEvent.getSource()).setStyle("-fx-background-color: green; -fx-border-color: white;");
        }
    }

    public void setMainController(AdminScreenController adminScreenController) {
        this.adminScreenController = adminScreenController;
    }

    //todo check su quanto specificare tableView<Giocatore> e TableColumn<Giocatore, String> e a che serve


}
