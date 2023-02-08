package com.fundamentos.springboot.fundamentos.bean;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{
    // Acá llamo a otra dependencia

    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class); // -> Manejo de logs
    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        // Nivel de loggeo bastante genral que nos indica información de por
        // que objetos está pasando la info
        LOGGER.info("Hemos ingresado al método printWithDependency");
        int num = 1;
        // Nivel debug apagado en producción. Solo info
        LOGGER.debug("El número enviado como param a la dependencia operación es : "+num);
        System.out.println(myOperation.sum(1));
        System.out.println("Hola desde la implementación de un bean con dependencia");
    }
}
