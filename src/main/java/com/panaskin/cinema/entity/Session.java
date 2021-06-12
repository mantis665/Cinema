package com.panaskin.cinema.entity;

import java.sql.Time;
import java.util.Date;

public class Session extends Entity {

    /**
     * 
     */
    private static final long serialVersionUID = 3523373710245794232L;

    private long id;
    private Date date;
    private Time start;
    private Film film;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setStart(Time beginingTime) {
        this.start = beginingTime;
    }

    public Time getTime() {
        return start;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Film getFilm() {
        return film;
    }

    @Override
    public String toString() {
        return "Session [id=" + id + ",date=" + date + ", " + "beginingTime=" + start + ", " + "filmId=" + film + "]";
    }
}
