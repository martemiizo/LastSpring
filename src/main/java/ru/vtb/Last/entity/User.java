package ru.vtb.Last.entity;

import org.springframework.stereotype.Component;

@Component
public class User {

    private String login;

    private String password;

    private String firstname;

    private String lastname;

    private Role role;

    public User() {
    }

    public User(String login, String password, String firstname, String lastname, Role role) {
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Role getRole() {
        return role;
    }
}
