package br.com.brunots.simplesdental.beckendtest.services;

import br.com.brunots.simplesdental.beckendtest.dtos.ProfissionalDTO;
import br.com.brunots.simplesdental.beckendtest.entities.Profissional;

import java.util.List;
import java.util.Optional;

public interface ProfissionalService {
    List<ProfissionalDTO> getAllProfissionais(String q, List<String> fields);
    ProfissionalDTO getProfissionalById(Long id);
    ProfissionalDTO createProfissional(ProfissionalDTO profissional);
    ProfissionalDTO updateProfissional(Long id, ProfissionalDTO profissional);
    void deleteProfissional(Long id);
}
