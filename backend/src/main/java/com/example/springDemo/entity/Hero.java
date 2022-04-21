package com.example.springDemo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "heroes")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @OneToMany(mappedBy = "hero")
    @JsonManagedReference
    private List<Superpower> superpowers;

    public Hero() {

    }

    public Hero(String name) {
        this.name = name;
    }

    public Hero(String name, List<Superpower> superpowers) {
        this.name = name;
        this.superpowers = superpowers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String names) {
        this.name = names;
    }

    public List<Superpower> getSuperpowers() {
        return this.superpowers;
    }

    public void setSuperpowers(List<Superpower> superpowers) {
        this.superpowers = superpowers;
    }

    public void addSuperpower(Superpower superpower){
        this.superpowers.add(superpower);
    }

   

    @Override
    public String toString() {
        return "Hero [id=" + id + ", name=" + name + ", superpowers=" + superpowers + "]";
    }

}