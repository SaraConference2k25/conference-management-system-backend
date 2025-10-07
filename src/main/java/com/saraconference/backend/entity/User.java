package com.saraconference.backend.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestController;


@Entity
@Table(name = "users")
@Getter @Setter
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String role;

}
