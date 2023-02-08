package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/// JpaRepository<Entidad, Tipo_dato_id>

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
