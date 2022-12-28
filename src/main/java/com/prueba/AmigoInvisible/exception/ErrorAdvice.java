package com.prueba.AmigoInvisible.exception;

import com.prueba.AmigoInvisible.entity.ApiException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorAdvice {
    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiException handleNotFoundException(PlayerNotFoundException e, HttpServletRequest request){
        return new ApiException(404, e.getMessage(), request.getServletPath());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiException handleExpiredJwtException(ExpiredJwtException e, HttpServletRequest request){
        return new ApiException(111, e.getMessage(), request.getServletPath());
    }
    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiException handleSignatureException(SignatureException e, HttpServletRequest request){
        return new ApiException(111, e.getMessage(), request.getServletPath());
    }
}
