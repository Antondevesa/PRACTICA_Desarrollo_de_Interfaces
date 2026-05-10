module com.example.practica_desarrollo_de_interfaces {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.practica_desarrollo_de_interfaces.controller to javafx.fxml;
    opens com.example.practica_desarrollo_de_interfaces.model to javafx.base;
    exports com.example.practica_desarrollo_de_interfaces;
}