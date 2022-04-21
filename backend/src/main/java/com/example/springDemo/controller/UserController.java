package com.example.springDemo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.springDemo.entity.User;
import com.example.springDemo.repository.UserRepository;

@RestController
@RequestMapping("users")
public class UserController {

  private final UserRepository repository;

  UserController(UserRepository repository) {
    this.repository = repository;
  }

  @GetMapping("")
  List<User> findAll() {

    return repository.findAll();
  }

  @GetMapping("/lastName/{lastName}")
  List<User> findByLastName(@PathVariable String lastName) {

    return repository.findByLastName(lastName);
  }

  @GetMapping("/name/{name}")
  User findUserByName(@PathVariable String name) {
    return repository.findUserByName(name);    
  }

  @GetMapping("/id/{id}")
  User findUserById(@PathVariable Long id) {
    return repository.findUserById(id);    
  }
  @DeleteMapping("/id/{id}")
  void deleteUser(@PathVariable Long id){
    repository.delete(repository.findUserById(id));
  }

  @PostMapping("")
  void create( @RequestBody User user) {
    repository.save(user);
  }

}