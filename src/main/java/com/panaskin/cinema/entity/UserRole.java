package com.panaskin.cinema.entity;

public enum UserRole {
    ADMIN(1), CLIENT(2); 

    int roleId;

    UserRole(int id) {
        this.roleId = id;
    }

    public int getRoleId() {
        return this.roleId;
    }
}
