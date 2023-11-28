package br.com.brunots.simplesdental.beckendtest.controllers;

import br.com.brunots.simplesdental.beckendtest.dtos.ProfissionalDTO;
import br.com.brunots.simplesdental.beckendtest.services.ProfissionalService;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
@RequiredArgsConstructor
public class ProfissionalController {

    @Autowired
    final private ProfissionalService service;

    @GetMapping
    public ResponseEntity<ProfissionalListResponse> listAll(@RequestParam(value = "q", required = false) String q, @RequestParam(value = "fields", required = false) List<String> fields) {
        List<ProfissionalDTO> result = service.getAllProfissionais(q, fields);
        return ResponseEntity.ok(new ProfissionalListResponse(result));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProfissionalResponse> findById(@PathVariable Long id) {
        try {
            ProfissionalDTO result = service.getProfissionalById(id);
            return ResponseEntity.ok(new ProfissionalResponse(result));
        } catch (NoResultException nre) {
            return ResponseEntity.status(404).body(new ProfissionalResponse(nre.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ProfissionalResponse> create(@RequestBody ProfissionalDTO profissional) {
        try {
            profissional = service.createProfissional(profissional);
            return ResponseEntity.ok(new ProfissionalResponse(profissional));
        } catch (IllegalStateException ise) {
            return ResponseEntity.badRequest().body(new ProfissionalResponse(ise.getMessage()));
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProfissionalResponse> updateProfessional(@PathVariable Long id, @RequestBody ProfissionalDTO profissional) {
        try {
            profissional = service.updateProfissional(id, profissional);
            return ResponseEntity.ok(new ProfissionalResponse(profissional, "Profissional atualizado com sucesso!"));
        } catch (NoResultException e) {
            return ResponseEntity.status(404).body(new ProfissionalResponse(e.getMessage()));
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProfissionalResponse> deleteProfissional(@PathVariable Long id) {
        try {
            service.deleteProfissional(id);
            return ResponseEntity.ok(new ProfissionalResponse("Profissional deletado com sucesso!"));
        } catch (NoResultException e) {
            return ResponseEntity.status(404).body(new ProfissionalResponse(e.getMessage()));
        }
    }

}