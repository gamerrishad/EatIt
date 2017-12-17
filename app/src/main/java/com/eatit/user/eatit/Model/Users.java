package com.eatit.user.eatit.Model;

/**
 * Created by User on 18-Nov-17.
 */

public class Users {
    private String name, password;

    public Users() {
        name = "Abcd";
        password = "123456";
    }

    public Users(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
