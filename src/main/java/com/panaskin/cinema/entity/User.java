package com.panaskin.cinema.entity;

public class User extends Entity {

    /**
     * 
     */
    private static final long serialVersionUID = 2914681607399823606L;

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private long roleId;
    private long statusId;
    private String locale;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return "User [login=" + login + 
               ", firstName=" + firstName + 
               ", lastName=" + lastName + 
               ", roleId=" + roleId + 
               ", statusId=" + statusId  + "]";
    }

}
