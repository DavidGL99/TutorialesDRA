package com.example.springDemo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "superpowers")
public class Superpower {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    @NotBlank(message = "powerName is mandatory")
    private String powerName;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Hero.class)
    @JoinColumn(name = "hero_id")
    @JsonBackReference
    private Hero hero;

    public Superpower() {

    }
    public Superpower(String powerName) {
        this.powerName = powerName;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setHero(Hero hero){
        this.hero = hero;
    }
    public Hero getHero(){
        return this.hero;
    }
    public String getpowerName() {
        return powerName;
    }
    public void setpowerName(String powerName) {
        this.powerName = powerName;
    }
    @Override
    public String toString() {
        return "Superpower [id=" + id + ", powerName=" + powerName + "]";
    }
}