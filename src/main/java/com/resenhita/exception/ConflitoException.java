package com.resenhita.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * HTTP Status 409 Conflict
 */
@ResponseStatus(HttpStatus.CONFLICT)
@Getter
public class ConflitoException extends RuntimeException{

    private final List<String> messages;

    public ConflitoException(String message) {
        super(message);
        this.messages = List.of(message);
    }

    public ConflitoException(List<String> messages){
        super(messages.toString());
        this.messages = messages;
    }

}
