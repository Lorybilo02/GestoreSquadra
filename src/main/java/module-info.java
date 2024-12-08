module org.example.teammanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.teammanagement to javafx.fxml;
    exports org.example.teammanagement;
}