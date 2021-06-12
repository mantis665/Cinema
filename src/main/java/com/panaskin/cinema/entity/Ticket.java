package com.panaskin.cinema.entity;

public class Ticket extends Entity {

    /**
     * 
     */
    private static final long serialVersionUID = 5516929581542523227L;
    
    private int seat;
    private long userId;
    private long sessionId;
    
    public int getSeat() {
        return seat;
    }
    public void setSeat(int seat) {
        this.seat = seat;
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
        return "Ticket [seat=" + seat + 
               ", userId=" + userId + 
               ", sessionId=" + sessionId + "]";
    }
    
}
