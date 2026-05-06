package com.example.practica_desarrollo_de_interfaces.service;

import com.example.practica_desarrollo_de_interfaces.dao.UserDAO;
import com.example.practica_desarrollo_de_interfaces.model.User;
import com.example.practica_desarrollo_de_interfaces.model.Role;
import com.example.practica_desarrollo_de_interfaces.util.SecurityUtil;
import java.util.List;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user != null && SecurityUtil.checkPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public boolean registerUser(String username, String email, String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(SecurityUtil.hashPassword(password));
        newUser.setRole(Role.USER);
        return userDAO.insert(newUser);
    }

    public List<User> getAllUsers() { return userDAO.findAll(); }
    public boolean deleteUser(int id) { return userDAO.delete(id); }
    public boolean updateUserRole(int id, String roleName) {
        return userDAO.updateRole(id, roleName);
    }

    public boolean updateUser(User user) {
        return userDAO.update(user);
    }

    public boolean updateUserFavorite(int userId, int carId) {
        return userDAO.updateUserFavorite(userId, carId);
    }
}