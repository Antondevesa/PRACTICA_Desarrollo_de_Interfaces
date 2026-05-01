module com.example.practica_desarrollo_de_interfaces {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.practica_desarrollo_de_interfaces to javafx.fxml;
    exports com.example.practica_desarrollo_de_interfaces;
}