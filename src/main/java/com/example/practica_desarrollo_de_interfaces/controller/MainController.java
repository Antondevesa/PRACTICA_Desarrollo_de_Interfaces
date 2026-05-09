package com.example.practica_desarrollo_de_interfaces.controller;

import com.example.practica_desarrollo_de_interfaces.util.AppView;
import com.example.practica_desarrollo_de_interfaces.util.SessionManager;
import com.example.practica_desarrollo_de_interfaces.util.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class MainController {

    @FXML private BorderPane mainRoot;

    @FXML private Button btnUsuarios;

    @FXML
    public void initialize() {
        ViewSwitcher.setMainRoot(mainRoot);

        if (!SessionManager.getInstance().isAdmin()) {
            btnUsuarios.setVisible(false);
            btnUsuarios.setManaged(false);
        }

        showCars();
    }

    @FXML
    private void showCars() {
        ViewSwitcher.loadViewInMainPanel(AppView.CARS);
    }

    @FXML
    private void showUsers() {
        if (SessionManager.getInstance().isAdmin()) {
            ViewSwitcher.loadViewInMainPanel(AppView.USERS);
        }
    }

    @FXML
    private void handleLogout() {
        SessionManager.getInstance().logout();
        ViewSwitcher.switchToWindow(AppView.LOGIN);
    }
}