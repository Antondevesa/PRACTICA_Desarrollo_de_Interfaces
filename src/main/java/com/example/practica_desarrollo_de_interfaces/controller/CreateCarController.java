package com.example.practica_desarrollo_de_interfaces.controller;

import com.example.practica_desarrollo_de_interfaces.model.Car;
import com.example.practica_desarrollo_de_interfaces.service.CarService;
import com.example.practica_desarrollo_de_interfaces.util.AppView;
import com.example.practica_desarrollo_de_interfaces.util.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class CreateCarController {

    @FXML private TextField modelField;
    @FXML private ComboBox<String> typeCombo;
    @FXML private TextField powerField;
    @FXML private DatePicker datePicker;
    @FXML private Label statusLabel;

    private CarService carService;

    @FXML
    public void initialize() {
        carService = new CarService();
        typeCombo.getItems().addAll("Berlina", "Deportivo", "Furgoneta", "SUV", "Utilitario");
    }

    @FXML
    private void handleSave() {
        try {
            String model = modelField.getText();
            String type = typeCombo.getValue();
            int power = Integer.parseInt(powerField.getText());
            LocalDate date = datePicker.getValue();

            if (model.isEmpty() || type == null || date == null) {
                statusLabel.setText("Por favor, rellena todos los campos.");
                return;
            }

            Car newCar = new Car(0, model, type, power, date, "/images/default_car.png");

            if (carService.saveCar(newCar)) {
                goBack();
            } else {
                statusLabel.setText("Error al guardar en la base de datos.");
            }

        } catch (NumberFormatException e) {
            statusLabel.setText("La potencia debe ser un número válido.");
        }
    }

    @FXML
    private void goBack() {
        ViewSwitcher.loadViewInMainPanel(AppView.CARS);
    }
}