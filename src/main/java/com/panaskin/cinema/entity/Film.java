package com.panaskin.cinema.entity;

public class Film extends Entity{

    /**
     * 
     */
    private static final long serialVersionUID = -266056502130879393L;
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
