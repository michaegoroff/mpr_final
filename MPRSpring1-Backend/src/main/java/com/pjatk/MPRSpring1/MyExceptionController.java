package com.pjatk.MPRSpring1;

import com.pjatk.MPRSpring1.CustomExceptions.CatAlreadyExistsException;
import com.pjatk.MPRSpring1.CustomExceptions.CatNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CatNotFoundException.class)
    protected ResponseEntity<Object> catNotFound(RuntimeException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(CatAlreadyExistsException.class)
    protected ResponseEntity<Object> catAlreadyExists(RuntimeException ex,WebRequest request){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
