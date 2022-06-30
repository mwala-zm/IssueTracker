package com.zechariah.issuetrackerdal.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class UserModel implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;
  private String username;
  private String password;

  @ManyToOne(optional = false)
  private Role role;

  public UserModel() {
  }

  public UserModel(String username, String password, Role role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> roles = new ArrayList<>();
    roles.add(new Role("Inspector"));
    roles.add(new Role("Supervisor"));
    return roles;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserModel userModel = (UserModel) o;
    return Objects.equals(id, userModel.id) && Objects.equals(username, userModel.username) && Objects.equals(password, userModel.password) && Objects.equals(role, userModel.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, password, role);
  }

  @Override
  public String toString() {
    return "UserModel{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", role=" + role +
            '}';
  }
}