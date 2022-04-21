package com.example.springDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.implementation.bind.annotation.Super;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.example.springDemo.entity.Hero;
import com.example.springDemo.entity.Superpower;
import com.example.springDemo.repository.HeroRepository;
import com.example.springDemo.repository.SuperpowerRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("superpowers")
public class SuperpowerController {

    @Autowired
    private SuperpowerRepository powerRepo;
    @Autowired
    private HeroRepository heroRepo;

    @PostMapping
    public void create(Superpower power, Long heroId) {
        powerRepo.save(power);
        heroRepo.findById(heroId).get().addSuperpower(power);
    }

    @GetMapping("")
    List<Superpower> findAll(@RequestParam(required = false) Long id) {
        if (id == null)
            return (List<Superpower>) powerRepo.findAll();

        List<Superpower> power = new ArrayList<Superpower>();
        power.add(powerRepo.findById(id).get());
        return power;
    }

}