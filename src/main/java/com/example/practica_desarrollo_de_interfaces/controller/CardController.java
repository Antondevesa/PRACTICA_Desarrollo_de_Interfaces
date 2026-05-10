package com.example.practica_desarrollo_de_interfaces.controller;

import com.example.practica_desarrollo_de_interfaces.model.Car;
import com.example.practica_desarrollo_de_interfaces.model.User;
import com.example.practica_desarrollo_de_interfaces.service.CarService;
import com.example.practica_desarrollo_de_interfaces.service.UserService;
import com.example.practica_desarrollo_de_interfaces.util.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CardController {

    @FXML
    private VBox cardContainer;
    @FXML
    private ImageView carImage;
    @FXML
    private Label typeLabel;
    @FXML
    private Label powerLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label communityFavLabel;
    @FXML
    private Button favButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;

    private Car car;
    private CarsController parentController;
    private final UserService userService = new UserService();
    private final CarService carService = new CarService();

    public void setCarData(Car car, CarsController parentController, Car communityFav) {
        this.car = car;
        this.parentController = parentController;

        typeLabel.setText(car.getType() + " - " + car.getModel());
        powerLabel.setText("Potencia: " + car.getEnginePower() + " CV");
        dateLabel.setText("Matriculación: " + car.getRegistrationDate());

        try {
            String imagePath = car.getImagePath();
            if (imagePath != null && !imagePath.isEmpty()) {
                carImage.setImage(new Image(getClass().getResourceAsStream(imagePath)));
            } else {
                carImage.setImage(new Image(getClass().getResourceAsStream("/images/default_car.png")));
            }
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + e.getMessage());
        }

        boolean isAdmin = SessionManager.getInstance().isAdmin();
        updateButton.setVisible(isAdmin);
        updateButton.setManaged(isAdmin);
        deleteButton.setVisible(isAdmin);
        deleteButton.setManaged(isAdmin);

        applyCustomStyles(communityFav);
    }

    private void applyCustomStyles(Car communityFav) {
        java.net.URL cssUrl = getClass().getResource("/css/styles.css");
        if (cssUrl != null) {
            String cssPath = cssUrl.toExternalForm();
            if (!cardContainer.getStylesheets().contains(cssPath)) {
                cardContainer.getStylesheets().add(cssPath);
            }
        }

        cardContainer.getStyleClass().removeAll("card-view", "card-favorito-usuario", "card-favorito-comunidad");
        cardContainer.getStyleClass().add("card-view");

        User currentUser = SessionManager.getInstance().getCurrentUser();

        if (currentUser != null && currentUser.getFavoriteCarId() != null) {
            if (currentUser.getFavoriteCarId() == car.getId()) {
                // Es tu favorito
                cardContainer.getStyleClass().add("card-favorito-usuario");
                favButton.setText("\u2764");
                favButton.setStyle("-fx-text-fill: #e74c3c; -fx-background-color: transparent; -fx-font-size: 22px; -fx-cursor: hand; -fx-padding: 0;");
            } else {
                favButton.setText("\u2661");
                favButton.setStyle("-fx-text-fill: #333333; -fx-background-color: transparent; -fx-font-size: 22px; -fx-cursor: hand; -fx-padding: 0;");
            }
        } else {
            favButton.setText("\u2661");
            favButton.setStyle("-fx-text-fill: #333333; -fx-background-color: transparent; -fx-font-size: 22px; -fx-cursor: hand; -fx-padding: 0;");
        }

        if (communityFav != null && communityFav.getId() == car.getId()) {
            cardContainer.getStyleClass().add("card-favorito-comunidad");
            communityFavLabel.setVisible(true);
            communityFavLabel.setManaged(true);
        } else {
            communityFavLabel.setVisible(false);
            communityFavLabel.setManaged(false);
        }
    }

    @FXML
    private void handleFavorite() {
        User currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            boolean guardadoOK = userService.updateUserFavorite(currentUser.getId(), car.getId());
            if (guardadoOK) {
                currentUser.setFavoriteCarId(car.getId());
                parentController.initializeCars();
            }
        }
    }

    @FXML
    private void handleUpdate() {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Editar Coche");
        alert.setHeaderText("Modificar: " + car.getModel());
        alert.setContentText("La función de edición estará disponible en la próxima actualización del sistema.");
        alert.showAndWait();
    }

    @FXML
    private void handleDelete() {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setTitle("Borrar Coche");
        alert.setHeaderText("¿Eliminar " + car.getModel() + "?");
        alert.setContentText("No tienes los permisos de base de datos necesarios para borrar este vehículo en este momento.");
        alert.showAndWait();
    }
};