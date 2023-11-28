package br.com.brunots.simplesdental.beckendtest.services.impl;

import br.com.brunots.simplesdental.beckendtest.dtos.ProfissionalDTO;
import br.com.brunots.simplesdental.beckendtest.entities.Profissional;
import br.com.brunots.simplesdental.beckendtest.repositories.ProfissionalRepository;
import br.com.brunots.simplesdental.beckendtest.services.ProfissionalService;
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
public class ProfissionalServiceImpl implements ProfissionalService {

    @Autowired
    final private ProfissionalRepository repository;

    @Override
    public List<ProfissionalDTO> getAllProfissionais(String q, List<String> fields) {
        List<ProfissionalDTO> result =  repository.findAll().stream().map(p -> ProfissionalDTO.fromEntity(p, q, fields)).collect(Collectors.filtering(Objects::nonNull, Collectors.toList()));
        if (result.isEmpty()) {
            if (isNull(q)) {
                throw new NoResultException("Nenhum profissional cadastrado");
            }
            throw new NoResultException("Nenhum profissional atende os critérios de busca informado.");
        }
        return result;
    }

    @Override
    public ProfissionalDTO getProfissionalById(Long id) {
        Optional<Profissional> optional = repository.findById(id);
        if (optional.isPresent()) {
            return ProfissionalDTO.fromEntity(optional.get());
        } else {
            throw new NoResultException("Profissional com o id " + id + " não encontrado!");
        }
    }

    @Override
    public ProfissionalDTO createProfissional(ProfissionalDTO profissional) {
        if (nonNull(profissional.getId())) {
            throw new IllegalStateException("Utilize o método updateProfissional para atualizar um profissional já salvo!");
        }
        profissional.setCreatedDate(LocalDateTime.now());
        Profissional saved = repository.save(profissional.toEntity());
        return ProfissionalDTO.fromEntity(saved);
    }

    @Override
    public ProfissionalDTO updateProfissional(Long id, ProfissionalDTO profissional) {
        Optional<Profissional> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NoResultException("O profissional com o id " + id + "não foi encontrado.");
        }
        Profissional old = optional.get();
        if (nonNull(profissional.getNome())) {
            old.setNome(profissional.getNome());
        }
        if (nonNull(profissional.getCargo())) {
            old.setCargo(profissional.getCargo());
        }
        if (nonNull(profissional.getNascimento())) {
            old.setNascimento(profissional.getNascimento());
        }
        Profissional saved = repository.save(old);
        return ProfissionalDTO.fromEntity(saved);
    }

    @Override
    public void deleteProfissional(Long id) {
        Optional<Profissional> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NoResultException("O profissional com o id " + id + " não foi encontrado.");
        }
        Profissional old = optional.get();
        old.setAtivo(Boolean.FALSE);
        repository.save(old);
    }
}
