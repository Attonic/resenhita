package com.resenhita.exception.handler;

import com.resenhita.exception.AcessoProibidoException;
import com.resenhita.exception.ConflitoException;
import com.resenhita.exception.NaoAutorizadoException;
import com.resenhita.exception.RecursoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class GlobalExceptionHandler {

    // 409
    @ExceptionHandler(ConflitoException.class)
    public ResponseEntity<ErroResponse> handlerConflito(ConflitoException e, HttpServletRequest request){
            return buildRespon(HttpStatus.CONFLICT, e.getMessage(), e.getMessages(), request);
    }

    // 403
    @ExceptionHandler(AcessoProibidoException.class)
    public ResponseEntity<ErroResponse> handlerAcessoProibido(AcessoProibidoException e,  HttpServletRequest request){
        return buildRespon(HttpStatus.FORBIDDEN, e.getMessage(), e.getMessages(), request);
    }

    // 404
    @ExceptionHandler(RecursoEncontradoException.class)
    public ResponseEntity<ErroResponse> handlerRecursoNaoEncontrado(RecursoEncontradoException e, HttpServletRequest requeste){
        return buildRespon(HttpStatus.FORBIDDEN, e.getMessage(), e.getMessages(), requeste);
    }

    //401
    @ExceptionHandler(NaoAutorizadoException.class)
    public ResponseEntity<ErroResponse> handlerNaoAutorizado(NaoAutorizadoException e,  HttpServletRequest request){
        return buildRespon(HttpStatus.UNAUTHORIZED, e.getMessage(), e.getMessages(), request);
    }

    private ResponseEntity<ErroResponse> buildRespon(
            HttpStatus status,
            String message,
            List<String> messages,
            HttpServletRequest request
    ){
         ErroResponse erroResponse = ErroResponse.builder()
                .timesTamp(LocalDateTime.now())
                .status(status.value())
                .error(message)
                .path(request.getRequestURI())
                .build();


        return  ResponseEntity.status(status).body(erroResponse);

    }
}


