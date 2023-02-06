package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.*;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// Se configuran los beans
@Configuration
public class MyConfigurationBean {
    // Metodo del tipo de la interfaz
    // Devuelve la implementación
    @Bean
    public MyBean beanOperations(){

        //return MyBeanImplement() -> Implementanto el bean versión 1
        return new MyBean2Implement(); // Implementando el bean versión 2
    }

    // Tambien se puede generar error por el uso de varios beans acá
    // En teoria esto no deberia pasar
    // No deria pasar que se creen dos beans de una interfaz no tiene sentido.
    /*
    @Bean
    public MyBean beanOperations2(){

        return new MyBeanImplement();//-> Implementanto el bean versión 1
        //return new MyBean2Implement(); // Implementando el bean versión 2
    }
    */


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

    // Configuración de datasource a nivel de clases !


}
