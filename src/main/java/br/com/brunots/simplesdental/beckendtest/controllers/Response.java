package br.com.brunots.simplesdental.beckendtest.controllers;

import lombok.Data;

@Data
public class Response<T>  {

    T data;
    String message;

    public Response(T data) {
        this.data = data;
    }

    public Response(String message) {
        this.message = message;
    }
}
