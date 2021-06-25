package com.panaskin.cinema.entity;

public class Ticket extends Entity {

    /**
     * 
     */
    private static final long serialVersionUID = 5516929581542523227L;
    
    private long id;
    private long userId;
    private long sessionId;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public long getSessionId() {
        return sessionId;
    }
    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }
    @Override
    public String toString() {
        return "Ticket [seat=" + id + 
               ", userId=" + userId + 
               ", sessionId=" + sessionId + "]";
    }
}
