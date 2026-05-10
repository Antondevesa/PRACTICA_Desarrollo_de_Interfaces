package com.example.practica_desarrollo_de_interfaces.controller;

import com.example.practica_desarrollo_de_interfaces.model.User;
import com.example.practica_desarrollo_de_interfaces.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class UsersController {

    @FXML private TableView<User> usersTable;
    @FXML private TableColumn<User, String> nameColumn;
    @FXML private TableColumn<User, String> emailColumn;
    @FXML private TableColumn<User, String> passwordColumn;
    @FXML private TableColumn<User, Integer> favColumn;
    @FXML private TextField searchEmailField;

    private UserService userService;
    private ObservableList<User> masterData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        userService = new UserService();

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        favColumn.setCellValueFactory(new PropertyValueFactory<>("favoriteCarId"));

        loadUsers();

        FilteredList<User> filteredData = new FilteredList<>(masterData, p -> true);
        searchEmailField.textProperty().addListener((obs, oldVal, newVal) -> {
            filteredData.setPredicate(user -> {
                if (newVal == null || newVal.isEmpty()) return true;
                return user.getEmail() != null && user.getEmail().toLowerCase().contains(newVal.toLowerCase());
            });
        });
        usersTable.setItems(filteredData);
    }

    private void loadUsers() {
        masterData.setAll(userService.getAllUsers());
    }

    @FXML
    private void deleteUser() {
        User selected = usersTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Estás seguro de borrar a " + selected.getUsername() + "?", ButtonType.YES, ButtonType.NO);
            if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                if (userService.deleteUser(selected.getId())) {
                    loadUsers();
                } else {
                    new Alert(Alert.AlertType.ERROR, "No se pudo borrar el usuario.").show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Por favor, selecciona un usuario de la tabla primero.").show();
        }
    }

    @FXML
    private void handleChangeRole() {
        User selected = usersTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String newRole = selected.getRole().name().equals("ADMIN") ? "USER" : "ADMIN";

            if (userService.updateUserRole(selected.getId(), newRole)) {
                loadUsers();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Rol Actualizado");
                alert.setHeaderText("Cambio de Rol Exitoso");
                alert.setContentText("El usuario '" + selected.getUsername() + "' ahora tiene el rol: " + newRole);
                alert.show();

            } else {
                new Alert(Alert.AlertType.ERROR, "No se pudo cambiar el rol.").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Por favor, selecciona un usuario de la tabla primero.").show();
        }
    }

    @FXML
    private void addUser() {
        try {
            com.example.practica_desarrollo_de_interfaces.util.ViewSwitcher.switchToWindow(
                    com.example.practica_desarrollo_de_interfaces.util.AppView.REGISTER.getFxmlPath(),
                    "Crear Nuevo Usuario"
            );
        } catch (Exception e) {
        }
    }

    @FXML
    private void handleModify() {
        User selected = usersTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Por favor, selecciona un usuario primero.").show();
            return;
        }

        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle("Modificar Usuario");
        dialog.setHeaderText("Editando datos de: " + selected.getUsername());

        ButtonType saveButtonType = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField usernameField = new TextField(selected.getUsername());
        TextField emailField = new TextField(selected.getEmail());

        grid.add(new Label("Usuario:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(emailField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                selected.setUsername(usernameField.getText());
                selected.setEmail(emailField.getText());
                return selected;
            }
            return null;
        });

        Optional<User> result = dialog.showAndWait();
        result.ifPresent(userToUpdate -> {
            if (userService.updateUser(userToUpdate)) {
                loadUsers();
                new Alert(Alert.AlertType.INFORMATION, "Usuario actualizado correctamente.").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Error al guardar en la base de datos.").show();
            }
        });
    }
}