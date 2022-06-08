package com.zechariah.issuetrackerdal.model;

import javax.persistence.*;
import java.util.Objects;

@Table
@Entity(name = "user" )
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String username;

    @Column(name = "role")
    private String role;

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
