package com.prueba.AmigoInvisible.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiException {
    private int status;
    private String message;
    private long timeStamp;
    private String path;
    private Map<String, String> validationErrors;

    public ApiException(){}

    public ApiException(int status, String message, String path){
        this.status = status;
        this.message = message;
        this.path = path;
        this.timeStamp = new Date().getTime();
    }
}
