package com.panaskin.cinema.entity;

public enum UserStatus {
    ACTIVE(1), BLOCKED(2);

    int statudId;

    UserStatus(int id) {
        this.statudId = id;
    }

    public int getRoleId() {
        return this.statudId;
    }
}
