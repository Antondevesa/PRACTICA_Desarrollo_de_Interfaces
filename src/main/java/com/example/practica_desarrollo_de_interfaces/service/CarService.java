package com.example.practica_desarrollo_de_interfaces.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.practica_desarrollo_de_interfaces.dao.CarDAO;
import com.example.practica_desarrollo_de_interfaces.model.Car;
import com.example.practica_desarrollo_de_interfaces.model.User;

public class CarService {

    private CarDAO carDAO;

    public CarService() {
        this.carDAO = new CarDAO();
    }

    public List<Car> getAllCars() {
        return carDAO.findAll();
    }

    public Car getMostFavoritedCar(List<User> allUsers, List<Car> allCars) {
        if (allUsers == null || allUsers.isEmpty() || allCars == null || allCars.isEmpty()) {
            return null;
        }

        Map<Integer, Integer> favoriteCounts = new HashMap<>();

        for (User user : allUsers) {
            if (user.getFavoriteCarId() != null) {
                int carId = user.getFavoriteCarId();
                favoriteCounts.put(carId, favoriteCounts.getOrDefault(carId, 0) + 1);
            }
        }

        if (favoriteCounts.isEmpty()) {
            return null;
        }

        int mostFavoritedCarId = favoriteCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        for (Car car : allCars) {
            if (car.getId() == mostFavoritedCarId) {
                return car;
            }
        }

        return null;
    }
    public boolean saveCar(Car car) {
        return carDAO.insert(car);
    }
}