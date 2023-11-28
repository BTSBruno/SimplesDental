package br.com.brunots.simplesdental.beckendtest.services;

import br.com.brunots.simplesdental.beckendtest.dtos.ContatoDTO;

import java.util.List;

public interface ContatoService {
    List<ContatoDTO> getAllContatos(String q, List<String> fields);
    ContatoDTO getContatoById(Long id);
    ContatoDTO createContato(ContatoDTO contato);
    ContatoDTO updateContato(Long id, ContatoDTO contato);
    void deleteContato(Long id);
}
