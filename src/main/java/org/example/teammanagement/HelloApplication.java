package org.example.teammanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 590, 372);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        Model model = new Model();
        /*model.addGiocatore("RomeroL", "LakakaR", "Romero", "Lakaka","ATT", 31, 9, "Belgio", "SX", "Longobarda", 2134, 2, 4,5, 567, 1);
        model.addGiocatore("AlessandroD","DipieroA", "Alessandro", "Dipiero","CEN",30,8,"Italia","DX","Longobarda",5682,2,3,7,897,1);
        model.addGiocatore("GigiB", "BuffoneG","Gigi","Buffone","POR",24,77,"Italia","DX","Longobarda",5782,6,0,1,1097,0);
        model.addGiocatore("ReggG","Giorgio","Regg","Giorgio","DEF",28,3,"Italia","SX","Longobarda",3471,6,1,2,1059,1);
        model.addGiocatore("FedericoB","BernardiniF","Federico","Bernardini","CEN",21,20,"Italia","SX","Longobarda",676,2,3,2,958,1);
        model.addGiocatore("KylianoP","PempèK","Kyliano","Pempè","ATT",25,7,"Francia","DX","Longobarda",2676,4,6,2,983,1);
        model.addGiocatore("RichardC","CalaflowersR","Richard","Calaflowers","DEF",23,4,"Inghilterra","SX","Longobarda",676,4,0,2,765,1);
        model.addGiocatore("AdamM","MatarolaA","Adam","Matarola","DEF",27,27,"Spagna","SX","Longobarda",632,2,1,5,765,1);
        model.addGiocatore("AlvaraM","MataraA","Alvara","Matara","ATT",30,19,"Spagna","DX","Longobarda",566,3,2,1,265,0);
        model.addGiocatore("MarcusV","VettarriM","Marcus","Vettarri","CEN",19,45,"Italia","DX","Longobarda",345,2,0,2,317,0);
        model.addGiocatore("StefanoD","DevriesS","Stefano","Devries","DIF",33,15,"Italia","DX","Longobarda",223,2,0,0,341,0);
    */}

    public static void main(String[] args) {
        Model.getInstance().testConnection();
        launch();
    }
}
