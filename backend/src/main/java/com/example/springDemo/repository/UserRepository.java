package com.example.springDemo.repository;

import java.util.List;

import com.example.springDemo.entity.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders ="*")
@RepositoryRestResource 
public interface UserRepository extends CrudRepository<User, Long> { 
    List<User> findByLastName(String lastName);
    List<User> findAll();
    User findUserByName(String name);
    User findUserById(Long id);

}


