package com.fundamentos.springboot.fundamentos.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user", nullable = false, unique = true)
    private Long id;
    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String email;

    // No tiene anotación ya que es de tipo DATE !!!
    private LocalDate birthDate;


    // Tipo de relación "Uno a muchos con Post" con la tabla Post
    // Ej un usuario puede tener muchos Post
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // Arreglo de Post
    // Notación para acceder al servicio a nivel de servicio rest sin ningun error 'stackOver'
    @JsonManagedReference
    private List<Post> post = new ArrayList<>();


    public User(String name, String email, LocalDate birthDate, List<Post> post) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", post=" + post +
                '}';
    }
}
