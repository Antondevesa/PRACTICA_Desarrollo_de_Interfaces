package com.example.practica_desarrollo_de_interfaces.controller;

import com.example.practica_desarrollo_de_interfaces.model.User;
import com.example.practica_desarrollo_de_interfaces.service.UserService;
import com.example.practica_desarrollo_de_interfaces.util.AppView;
import com.example.practica_desarrollo_de_interfaces.util.SessionManager;
import com.example.practica_desarrollo_de_interfaces.util.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final UserService userService = new UserService();

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Rellena todos los campos.");
            return;
        }

        User user = userService.login(username, password);

        if (user != null) {
            SessionManager.getInstance().setCurrentUser(user);

            ViewSwitcher.switchToWindow(AppView.MAIN.getFxmlPath(), "Catálogo de Coches");
        } else {
            errorLabel.setText("Usuario o contraseña incorrectos.");
        }
    }

    @FXML
    private void goToRegister() {
        ViewSwitcher.switchToWindow(AppView.REGISTER.getFxmlPath(), "Registro de Usuario");
    }
}