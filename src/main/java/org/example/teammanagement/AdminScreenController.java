package org.example.teammanagement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AdminScreenController implements Initializable {

    public TableView playersTable;
    public TableColumn shirtColumn;
    public TableColumn nameColumn;
    public TableColumn surnameColumn;
    public TableColumn ageColumn;
    public TableColumn positionColumn;
    public TableColumn minutesColumn;
    public TableColumn salaryColumn;
    public TableColumn yearsColumn;
    public TableColumn goalsColumn;
    public TableColumn assistColumn;
    public TableColumn nationalityColumn;
    public Button viewRequestsButton;
    public Label usernameLabel;
    public Label teamLabel;
    public String username;
    public String team;
    public Model model = new Model();
    public Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    public boolean insertable = true;
    public List<Giocatore> players;
    List<String> nationalities = Arrays.asList(
            "Afghanistan", "Albania", "Algeria", "Andorra", "Angola",
            "Antigua e Barbuda", "Argentina", "Armenia", "Australia", "Austria",
            "Azerbaigian", "Bahamas", "Bahrain", "Bangladesh", "Barbados",
            "Bielorussia", "Belgio", "Belize", "Benin", "Bhutan",
            "Bolivia", "Bosnia ed Erzegovina", "Botswana", "Brasile", "Brunei",
            "Bulgaria", "Burkina Faso", "Burundi", "Capo Verde", "Cambogia",
            "Camerun", "Canada", "Ciad", "Cile", "Cina",
            "Colombia", "Comore", "Congo", "Costa Rica", "Croazia",
            "Cuba", "Cipro", "Repubblica Ceca", "Danimarca", "Gibuti",
            "Dominica", "Ecuador", "Egitto", "El Salvador", "Guinea Equatoriale",
            "Eritrea", "Estonia", "Eswatini", "Etiopia", "Figi",
            "Filippine", "Finlandia", "Francia", "Gabon", "Gambia", "Georgia",
            "Germania", "Ghana", "Grecia", "Grenada", "Guatemala",
            "Guinea", "Guyana", "Haiti", "Honduras", "Ungheria",
            "Islanda", "India", "Indonesia", "Iran", "Iraq",
            "Irlanda", "Israele", "Italia", "Ivory Coast (Costa d'Avorio)", "Giamaica",
            "Giappone", "Giordania", "Kazakistan", "Kenya", "Saint Kitts e Nevis",
            "Kuwait", "Kirgizistan", "Laos", "Lettonia", "Libano",
            "Lesotho", "Liberia", "Libia", "Liechtenstein", "Lituania",
            "Lussemburgo", "Macedonia", "Madagascar", "Malawi",
            "Malesia", "Maldive", "Mali", "Malta", "Marrocco",
            "Marshalls", "Mauritius", "Messico", "Micronesia", "Moldavia",
            "Monaco", "Mongolia", "Montenegro", "Marocco", "Mozambico",
            "Namibia", "Nauru", "Nepal", "Paesi Bassi", "Nuova Zelanda",
            "Nicaragua", "Nigeria", "Niger", "Corea del Nord", "Norvegia",
            "Oman", "Pakistan", "Palau", "Panama", "Papua Nuova Guinea",
            "Paraguay", "Perù", "Filippine", "Polonia", "Portogallo",
            "Qatar", "Romania", "Russia", "Ruanda", "Saint Vincent e Grenadine",
            "Samoa", "San Marino", "Sao Tome e Principe", "Arabia Saudita", "Senegal",
            "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovacchia",
            "Slovenia", "Isole Solomon", "Somalia", "Sri Lanka", "Stati Uniti d'America",
            "Sudan", "Suriname", "Svezia", "Svizzera", "Siria",
            "Taiwan", "Tagikistan", "Tanzania", "Tailandia", "Timor Est",
            "Togo", "Tonga", "Trinidad e Tobago", "Tunisia", "Turchia",
            "Turkmenistan", "Tuvalu", "Uganda", "Ucraina", "Emirati Arabi Uniti",
            "Uruguay", "Uzbekistan", "Vanuatu", "Vaticano", "Venezuela",
            "Viet Nam", "Yemen", "Zambia", "Zimbabwe"
    );


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() ->{
            // Sincronizza il contenuto tra passwordField e passwordTextField
            Stage stage = (Stage) playersTable.getScene().getWindow();
            stage.setResizable(false);
        });
        try {
            shirtColumn.setCellValueFactory(new PropertyValueFactory<>("numMaglia"));
            shirtColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
                @Override
            public String toString(Integer object) {
                    if (object==null){
                        return "";
                    }
                    insertable = true;
                    try{
                        return object.toString(); // Converte l'Integer in String per visualizzazione
                    } catch (Exception e) {
                        errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI POSITIVI!!!");
                        errorAlert.show();
                        insertable = false;
                    }
                    return null;
                }

                @Override
                public Integer fromString(String string) {
                    insertable = true;
                    try{
                        return Integer.parseInt(string); // Converte la String in Integer per il modello dati
                    } catch (Exception e) {
                       errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI POSITIVI!!!");
                       errorAlert.show();
                       insertable = false;
                    }
                    return null;
                }
            }));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
            nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            surnameColumn.setCellValueFactory(new PropertyValueFactory<>("cognome"));
            surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            ageColumn.setCellValueFactory(new PropertyValueFactory<>("eta"));
            ageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
                @Override
                public String toString(Integer object) {
                    if (object==null){
                        return "";
                    }
                    insertable = true;
                    try{
                        return object.toString(); // Converte l'Integer in String per visualizzazione
                    } catch (Exception e) {
                        errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI POSITIVI!!!");
                        errorAlert.show();
                        insertable = false;
                    }
                    return null;
                }

                @Override
                public Integer fromString(String string) {
                    insertable = true;
                    try{
                        return Integer.parseInt(string); // Converte la String in Integer per il modello dati
                    } catch (Exception e) {
                        errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI POSITIVI!!!");
                        errorAlert.show();
                        insertable = false;
                    }
                    return null;
                }
            }));
            positionColumn.setCellValueFactory(new PropertyValueFactory<>("ruolo"));
            positionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            minutesColumn.setCellValueFactory(new PropertyValueFactory<>("MinutiGiocati"));
            minutesColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
                @Override
                public String toString(Integer object) {
                    if (object==null){
                        return "";
                    }
                    insertable = true;
                    try{
                        return object.toString(); // Converte l'Integer in String per visualizzazione
                    } catch (Exception e) {
                        errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI POSITIVI!!!");
                        errorAlert.show();
                        insertable = false;
                    }
                    return null;
                }

                @Override
                public Integer fromString(String string) {
                    insertable = true;
                    try{
                        return Integer.parseInt(string); // Converte la String in Integer per il modello dati
                    } catch (Exception e) {
                        errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI POSITIVI!!!");
                        errorAlert.show();
                        insertable = false;
                    }
                    return null;
                }
            }));
            salaryColumn.setCellValueFactory(new PropertyValueFactory<>("stipendio"));
            yearsColumn.setCellValueFactory(new PropertyValueFactory<>("anniContratto"));
            salaryColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
                @Override
                public String toString(Integer object) {
                    if (object==null){
                        return "";
                    }
                    insertable = true;
                    try{
                        return object.toString(); // Converte l'Integer in String per visualizzazione
                    } catch (Exception e) {
                        errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI POSITIVI!!!");
                        errorAlert.show();
                        insertable = false;
                    }
                    return null;
                }

                @Override
                public Integer fromString(String string) {
                    insertable = true;
                    try{
                        return Integer.parseInt(string); // Converte la String in Integer per il modello dati
                    } catch (Exception e) {
                        errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI POSITIVI!!!");
                        errorAlert.show();
                        insertable = false;
                    }
                    return null;
                }
            }));

            yearsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
                @Override
                public String toString(Integer object) {
                    if (object==null){
                        return "";
                    }
                    insertable = true;
                    try{
                        return object.toString(); // Converte l'Integer in String per visualizzazione
                    } catch (Exception e) {
                        errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI POSITIVI!!!");
                        errorAlert.show();
                        insertable = false;
                    }
                    return null;
                }

                @Override
                public Integer fromString(String string) {
                    insertable = true;
                    try{
                        return Integer.parseInt(string); // Converte la String in Integer per il modello dati
                    } catch (Exception e) {
                        errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI POSITIVI!!!");
                        errorAlert.show();
                        insertable = false;
                    }
                    return null;
                }
            }));
            goalsColumn.setCellValueFactory(new PropertyValueFactory<>("Goal"));
            goalsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
                @Override
                public String toString(Integer object) {
                    if (object==null){
                        return "";
                    }
                    insertable = true;
                    try{
                        return object.toString(); // Converte l'Integer in String per visualizzazione
                    } catch (Exception e) {
                        errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI POSITIVI!!!");
                        errorAlert.show();
                        insertable = false;
                    }
                    return null;
                }

                @Override
                public Integer fromString(String string) {
                    insertable = true;
                    try{
                        return Integer.parseInt(string); // Converte la String in Integer per il modello dati
                    } catch (Exception e) {
                        errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI POSITIVI!!!");
                        errorAlert.show();
                        insertable = false;
                    }
                    return null;
                }
            }));
            assistColumn.setCellValueFactory(new PropertyValueFactory<>("Assist"));
            assistColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
                @Override
                public String toString(Integer object) {
                    if (object==null){
                        return "";
                    }
                    insertable = true;
                    try{
                        return object.toString(); // Converte l'Integer in String per visualizzazione
                    } catch (Exception e) {
                        errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI POSITIVI!!!");
                        errorAlert.show();
                        insertable = false;
                    }
                    return null;
                }

                @Override
                public Integer fromString(String string) {
                    insertable = true;
                    try{
                        return Integer.parseInt(string); // Converte la String in Integer per il modello dati
                    } catch (Exception e) {
                        errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI POSITIVI!!!");
                        errorAlert.show();
                        insertable = false;
                    }
                    return null;
                }
            }));
            nationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nazionalita"));
            nationalityColumn.setCellFactory(TextFieldTableCell.forTableColumn());

            errorAlert.setTitle("ERRORE!!!");
            errorAlert.setHeaderText("SI E' VERIFICATO UN ERRORE!");

        } catch (Exception e) {
        }
    }




    public void setUsername(String username) {
        this.username = username;
        usernameLabel.setText(username);
        teamLabel.setText(model.getSquadraByAdmin(username));
        List<Giocatore> players = model.getGiocatoriBySquadra(teamLabel.getText());
        Allenatore a = model.getAllenatoreBySquadra(teamLabel.getText());
        playersTable.getItems().add(a);

        playersTable.getItems().addAll(players);
    }

    public void editNumbers(TableColumn.CellEditEvent cellEditEvent) {
        if(playersTable.getSelectionModel().getSelectedItem() instanceof Giocatore) {

            Giocatore selectedPlayer = (Giocatore) playersTable.getSelectionModel().getSelectedItem();
            if (insertable) {
                Integer newValue = (Integer) cellEditEvent.getNewValue();          //Prende il nuovo valore della colonna
                if (newValue < 0 || newValue == null) {
                    playersTable.refresh();
                    errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI POSITIVI!!!");
                    errorAlert.show();
                } else {
                    if (Objects.equals(cellEditEvent.getTableColumn().getId(), "shirtColumn") && newValue < 100) {
                        players = model.getGiocatoriBySquadra(teamLabel.getText());
                        boolean taken = false;
                        for (Giocatore g : players) {
                            if (g.getNumMaglia() == newValue) {
                                errorAlert.setContentText("NUMERO DI MAGLIA OCCUPATO DA " + g.getCognome());
                                errorAlert.show();
                                taken = true;
                                break;
                            }
                        }
                        if (!taken) {
                            selectedPlayer.setNumMaglia(newValue);
                            model.editNumMagliaGiocatore(selectedPlayer.getId(), newValue);
                        } else {
                            playersTable.refresh();
                        }
                    } else if (Objects.equals(cellEditEvent.getTableColumn().getId(), "ageColumn")) {
                        selectedPlayer.setEta(newValue);
                        model.editEtaGiocatore(selectedPlayer.getId(), newValue);
                    } else if (Objects.equals(cellEditEvent.getTableColumn().getId(), "minutesColumn")) {
                        selectedPlayer.setMinutiGiocati(newValue);
                        model.editMinutigiocatiGiocatore(selectedPlayer.getId(), newValue);

                    } else if (Objects.equals(cellEditEvent.getTableColumn().getId(), "salaryColumn")) {
                        selectedPlayer.setStipendio(newValue);
                        model.editStipendioGiocatore(selectedPlayer.getId(), newValue);
                    } else if (Objects.equals(cellEditEvent.getTableColumn().getId(), "yearsColumn")) {
                        selectedPlayer.setAnniContratto(newValue);
                        model.editAnnicontrattoGiocatore(selectedPlayer.getId(), newValue);

                    } else if (Objects.equals(cellEditEvent.getTableColumn().getId(), "goalsColumn")) {
                        selectedPlayer.setGoal(newValue);
                        model.editGoalGiocatore(selectedPlayer.getId(), newValue);
                    } else if (Objects.equals(cellEditEvent.getTableColumn().getId(), "assistColumn")) {
                        selectedPlayer.setAssist(newValue);
                        model.editAssistGiocatore(selectedPlayer.getId(), newValue);
                    } else if (Objects.equals(cellEditEvent.getTableColumn().getId(), "shirtColumn") && newValue > 99) {
                        errorAlert.setContentText("IL NUMERI DI MAGLIA VANNO DA 1 A 99!!!");
                        errorAlert.show();
                        playersTable.refresh();
                    }
                }
            } else {
                playersTable.refresh();
            }
        } else if (playersTable.getSelectionModel().getSelectedItem() instanceof Allenatore){
            Allenatore a = (Allenatore) playersTable.getSelectionModel().getSelectedItem();
            Integer newValue = (Integer) cellEditEvent.getNewValue();
            if(newValue!=null && newValue > 0){
                if(Objects.equals(cellEditEvent.getTableColumn().getId(), "ageColumn")){
                    a.setEta(newValue);
                    model.editEtaAllenatore(a.getId(), newValue);
                    playersTable.refresh();
                } else if (Objects.equals(cellEditEvent.getTableColumn().getId(), "salaryColumn")) {
                    a.setStipendio(newValue);
                    model.editStipendioAllenatore(a.getId(), newValue);
                    playersTable.refresh();

                }
                else if (Objects.equals(cellEditEvent.getTableColumn().getId(), "yearsColumn")) {
                    a.setAnniContratto(newValue);
                    model.editAnniContrattoAllenatore(a.getId(), newValue);
                    playersTable.refresh();

                } else{
                    errorAlert.setContentText("IL MISTER NON HA QUESTO ATTRIBUTO!!!");
                    errorAlert.show();
                    playersTable.refresh();
                }
            }
            else{
                errorAlert.setContentText("PUOI INSERIRE SOLO NUMERI POSITIVI!!!");
                errorAlert.show();
                playersTable.refresh();
            }

        }

    }


    public void editStrings(TableColumn.CellEditEvent cellEditEvent) {
        if(playersTable.getSelectionModel().getSelectedItem() instanceof Giocatore) {
            Giocatore selectedPlayer = (Giocatore) playersTable.getSelectionModel().getSelectedItem();
            String newValue = (String) cellEditEvent.getNewValue();
            if(Objects.equals(cellEditEvent.getTableColumn().getId(), "positionColumn")){
                if(!Objects.equals(newValue, "POR") && !Objects.equals(newValue, "ATT") && !Objects.equals(newValue, "DIF") && !Objects.equals(newValue, "CEN")){
                    errorAlert.setContentText("POSIZIONE NON VALIDA!!!");
                    errorAlert.show();
                    playersTable.refresh();
                }
                else{
                    selectedPlayer.setRuolo(newValue);
                    model.editRuoloGiocatore(selectedPlayer.getId(), newValue);
                }
            }
            else{
                if (!newValue.matches("^[a-zA-Zàáèéìíòóùú\\s']+$")){
                    errorAlert.setContentText("CI SONO CARATTERI NON VALIDI!!!");
                    errorAlert.show();
                    playersTable.refresh();
                }
                else{
                    if(Objects.equals(cellEditEvent.getTableColumn().getId(), "nameColumn")){
                        selectedPlayer.setNome(newValue);
                        model.editNomeGiocatore(selectedPlayer.getId(), newValue);
                    } else if (Objects.equals(cellEditEvent.getTableColumn().getId(), "surnameColumn")) {
                        selectedPlayer.setCognome(newValue);
                        model.editCognomeGiocatore(selectedPlayer.getId(), newValue);
                    } else if (Objects.equals(cellEditEvent.getTableColumn().getId(), "nationalityColumn")) {
                        if (nationalities.contains(newValue)){
                            selectedPlayer.setNazionalita(newValue);
                            model.editNazionalitaGiocatore(selectedPlayer.getId(), newValue);

                        }
                        else{
                            errorAlert.setContentText("INSERISCI BENE LA NAZIONALITA'!!!");
                            errorAlert.show();
                            playersTable.refresh();
                        }

                    }
                }
            }
        }else if (playersTable.getSelectionModel().getSelectedItem() instanceof Allenatore){
            Allenatore a = (Allenatore) playersTable.getSelectionModel().getSelectedItem();
            String newValue = (String) cellEditEvent.getNewValue();
             if (Objects.equals(cellEditEvent.getTableColumn().getId(), "nationalityColumn")) {
                 if(nationalities.contains(newValue)){
                     //todo aspettare che Lorenzo fixi
                     a.setNazionalita(newValue);
                     model.editNazionalitaAllenatore(a.getId(), newValue);
                     //playersTable.refresh();
                 }
                 else{
                     errorAlert.setContentText("INSERISCI BENE LA NAZIONALITA'!!!");
                     errorAlert.show();
                     playersTable.refresh();
                 }

             } else if (Objects.equals(cellEditEvent.getTableColumn().getId(), "nameColumn")) {
                 if(!newValue.matches("^[a-zA-Zàáèéìíòóùú\\s']+$")){
                     errorAlert.setContentText("CI SONO CARATTERI NON VALIDI!!!");
                     errorAlert.show();
                     playersTable.refresh();
                 }
                 else{
                     a.setNome(newValue);
                     model.editNomeAllenatore(a.getId(), newValue);
                 }

             } else if (Objects.equals(cellEditEvent.getTableColumn().getId(), "surnameColumn")) {
                 if(!newValue.matches("^[a-zA-Zàáèéìíòóùú\\s']+$")){
                     errorAlert.setContentText("CI SONO CARATTERI NON VALIDI!!!");
                     errorAlert.show();
                     playersTable.refresh();
                 }
                 else{
                     a.setCognome(newValue);
                     model.editCognomeAllenatore(a.getId(), newValue);
                 }
             }
        }
    }

    public void viewRequests(ActionEvent actionEvent) throws IOException {
        // Carica il file FXML per la nuova schermata
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminRequestsScreen.fxml"));
        Parent root = loader.load();

        // Ottieni l'username della schermata corrente
        String currentUsername = username; // Assicurati che 'username' contenga il valore corretto

        // Imposta il controller della nuova schermata
        AdminRequestsController controller = loader.getController();
        controller.setUsername(currentUsername); // Passa l'username

        // Passa anche il controller di schermata1 a schermata2
        controller.setMainController(this); // Qui passi il controller di schermata1

        // Creazione della nuova finestra
        Stage currentStage = (Stage) playersTable.getScene().getWindow();  // Finestra corrente
        Stage newStage = new Stage();  // Nuova finestra
        Scene scene = new Scene(root);  // Nuova scena
        newStage.setScene(scene);
        newStage.setTitle("Sezione Richieste di " + username);

        // Imposta la nuova finestra come modale
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.initOwner(currentStage);

        // Mostra la finestra modale
        newStage.showAndWait();

        // Dopo che la finestra modale si chiude, puoi eseguire altre operazioni se necessario
        playersTable.refresh();
    }
    public void refreshIt(){
        playersTable.getItems().clear();
        Allenatore a = model.getAllenatoreBySquadra(teamLabel.getText());
        playersTable.getItems().add(a);
        playersTable.getItems().addAll(model.getGiocatoriBySquadra(teamLabel.getText()));
        playersTable.refresh();
    }
    public void hover(MouseEvent mouseEvent) {
        viewRequestsButton.setStyle("-fx-background-color: #4b3620; -fx-border-color: White;");
    }

    public void removeHover(MouseEvent mouseEvent) {
        viewRequestsButton.setStyle("-fx-background-color: brown; -fx-border-color: black;");
    }
}
