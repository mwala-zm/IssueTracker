package com.zechariah.issuetrackerdal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;


@Entity
public class User {

    private  @Id
    @GeneratedValue
    Long id;
    private String username;
    private String role;

    public User() {}

     User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if(!(o instanceof User))
            return false;

        User users = (User) o;
        return Objects.equals(this.id, users.id) && Objects.equals(this.role, users.role);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.username, this.role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
