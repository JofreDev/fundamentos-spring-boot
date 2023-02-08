package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.dto.UserDTO;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
/// JpaRepository<Entidad, Tipo_dato_id>

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Acá se está usando JPQL
    @Query("Select u from User u where u.email =?1") // Usando JPQL
    Optional<User> findUserByEmail(String email);

    // Acá se está usando JPQL
    @Query("select u from User u where u.name like ?1%") // Buscar y ordenar a partir de un nombre.
    List<User> findAndSort(String name, Sort sort);

    // Usando Query Methods !!!!

    List<User> findByName(String name);
    List<User> findByEmailAndName(String email,String name);


    //// #### Se usa la sentencia like

    List<User> findByNameLike(String name);

    //// ### Se usa la sentencia OR

    List<User> findByNameOrEmail(String name, String email);

    //// ### Por intervalo de fecha

    List<User> findByBirthDateIsBetween(LocalDate begin, LocalDate end);

    //// ### BUsqueda por nombre 'like' y ordenado de forma descendente

    List<User> findByNameLikeOrderByIdDesc(String name);

    /// Contain es un similar

    List<User> findByNameContainingOrderByIdDesc(String name);

    // Uso de parameters con JPQL y DTO

        /*El concepto de Java Optional hace referencia a una variable que puede tener
            un valor asignado o que puede contener un valor null*/

    @Query("SELECT new com.fundamentos.springboot.fundamentos.dto.UserDTO(u.id , u.name, u.birthDate)" +
            "FROM User u " +
            "WHERE u.birthDate=:parametroFecha " +
            "and u.email=:parametroEmail")
    Optional<UserDTO> getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate date,@Param("parametroEmail") String email);




}
