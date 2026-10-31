package com.pvmplanner.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PlayerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String rsn;
    private int attack;
    private int strength;
    private int defence;
    private int ranged;
    private int magic;
    private int necromancy;
    private int prayer;
    private int herblore;
    private int summoning;

    public PlayerProfile() {
    }

    public PlayerProfile(String rsn) {
        this.rsn = rsn;
    }

    public Long getId() {
        return id;
    }

    public String getRsn() {
        return rsn;
    }

    public void setRsn(String rsn) {
        this.rsn = rsn;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getNecromancy() {
        return necromancy;
    }

    public void setNecromancy(int necromancy) {
        this.necromancy = necromancy;
    }

    public int getHerblore() {
        return herblore;
    }

    public void setHerblore(int herblore) {
        this.herblore = herblore;
    }

    public int getSummoning() {
        return summoning;
    }

    public void setSummoning(int summoning) {
        this.summoning = summoning;
    }

    public int getPrayer() {
        return prayer;
    }

    public void setPrayer(int prayer) {
        this.prayer = prayer;
    }

    public int getRanged() {
        return ranged;
    }

    public void setRanged(int ranged) {
        this.ranged = ranged;
    }
}
