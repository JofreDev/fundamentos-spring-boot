package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    @Bean
    public MyBean beanOperations(){

        //return MyBeanImplement() -> Implementanto el bean versión 1
        return new MyBean2Implement(); // Implementando el bean versión 2
    }


    @Bean
    public MyOperation beanOperationOperations(){

        return new MyOperationImplement() ; // Implementando el bean versión 1
    }

    @Bean
    public MyBeanWithDependency beanOperationOperationWithDependency(MyOperation myOperation){
        return new MyBeanWithDependencyImplement(myOperation) ; // Implementando el bean versión 1
    }

    @Bean
    public GeometricOperations beanGeometricOperations(){

        return new SquareAreaImplement() ; // Implementando el bean versión 1
    }

    @Bean
    public PrintAreas PrintAreaWithDependency(GeometricOperations geometricOperations){
        return new PrintAreasImplement(geometricOperations) ; // Implementando el bean versión 1
    }
}
