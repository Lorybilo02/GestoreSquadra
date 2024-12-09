module org.example.teammanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires spring.security.crypto;


    opens org.example.teammanagement to javafx.fxml;
    exports org.example.teammanagement;
}