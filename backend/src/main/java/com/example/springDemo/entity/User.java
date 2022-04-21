package com.example.springDemo.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity 
@Table(name = "users") 
public class User {


    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private long id;

    @NotBlank(message = "Name is mandatory") 
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email 
    private String email;

    @NotBlank(message = "lastName is mandatory") 
    private String lastName;

    public User(){
        
    }
    
    public User(String name, String email, String lastName) {
        this.name = name;
        this.email = email;
        this.lastName = lastName;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", lastName=" + lastName + "]";
    }

}