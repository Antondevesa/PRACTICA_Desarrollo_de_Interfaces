package com.example.practica_desarrollo_de_interfaces.controller;

import com.example.practica_desarrollo_de_interfaces.service.UserService;
import com.example.practica_desarrollo_de_interfaces.util.AppView;
import com.example.practica_desarrollo_de_interfaces.util.SecurityUtil;
import com.example.practica_desarrollo_de_interfaces.util.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField checkPasswordField;
    @FXML private Label errorLabel;

    private UserService userService;

    @FXML
    public void initialize() {
        userService = new UserService();
        errorLabel.setText("");
    }

    @FXML
    public void handleRegister() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String checkPassword = checkPasswordField.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || checkPassword.isEmpty()) {
            errorLabel.setText("Todos los campos son obligatorios.");
            return;
        }

        if (!password.equals(checkPassword)) {
            errorLabel.setText("Las contraseñas no coinciden.");
            return;
        }

        if (password.length() < 6 || !password.matches(".*[a-zA-Z]+.*") || !password.matches(".*[0-9]+.*")) {
            errorLabel.setText("La contraseña debe tener al menos 6 caracteres, letras y números.");
            return;
        }

        String hashedPassword = SecurityUtil.hashPassword(password);

        boolean registrado = userService.registerUser(username, email, hashedPassword);

        if (registrado) {
            ViewSwitcher.switchToWindow(AppView.LOGIN);
        } else {
            errorLabel.setText("El nombre de usuario ya existe. Elige otro.");
        }
    }

    @FXML
    public void goToLogin() {
        ViewSwitcher.switchToWindow(AppView.LOGIN);
    }
}