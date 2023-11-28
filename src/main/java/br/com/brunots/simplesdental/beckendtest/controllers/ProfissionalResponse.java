package br.com.brunots.simplesdental.beckendtest.controllers;

import br.com.brunots.simplesdental.beckendtest.dtos.ProfissionalDTO;
import br.com.brunots.simplesdental.beckendtest.entities.Profissional;

public class ProfissionalResponse extends Response<ProfissionalDTO> {

    public ProfissionalResponse(ProfissionalDTO data) {
        super(data);
    }

    public ProfissionalResponse(ProfissionalDTO data, String message) {
        super(data);
        super.message = message;
    }

    public ProfissionalResponse(String message) {
        super(message);
    }

}