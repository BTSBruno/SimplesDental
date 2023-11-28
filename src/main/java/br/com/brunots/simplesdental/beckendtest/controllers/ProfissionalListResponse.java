package br.com.brunots.simplesdental.beckendtest.controllers;

import br.com.brunots.simplesdental.beckendtest.dtos.ProfissionalDTO;

import java.util.List;

public class ProfissionalListResponse extends Response<List<ProfissionalDTO>> {

    public ProfissionalListResponse(List<ProfissionalDTO> data) {
        super(data);
    }

    public ProfissionalListResponse(String message) {
        super(message);
    }

}
