package com.fundamentos.springboot.fundamentos.bean;

public class SquareAreaImplement implements GeometricOperations{
    @Override
    public int calculateArea(int a, int b) {
        return a*b;
    }
}
