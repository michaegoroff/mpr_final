package com.pjatk.MPRSpring1.CustomExceptions;

public class CatNotFoundException extends RuntimeException{
    public  CatNotFoundException(){
        super("Cat not found!");
    }
}
