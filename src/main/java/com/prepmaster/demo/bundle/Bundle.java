package com.prepmaster.demo.bundle;

import java.time.LocalDate;

public class Bundle {
    private Long id;
    private String name;
    private String description;
    private LocalDate timeAllowed;

    public Bundle() {
    }

    public Bundle(
            String name,
            String description,
            LocalDate timeAllowed) {
        this.name = name;
        this.description = description;
        this.timeAllowed = timeAllowed;
    }

    public Bundle(
            Long id,
            String name,
            String description,
            LocalDate timeAllowed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.timeAllowed = timeAllowed;
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

    public LocalDate getTimeAllowed() {
        return timeAllowed;
    }

    public void setTimeAllowed(LocalDate timeAllowed) {
        this.timeAllowed = timeAllowed;
    }

    @Override
    public String toString() {
        return "Bundle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", timeAllowed=" + timeAllowed +
                '}';
    }
}
