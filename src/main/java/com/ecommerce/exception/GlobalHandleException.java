package com.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandleException {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String,String>> handleInvalidRequestArgsException(MethodArgumentNotValidException ex){

            Map<String,String> resp = new HashMap<>();

            ex.getBindingResult().getAllErrors().forEach(error ->{
                String fieldName = ((FieldError)error).getField();
                String message = error.getDefaultMessage();

                resp.put(fieldName , message);
            });

            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<Map<String,String>> handleUserNotFoundException(ResourceNotFoundException ex){

            Map<String,String> resp = new HashMap<>();

            resp.put("ERROR", ex.getMessage());

            return new  ResponseEntity<>(resp , HttpStatus.NOT_FOUND);

        }

}
