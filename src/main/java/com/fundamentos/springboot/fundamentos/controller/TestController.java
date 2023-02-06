package com.fundamentos.springboot.fundamentos.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping // Acepta todas las solicitudes Http
    @ResponseBody /// Responder un body

    // Por defecto está sobre Tomcat !!

    public ResponseEntity<String> function(){
        return new ResponseEntity<>("Hello from controller, un cambio, otro cambiooo", HttpStatus.OK);
    }


}
