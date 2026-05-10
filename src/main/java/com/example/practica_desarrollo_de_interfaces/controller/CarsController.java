package com.example.practica_desarrollo_de_interfaces.controller;

import com.example.practica_desarrollo_de_interfaces.model.Car;
import com.example.practica_desarrollo_de_interfaces.service.CarService;
import com.example.practica_desarrollo_de_interfaces.service.UserService;
import com.example.practica_desarrollo_de_interfaces.util.AppView;
import com.example.practica_desarrollo_de_interfaces.util.SessionManager;
import com.example.practica_desarrollo_de_interfaces.util.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CarsController {

    @FXML private FlowPane carsFlowPane;
    @FXML private TextField searchField;
    @FXML private Button btnAddCar;

    private final CarService carService = new CarService();
    private final UserService userService = new UserService();
    private List<Car> allCars;

    @FXML
    public void initialize() {
        boolean isAdmin = SessionManager.getInstance().isAdmin();
        btnAddCar.setVisible(isAdmin);
        btnAddCar.setManaged(isAdmin);

        initializeCars();

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterCars(newValue);
        });
    }

    public void initializeCars() {
        allCars = carService.getAllCars();
        renderCars(allCars);
    }

    private void filterCars(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            renderCars(allCars);
        } else {
            List<Car> filtered = allCars.stream()
                    .filter(c -> c.getType().toLowerCase().contains(keyword.toLowerCase()) ||
                            c.getModel().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());
            renderCars(filtered);
        }
    }

    private void renderCars(List<Car> cars) {
        carsFlowPane.getChildren().clear();

        Car communityFav = carService.getMostFavoritedCar(userService.getAllUsers(), allCars);

        for (Car car : cars) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/practica_desarrollo_de_interfaces/card_view.fxml"));

                VBox cardNode = loader.load();

                CardController controller = loader.getController();

                controller.setCarData(car, this, communityFav);

                carsFlowPane.getChildren().add(cardNode);

            } catch (IOException e) {
                System.err.println("Error al cargar la tarjeta del coche: " + car.getModel());
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void goToCreateCar() {
        ViewSwitcher.loadViewInMainPanel(AppView.CREATE_CAR);
    }
}