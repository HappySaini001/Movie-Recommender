/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happy.movie.recommender.model;

import jakarta.persistence.*; // Imports JPA tools

@Entity // Tells Spring: "This class represents a database table"
@Table(name = "movies") // Tells Spring: "Name the table 'movies' in MySQL"
public class Movie {

    @Id // Marks this field as the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the ID (1, 2, 3...)
    private Long id;

    @Column(length = 500) // Increases the allowed length for titles
    private String title;

    private String director;

    @Column(length = 2000) // Comb stores keywords/cast, so we need extra space
    private String comb;

    // --- CONSTRUCTORS ---
    
    // Empty constructor is required by JPA/Hibernate
    public Movie() {
    }

    // Constructor we will use to save data easily
    public Movie(String title, String director, String comb) {
        this.title = title;
        this.director = director;
        this.comb = comb;
    }

    // --- GETTERS AND SETTERS ---
    // (These allow other parts of your app to read/write these fields)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getComb() {
        return comb;
    }

    public void setComb(String comb) {
        this.comb = comb;
    }
}
