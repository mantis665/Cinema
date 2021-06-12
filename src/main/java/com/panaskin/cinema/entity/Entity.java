package com.panaskin.cinema.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 5651392880174336232L;

    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

}
