package br.com.brunots.simplesdental.beckendtest.services.impl;

import br.com.brunots.simplesdental.beckendtest.dtos.ContatoDTO;
import br.com.brunots.simplesdental.beckendtest.entities.Contato;
import br.com.brunots.simplesdental.beckendtest.entities.Profissional;
import br.com.brunots.simplesdental.beckendtest.repositories.ContatoRepository;
import br.com.brunots.simplesdental.beckendtest.repositories.ProfissionalRepository;
import br.com.brunots.simplesdental.beckendtest.services.ContatoService;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class ContatoServiceImpl implements ContatoService {

    @Autowired
    final private ContatoRepository repository;
    @Autowired
    final private ProfissionalRepository profissionalRepository;

    @Override
    public List<ContatoDTO> getAllContatos(String q, List<String> fields) {
        List<ContatoDTO> result =  repository.findAll().stream().map(p -> ContatoDTO.fromEntity(p, q, fields)).collect(Collectors.filtering(Objects::nonNull, Collectors.toList()));
        if (result.isEmpty()) {
            if (isNull(q)) {
                throw new NoResultException("Nenhum contato cadastrado");
            }
            throw new NoResultException("Nenhum contato atende os critérios de busca informado.");
        }
        return result;
    }

    @Override
    public ContatoDTO getContatoById(Long id) {
        Optional<Contato> optional = repository.findById(id);
        if (optional.isPresent()) {
            return ContatoDTO.fromEntity(optional.get());
        } else {
            throw new NoResultException("Contato com o id " + id + " não encontrado!");
        }
    }

    @Override
    public ContatoDTO createContato(ContatoDTO contato) {
        if (nonNull(contato.getId())) {
            throw new IllegalStateException("Utilize o método updateContato para atualizar um profissional já salvo!");
        }
        contato.setCreatedDate(LocalDateTime.now());
        Contato entity = contato.toEntity();
        entity.setProfissional(getProfissional(contato.getProfissional()));
        Contato saved = repository.save(entity);
        return ContatoDTO.fromEntity(saved);
    }

    private Profissional getProfissional(Long id) {
        Optional<Profissional> optional = profissionalRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NoResultException("Profissional com o id " + id + " não encontrado!");
        }
        return optional.get();
    }

    @Override
    public ContatoDTO updateContato(Long id, ContatoDTO contato) {
        Optional<Contato> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NoResultException("O profissional com o id " + id + "não foi encontrado.");
        }
        Contato old = optional.get();
        if (nonNull(contato.getNome())) {
            old.setNome(contato.getNome());
        }
        if (nonNull(contato.getContato())) {
            old.setContato(contato.getContato());
        }
        if (nonNull(contato.getProfissional()) && !contato.getProfissional().equals(old.getProfissional().getId())) {
            old.setProfissional(getProfissional(contato.getProfissional()));
        }
        Contato saved = repository.save(old);
        return ContatoDTO.fromEntity(saved);
    }

    @Override
    public void deleteContato(Long id) {
        Optional<Contato> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NoResultException("O contato com o id " + id + " não foi encontrado.");
        }
        Contato old = optional.get();
        repository.delete(old);
    }

}
