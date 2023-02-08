package com.fundamentos.springboot.fundamentos.usecases;

import com.fundamentos.springboot.fundamentos.entity.User;

import java.util.List;

public interface GetUser {
    List<User> getAll();
}
