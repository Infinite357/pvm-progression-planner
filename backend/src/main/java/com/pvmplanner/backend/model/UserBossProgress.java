,.[]-\/package com.pvmplanner.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserBossProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "boss_id")
    private Boss boss;

    private int killCount;

    private boolean completed;

    private String confidence;

    public UserBossProgress() {
    }

    public UserBossProgress(Boss boss, int killCount, boolean completed, String confidence) {
        this.boss = boss;
        this.killCount = killCount;
        this.completed = completed;
        this.confidence = confidence;
    }

    public Long getId() {
        return id;
    }

    public Boss getBoss() {
        return boss;
    }

    public int getKillCount() {
        return killCount;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }
}