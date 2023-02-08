package com.fundamentos.springboot.fundamentos.controller;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.usecases.CreateUser;
import com.fundamentos.springboot.fundamentos.usecases.DeleteUser;
import com.fundamentos.springboot.fundamentos.usecases.GetUser;
import com.fundamentos.springboot.fundamentos.usecases.UpdateUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Hereda de la notación controller. Todos los metodos acá se formatean a JSON
// Definir ruta
@RequestMapping("/api/users")
public class UserRestController {

    // Create, get, delete, update
    // Se llama a los casos de uso.

    private GetUser getUser;
    private CreateUser createUser;

    private DeleteUser deleteUser;
    private UpdateUser updateUser;

    public UserRestController(GetUser getUser,
                              CreateUser createUser,
                              DeleteUser deleteUser,
                              UpdateUser updateUser) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
    }
@GetMapping("/")
    List<User> get(){
        return getUser.getAll();
    }

@PostMapping("/")
ResponseEntity<User> newUser(@RequestBody User newUser){
    // Devuelve un 201 (Se creo bien)
    return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
}

@DeleteMapping("/{id}")
ResponseEntity deleteUser(@PathVariable Long id){
    deleteUser.remove(id);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
}

@PutMapping("/{id}")
ResponseEntity<User> replaceUser(@RequestBody User newUser, @PathVariable Long id){
    return new ResponseEntity<>(updateUser.update(newUser,id), HttpStatus.OK);
}

}
