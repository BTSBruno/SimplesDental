package br.com.brunots.simplesdental.beckendtest.controllers;

import br.com.brunots.simplesdental.beckendtest.dtos.ContatoDTO;

public class ContatoResponse extends Response<ContatoDTO> {

    public ContatoResponse(ContatoDTO data) {
        super(data);
    }

    public ContatoResponse(ContatoDTO data, String message) {
        super(data);
        super.message = message;
    }

    public ContatoResponse(String message) {
        super(message);
    }

}