package com.example.practica_desarrollo_de_interfaces.util;

public enum AppView {
    LOGIN("com/example/practica_desarrollo_de_interfaces/login_view.fxml", "Iniciar Sesión"),;

    private final String fxmlPath;
    private final String title;

    AppView(String fxmlPath, String title) {
        this.fxmlPath = fxmlPath;
        this.title = title;
    }

    public String getFxmlPath() { return fxmlPath; }
    public String getTitle() { return title; }
}