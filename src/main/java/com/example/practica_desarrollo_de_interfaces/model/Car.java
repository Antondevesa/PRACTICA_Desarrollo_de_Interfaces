package com.example.practica_desarrollo_de_interfaces.model;

import java.time.LocalDate;


public class Car {

    private int id;
    private String model;
    private String type;
    private int enginePower;
    private LocalDate registrationDate;
    private String imagePath;


    public Car(int id, String model, String type, int enginePower, LocalDate registrationDate, String imagePath) {
        this.id = id;
        this.model = model;
        this.type = type;
        this.enginePower = enginePower;
        this.registrationDate = registrationDate;
        this.imagePath = imagePath;
    }

    // --- GETTERS Y SETTERS ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", enginePower=" + enginePower +
                ", registrationDate=" + registrationDate +
                '}';
    }
}