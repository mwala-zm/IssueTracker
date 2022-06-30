package com.zechariah.issuetrackerdal.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "equipment")
public class EquipmentModel {

    private @Id @GeneratedValue Long id;
    private String name;
    private String description;
    private String location;

    EquipmentModel() {}

    public EquipmentModel(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentModel that = (EquipmentModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, location);
    }

    @Override
    public String toString() {
        return "EquipmentModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}