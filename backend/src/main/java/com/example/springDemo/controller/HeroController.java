package com.example.springDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.example.springDemo.entity.Hero;
import com.example.springDemo.entity.Superpower;
import com.example.springDemo.repository.HeroRepository;
import com.example.springDemo.repository.SuperpowerRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("heroes")
public class HeroController {

  @Autowired
  private HeroRepository repository;
  @Autowired
  private SuperpowerRepository powerRepo;
  @Autowired
  SuperpowerController controller;

  @GetMapping("/superpowers")
  List<Superpower> findPowersOfHeros(@RequestParam(required = true) Long id) {

    List<Superpower> powers = new ArrayList<Superpower>();
    powers.addAll(repository.findById(id).get().getSuperpowers());
    return powers;
  }

  @DeleteMapping("/superpowers")
  ResponseEntity<Hero> deletePowerOfHero(@RequestParam(required = true) Long id, @RequestBody Superpower power) {
    Hero hero = repository.findById(id).get();

    for (Superpower s : hero.getSuperpowers()) {
      if (s.getpowerName().equals(power.getpowerName())) {
        s.setHero(null);
        powerRepo.delete(s);

      }
    }

    return ResponseEntity.ok().body(hero);

  }

  @GetMapping("")
  List<Hero> findAll(@RequestParam(required = false) Long id) {
    if (id == null)
      return (List<Hero>) repository.findAll();

    List<Hero> hero = new ArrayList<Hero>();
    hero.add(repository.findById(id).get());
    return hero;
  }

  @PostMapping("")
  ResponseEntity<Hero> create(@RequestBody Hero hero) {
    return ResponseEntity.ok().body(repository.save(hero));
  }

  @DeleteMapping("")
  void delete(@RequestParam(required = true) Long id) {

    System.out.println(repository.findById(id).get());

    for (Superpower s : repository.findById(id).get().getSuperpowers()) {
      this.deletePowerOfHero(id, powerRepo.findById(s.getId()).get());

    }
    repository.deleteById(id);
  }

  @PutMapping("")
  public ResponseEntity<Hero> updateHero(@RequestBody Hero hero) {

    Hero h = repository.findById(hero.getId()).get();
    h.setName(hero.getName());

    for (Superpower s : hero.getSuperpowers()) {
      s.setHero(h);
      powerRepo.save(s);
    }

    return ResponseEntity.ok().body(repository.save(h));

  }

}