package com.fundamentos.springboot.fundamentos.bean;

public class PrintAreasImplement implements PrintAreas{

    private final GeometricOperations geometricOperations;

    public PrintAreasImplement(GeometricOperations geometricOperations) {
        this.geometricOperations = geometricOperations;
    }

    @Override
    public void printAreasWithDependency() {
        System.out.println("************************************************++");
        System.out.println("Usted ha llamado al impresor de Areas");
        System.out.println("************************************************++");
        System.out.println("Area :" + geometricOperations.calculateArea(3,4) + "m2");

    }
}
