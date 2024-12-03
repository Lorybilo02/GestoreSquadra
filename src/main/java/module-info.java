module org.example.teammanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.teammanagement to javafx.fxml;
    exports org.example.teammanagement;
}