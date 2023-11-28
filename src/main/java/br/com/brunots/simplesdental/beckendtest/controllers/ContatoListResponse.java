package br.com.brunots.simplesdental.beckendtest.controllers;

import br.com.brunots.simplesdental.beckendtest.dtos.ContatoDTO;

import java.util.List;

public class ContatoListResponse extends Response<List<ContatoDTO>> {

    public ContatoListResponse(List<ContatoDTO> data) {
        super(data);
    }

    public ContatoListResponse(String message) {
        super(message);
    }

}
