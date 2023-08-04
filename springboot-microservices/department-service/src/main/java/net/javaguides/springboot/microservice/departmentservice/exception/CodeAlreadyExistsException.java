package net.javaguides.springboot.microservice.departmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CodeAlreadyExistsException extends RuntimeException{
    private String message;

    public CodeAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
