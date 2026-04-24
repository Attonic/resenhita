package com.resenhita.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * HTTP Status 403
 */
@Getter
@ResponseStatus(HttpStatus.FORBIDDEN)
public class AcessoProibidoException extends RuntimeException{

    private final List<String> messages;

    public AcessoProibidoException(String message){
        super(message);
        this.messages = List.of(message);
    }

    public AcessoProibidoException(List<String> messages){
        super(messages.toString());
        this.messages = messages;
    }
}
