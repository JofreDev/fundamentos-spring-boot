package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{
    // Acá llamo a otra dependencia

    MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        int num = 1;
        System.out.println(myOperation.sum(1));
        System.out.println("Hola desde la implementación de un bean con dependencia");
    }
}
