package com.zechariah.issuetrackerdal.model;

import com.zechariah.issuetrackerdal.model.enums.Status;

import java.util.Objects;

import javax.persistence.*;

@Entity(name = "inspection")
public class InspectionModel { 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;

    private Status status;
    private String name;
    private String date;
    private String comment;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private UserModel user;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private EquipmentModel equipment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private State state;

    public InspectionModel(){
        super();
    }

    public InspectionModel(String description, Status status, String name, String date, String comment) {
        super();
        this.description = description;
        this.status = status;
        this.name = name;
        this.date = date;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserModel getUsers() {
        return user;
    }

    public void setUsers(UserModel users) {
        this.user = users;
    }

    public EquipmentModel getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentModel equipment) {
        this.equipment = equipment;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InspectionModel that = (InspectionModel) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && status == that.status && Objects.equals(name, that.name) && Objects.equals(date, that.date) && Objects.equals(comment, that.comment) && Objects.equals(user, that.user) && Objects.equals(equipment, that.equipment) && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, status, name, date, comment, user, equipment, state);
    }

    @Override
    public String toString() {
        return "InspectionModel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", comment='" + comment + '\'' +
                ", user=" + user +
                ", equipment=" + equipment +
                ", state=" + state +
                '}';
    }
}

