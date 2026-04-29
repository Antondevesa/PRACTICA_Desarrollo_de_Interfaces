package com.example.practica_desarrollo_de_interfaces.model;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private Integer favoriteCarId;

    public User() {
    }

    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(int id, String username, String email, String password, Role role, Integer favoriteCarId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.favoriteCarId = favoriteCarId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Integer getFavoriteCarId() { return favoriteCarId; }
    public void setFavoriteCarId(Integer favoriteCarId) { this.favoriteCarId = favoriteCarId; }
}