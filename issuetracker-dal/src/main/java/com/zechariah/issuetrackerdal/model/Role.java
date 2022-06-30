package com.zechariah.issuetrackerdal.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    @Column(name = "authority_role")
    private String authority;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private UserModel user;


    public Role() {
    }

    public Role(String authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name) && Objects.equals(authority, role.authority) && Objects.equals(user, role.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, authority, user);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authority='" + authority + '\'' +
                ", user=" + user +
                '}';
    }
}
