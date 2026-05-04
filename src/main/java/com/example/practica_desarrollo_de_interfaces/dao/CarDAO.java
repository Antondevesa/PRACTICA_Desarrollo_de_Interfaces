package com.example.practica_desarrollo_de_interfaces.dao;

import com.example.practica_desarrollo_de_interfaces.model.Car;
import com.example.practica_desarrollo_de_interfaces.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();

        String sql = "SELECT * FROM cars";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                java.sql.Date sqlDate = rs.getDate("registrationDate");
                LocalDate localDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                Car car = new Car(
                        rs.getInt("id"),
                        rs.getString("model"),
                        rs.getString("type"),
                        rs.getInt("enginePower"),
                        rs.getDate("registrationDate").toLocalDate(),
                        rs.getString("imagePath")
                );

                cars.add(car);
            }

        } catch (SQLException e) {
            System.err.println("Error grave intentando recuperar la lista de coches de la BD.");
            e.printStackTrace();
        }

        return cars;
    }


    public boolean insert(Car car) {
        String sql = "INSERT INTO cars (model, type, enginePower, registrationDate, imagePath) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, car.getModel());
            pstmt.setString(2, car.getType());
            pstmt.setInt(3, car.getEnginePower());
            pstmt.setDate(4, java.sql.Date.valueOf(car.getRegistrationDate()));
            pstmt.setString(5, car.getImagePath());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}