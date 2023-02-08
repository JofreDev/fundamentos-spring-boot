package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.service.UserService;
import com.fundamentos.springboot.fundamentos.usecases.GetUser;
import com.fundamentos.springboot.fundamentos.usecases.GetUserImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService);
    }
}
