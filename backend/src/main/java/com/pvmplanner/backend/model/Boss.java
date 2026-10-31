package com.pvmplanner.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Boss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String difficulty;

    public Boss() {
    }

    public Boss(String name, String difficulty) {
        this.name = name;
        this.difficulty = difficulty;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}