package com.pvmplanner.backend.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "user_boss_progress",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_progress_player_boss",
                        columnNames = {"player_profile_id", "boss_id"}
                )
        }
)
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

    @ManyToOne
    @JoinColumn(name = "player_profile_id")
    private PlayerProfile playerProfile;

    public void setPlayerProfile(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    public PlayerProfile getPlayerProfile() {
        return playerProfile;
    }
}