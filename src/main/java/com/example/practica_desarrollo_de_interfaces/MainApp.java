package com.example.practica_desarrollo_de_interfaces;

import com.example.practica_desarrollo_de_interfaces.util.AppView;
import com.example.practica_desarrollo_de_interfaces.util.ViewSwitcher;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        ViewSwitcher.setPrimaryStage(stage);

        ViewSwitcher.switchToWindow(AppView.LOGIN);

        stage.setTitle("Gestión de Coches - Práctica Desarrollo de Interfaces");
    }

    public static void main(String[] args) {
        launch();
    }
}