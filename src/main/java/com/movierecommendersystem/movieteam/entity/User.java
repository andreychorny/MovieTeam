package com.movierecommendersystem.movieteam.entity;


import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long id;

    private String username;

    private String password;

    private String email;
}
