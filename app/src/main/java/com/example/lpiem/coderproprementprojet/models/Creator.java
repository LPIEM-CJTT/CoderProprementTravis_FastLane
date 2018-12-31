package com.example.lpiem.coderproprementprojet.models;

public class Creator {
    private String name;
    private String role;

    public Creator(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
