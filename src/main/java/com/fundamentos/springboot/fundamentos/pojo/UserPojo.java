package com.fundamentos.springboot.fundamentos.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding // -> Importante para la inyección de dependencia.
// Se debe configurar la clase como dependencia.
@ConfigurationProperties(prefix = "user") // -> Representa el pefijo del properties
public class UserPojo {

    private String email;
    private String password;
    private int age;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserPojo(String email, String password, int age) {
        this.email = email;
        this.password = password;
        this.age = age;
    }
}
