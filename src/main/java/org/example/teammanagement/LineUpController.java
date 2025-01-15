package org.example.teammanagement;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.teammanagement.Modulo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class LineUpController implements Initializable {
    public ChoiceBox choicer;
    public Circle n1;
    public Circle n2;
    public Circle n3;
    public Circle n4;
    public Circle n5;
    public Circle n6;
    public Circle n7;
    public Circle n8;
    public Circle n9;
    public Circle n10;
    public Circle n11;
    public Modulo modulo4231 = new Modulo();
    public Modulo modulo352 = new Modulo(95.0, 215.0, 310.0, 405.0, 244.0, 375.0, 310.0, 244.0, 375.0, 518.0, 138.0, 217.0, 231.0, 217.0, 147.0, 147.0, 87.0, 30.0, 30.0, 138.0, 69.0, 196.0, 296.0, 389.0, 210.0, 353.0, 275.0, 222.0, 361.0, 480.0, 107.0, 187.0, 199.0, 187.0, 116.0, 116.0, 56.0, -3.0, -3.0, 107.0 );
    public Modulo modulo442 = new Modulo(121.0,244.0,375.0,490.0,244.0,375.0,375.0,244.0,121.0,490.0,199.0,234.0,234.0,199.0,138.0,138.0,30.0,30.0,87.0,87.0,95.0,224.0,361.0,473.0,210.0,353.0,340.0,221.0,107.0,452.0,166.0,200.0,200.0,166.0,102.0,102.0,0.0,0.0,56.0,56.0);
    public Modulo modulo532 = new Modulo(134.0,210.0,310.0,405.0,259.0,361.0,310.0,259.0,361.0,481.0,167.0,213.0,227.0,213.0, 153.0,153.0,94.0,30.0,30.0,167.0,108.0,190.0,297.0,389.0,225.0,339.0,275.0,236.0,347.0,443.0,135.0,180.0,194.0,180.0,121.0,121.0,61.0,-1.0,-1.0,135.0);

    public Modulo modulo433 = new Modulo(121.0,244.0,375.0,490.0,243.0,374.0,310.0,310.0,121.0,490.0,199.0,234.0,234.0,199.0,135.0,135.0, 175.0,30.0,65.0,65.0,95.0,224.0,361.0,473.0,209.0,353.0,275.0,287.0,107.0,452.0,166.0,200.0,200.0,166.0,104.0,104.0,144.0,0.0,36.0,36.0);
    public Modulo modulo343 = new Modulo(95.0, 215.0, 310.0, 405.0, 244.0, 375.0,518.0,310.0,121.0,490.0,138.0,217.0,231.0,217.0,138.0,138.0,138.0,30.0,62.0,62.0,68.0,196.0,296.0,389.0,210.0,353.0,483.0,287.0,107.0,452.0,107.0,187.0,201.0,187.0,107.0,107.0,107.0,0.0,31.0,31.0);
    public Label name10;
    public Label name8;
    public Label name9;
    public Label name7;
    public Label name6;
    public Label name5;
    public Label name4;
    public Label name3;
    public Label name2;
    public Label name11;
    public Label name1;
    public TextField changePlayerFIeld;
    public Label player;
    public TableView<Giocatore> playersTable;
    public TableColumn<Giocatore, Integer> shirtColumn;
    public TableColumn<Giocatore, String> nameColumn;
    public TableColumn<Giocatore, String> surnameColumn;
    public TableColumn<Giocatore, Integer> ageColumn;
    public TableColumn<Giocatore, Integer> assistsColumn;
    public TableColumn<Giocatore, Integer> positionColumn;
    public TableColumn<Giocatore, Integer> minutesColumn;
    public TableColumn<Giocatore, Integer> goalsColumn;
    public TableColumn<Giocatore, String> nationalityColumn;
    public Button changeButton;
    public Label name;
    public Label surname;
    public Label salary;
    public Label years;


    ArrayList<Double> namesPositions = new ArrayList<Double>();
    ArrayList<Circle> circles = new ArrayList<>();
    ArrayList<Label> names = new ArrayList<>();
    Model model = new Model();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*todo rendere il textfield non focusabile. Aggiungere i giocatori della squadra alla tabella
          todo far si che quando clicco su un giocatore in tabella, il nome compaia nel textfield e poi si proceda al cambio */

        circles.add(n2);
        circles.add(n3);
        circles.add(n4);
        circles.add(n5);
        circles.add(n6);
        circles.add(n7);
        circles.add(n8);
        circles.add(n9);
        circles.add(n10);
        circles.add(n11);
        ArrayList<Double> positions = new ArrayList<Double>();
        for(Circle c : circles){
            positions.add(c.getLayoutX());
        }

        for(Circle c : circles){
            positions.add(c.getLayoutY());
        }


        positions.add(name2.getLayoutX());
        positions.add(name3.getLayoutX());
        positions.add(name4.getLayoutX());
        positions.add(name5.getLayoutX());
        positions.add(name6.getLayoutX());
        positions.add(name7.getLayoutX());
        positions.add(name8.getLayoutX());
        positions.add(name9.getLayoutX());
        positions.add(name10.getLayoutX());
        positions.add(name11.getLayoutX());
        positions.add(name2.getLayoutY());
        positions.add(name3.getLayoutY());
        positions.add(name4.getLayoutY());
        positions.add(name5.getLayoutY());
        positions.add(name6.getLayoutY());
        positions.add(name7.getLayoutY());
        positions.add(name8.getLayoutY());
        positions.add(name9.getLayoutY());
        positions.add(name10.getLayoutY());
        positions.add(name11.getLayoutY());

        modulo4231.setAll(positions);
        for(int i = 0; i < positions.size(); i++){
            System.out.println(modulo4231.playersPosition.get(i));
        }

        names.add(name2);
        names.add(name3);
        names.add(name4);
        names.add(name5);
        names.add(name6);
        names.add(name7);
        names.add(name8);
        names.add(name9);
        names.add(name10);
        names.add(name11);



        choicer.setValue("4-2-3-1");
        choicer.getItems().addAll("4-2-3-1", "3-5-2","4-4-2", "4-3-3", "3-4-3", "5-3-2");
        choicer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Attiva una funzione quando viene selezionato un nuovo elemento
            onChoiceSelected((String) newValue);
    });
        Platform.runLater(() ->{
            Stage stage = (Stage) name.getScene().getWindow();
            String username = stage.getTitle();
            String team = model.getSquadraByAllenatore(username);
            name.setText(model.getAllenatoreBySquadra(team).getNome());
            System.out.println(model.getAllenatoreBySquadra(team).getCognome() + "DIO");
            surname.setText(model.getAllenatoreBySquadra(team).getCognome());
            salary.setText(String.valueOf(model.getAllenatoreBySquadra(team).getStipendio()));
            years.setText(String.valueOf(model.getAllenatoreBySquadra(team).getAnniContratto()));
            List<Giocatore> giocatori = model.getGiocatoriBySquadra(team);
            ObservableList<Giocatore> observableGiocatori = FXCollections.observableArrayList(giocatori);
            shirtColumn.setCellValueFactory(new PropertyValueFactory<>("numMaglia"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
            surnameColumn.setCellValueFactory(new PropertyValueFactory<>("cognome"));
            ageColumn.setCellValueFactory(new PropertyValueFactory<>("eta"));
            positionColumn.setCellValueFactory(new PropertyValueFactory<>("ruolo"));
            minutesColumn.setCellValueFactory(new PropertyValueFactory<>("MinutiGiocati"));
            goalsColumn.setCellValueFactory(new PropertyValueFactory<>("Goal"));
            assistsColumn.setCellValueFactory(new PropertyValueFactory<>("Assist"));
            nationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nazionalita"));
            playersTable.setItems(observableGiocatori);



        });

    }

    private void onChoiceSelected(String choice) {
        // Esegui una funzione basata sulla scelta
        switch (choice) {
            case "4-2-3-1":
                System.out.println("Hai selezionato 4-2-3-1");
                for(int i = 0; i < 10; i++){
                    circles.get(i).setLayoutX(modulo4231.get(i));

                }
                for(int i = 0; i < 10; i++){
                    circles.get(i).setLayoutY(modulo4231.get(i+10));
                }
                for(int i = 0; i < 10; i++){
                    names.get(i).setLayoutX(modulo4231.get(i+20));
                }
                for(int i = 0; i < 10; i++){
                    names.get(i).setLayoutY(modulo4231.get(i+30));
                }
                break;
            case "3-5-2":
                System.out.println("Hai selezionato 3-5-2");
                for(int i = 0; i < 10; i++){
                    circles.get(i).setLayoutX(modulo352.get(i));
                }

                for(int i = 0; i < 10; i++){
                    circles.get(i).setLayoutY(modulo352.get(i+10));
                }

                for(int i = 0; i < 10; i++){
                    names.get(i).setLayoutX(modulo352.get(i+20));
                }
                for(int i = 0; i < 10; i++){
                    names.get(i).setLayoutY(modulo352.get(i+30));
                }
                break;
            case "4-4-2":
                System.out.println("Hai selezionato 4-4-2");
                for(int i = 0; i < 10; i++){
                    circles.get(i).setLayoutX(modulo442.get(i));
                }

                for(int i = 0; i < 10; i++){
                    circles.get(i).setLayoutY(modulo442.get(i+10));
                }

                for(int i = 0; i < 10; i++){
                    names.get(i).setLayoutX(modulo442.get(i+20));
                }
                for(int i = 0; i < 10; i++){
                    names.get(i).setLayoutY(modulo442.get(i+30));
                }
                break;
            case "4-3-3":
                System.out.println("Hai selezionato 4-3-3");
                for(int i = 0; i < 10; i++){
                    circles.get(i).setLayoutX(modulo433.get(i));
                }

                for(int i = 0; i < 10; i++){
                    circles.get(i).setLayoutY(modulo433.get(i+10));
                }

                for(int i = 0; i < 10; i++){
                    names.get(i).setLayoutX(modulo433.get(i+20));
                }
                for(int i = 0; i < 10; i++){
                    names.get(i).setLayoutY(modulo433.get(i+30));
                }
                break;
            case "3-4-3":
                System.out.println("Hai selezionato 3-4-3");
                for(int i = 0; i < 10; i++){
                    circles.get(i).setLayoutX(modulo343.get(i));
                }

                for(int i = 0; i < 10; i++){
                    circles.get(i).setLayoutY(modulo343.get(i+10));
                }

                for(int i = 0; i < 10; i++){
                    names.get(i).setLayoutX(modulo343.get(i+20));
                }
                for(int i = 0; i < 10; i++){
                    names.get(i).setLayoutY(modulo343.get(i+30));
                }
                break;
            case "5-3-2":
                System.out.println("Hai selezionato 5-3-2");
                for(int i = 0; i < 10; i++){
                    circles.get(i).setLayoutX(modulo532.get(i));
                }

                for(int i = 0; i < 10; i++){
                    circles.get(i).setLayoutY(modulo532.get(i+10));
                }

                for(int i = 0; i < 10; i++){
                    names.get(i).setLayoutX(modulo532.get(i+20));
                }
                for(int i = 0; i < 10; i++){
                    names.get(i).setLayoutY(modulo532.get(i+30));
                }
                break;
            default:
                System.out.println("Selezione non valida");
                break;
        }
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void setLabel(MouseEvent mouseEvent) {
        player = (Label) mouseEvent.getSource();
        changePlayerFIeld.setText(player.getText());
        if(changePlayerFIeld.isFocused()) {
            changePlayerFIeld.getParent().requestFocus();
        }
    }

    public void setPlayernName() {
        if (player != null) {
            double centerX = player.getLayoutX() + player.getWidth() / 2;
            double centerY = player.getLayoutY() + player.getHeight() / 2;
            if (!Objects.equals(changePlayerFIeld.getText(), player.getText())) {
                player.setText(changePlayerFIeld.getText());
                player.setLayoutX(centerX - (player.getText().length() * 5.6 / 2));
            }
        }
    }

    public void changePlayerNameByKeyboard(MouseEvent mouseEvent) {
        System.out.println("Layout Iniziale: " + player.getLayoutX() + " " + player.getText());
        changePlayerFIeld.setOnKeyPressed(event -> {
            if (Objects.requireNonNull(event.getCode()) == KeyCode.ENTER) {
                setPlayernName();
            }
            else{
                System.out.println(player.getText() + " " + player.getLayoutX());
            }
        });
    }

    public void changePlayerNameByClick(MouseEvent actionEvent){
        setPlayernName();
    }

    public void hover(MouseEvent mouseEvent) {
        changeButton.setStyle("-fx-background-color: black; -fx-border-color: White;");
    }

    public void removeHover(MouseEvent mouseEvent) {

        changeButton.setStyle("-fx-background-color: DarkBlue; -fx-border-color: Black;");
    }


}