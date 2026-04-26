package com.example.practica_desarrollo_de_interfaces.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewSwitcher {
    private static Stage primaryStage;
    private static BorderPane mainRoot;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void setMainRoot(BorderPane root) {
        mainRoot = root;
    }

    public static void switchToWindow(AppView view) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFxmlPath()));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle(view.getTitle());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToWindow(String path, String title) {
        try {
            Parent root = FXMLLoader.load(ViewSwitcher.class.getResource(path));
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void loadViewInMainPanel(AppView view) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFxmlPath()));
            mainRoot.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}