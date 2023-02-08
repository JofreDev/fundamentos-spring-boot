package com.fundamentos.springboot.fundamentos.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {

    @Id // Id que representa la entidad a nivel de la tabla en la BD
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Se crea un id unico
    // A nivel de base de datos el atributo de llama "id_post"
    @Column(name="id_post", nullable = false, unique = true)
    private Long id;
    @Column(name = "description", length = 255)
    private String description;
    @ManyToOne
    @JsonBackReference // Soluciona error de Unsupported Media Type
    private User user;

    public Post(String description, User user) {
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
