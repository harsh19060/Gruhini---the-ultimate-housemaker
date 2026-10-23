package com.household.app.controller;
import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestControllerAdvice public class ApiErrors {
 @ExceptionHandler(MethodArgumentNotValidException.class) @ResponseStatus(HttpStatus.BAD_REQUEST) public Map<String,Object> validation(MethodArgumentNotValidException e){return Map.of("error","Validation failed","details",e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).toList());}
 @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class) @ResponseStatus(HttpStatus.CONFLICT) public Map<String,String> duplicate(Exception e){return Map.of("error","Duplicate value or related records prevent this operation");}
}
