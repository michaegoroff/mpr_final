package com.pjatk.MPRSpring1.CustomExceptions;

public class CatAlreadyExistsException extends RuntimeException {
    public CatAlreadyExistsException(){
        super("Cat already exists!");
    }
}
