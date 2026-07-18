package com.resenhita.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

//422
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
@Getter
public class LimiteDeReviewException extends RuntimeException{

    private List<String> messages;

    public LimiteDeReviewException(String message){
        super(message);
        this.messages = List.of(message);
    }

    public LimiteDeReviewException(List<String> messages) {
        super(messages.toString());
        this.messages = messages;
    }

}
