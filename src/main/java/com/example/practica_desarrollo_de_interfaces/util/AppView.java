package com.example.practica_desarrollo_de_interfaces.util;

public enum AppView {
    LOGIN("/com/example/practica_desarrollo_de_interfaces/login_view.fxml", "Iniciar Sesión"),
    REGISTER("/com/example/practica_desarrollo_de_interfaces/register.fxml", "Registro de Usuario"),
    MAIN("/com/example/practica_desarrollo_de_interfaces/main_view.fxml", "Panel Principal"),
    CARS("/com/example/practica_desarrollo_de_interfaces/cars_view.fxml", "Catálogo de Coches"),
    USERS("/com/example/practica_desarrollo_de_interfaces/users_view.fxml", "Gestión de Usuarios"),
    CREATE_CAR("/com/example/practica_desarrollo_de_interfaces/create_car_view.fxml", "Añadir Nuevo Coche");

    private final String fxmlPath;
    private final String title;

    AppView(String fxmlPath, String title) {
        this.fxmlPath = fxmlPath;
        this.title = title;
    }

    public String getFxmlPath() { return fxmlPath; }
    public String getTitle() { return title; }
}