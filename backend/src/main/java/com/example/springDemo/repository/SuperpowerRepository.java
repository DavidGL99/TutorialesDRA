package com.example.springDemo.repository;

import com.example.springDemo.entity.Superpower;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders ="*")
@RepositoryRestResource 
public interface SuperpowerRepository extends CrudRepository<Superpower, Long> { 
   
}

