package br.com.brunots.simplesdental.beckendtest.controllers;

import br.com.brunots.simplesdental.beckendtest.dtos.ContatoDTO;
import br.com.brunots.simplesdental.beckendtest.services.ContatoService;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
@RequiredArgsConstructor
public class ContatoController {

    @Autowired
    final private ContatoService service;

    @GetMapping
    public ResponseEntity<ContatoListResponse> listAll(@RequestParam(value = "q", required = false) String q, @RequestParam(value = "fields", required = false) List<String> fields) {
        List<ContatoDTO> result = service.getAllContatos(q, fields);
        return ResponseEntity.ok(new ContatoListResponse(result));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ContatoResponse> findById(@PathVariable Long id) {
        try {
            ContatoDTO result = service.getContatoById(id);
            return ResponseEntity.ok(new ContatoResponse(result));
        } catch (NoResultException nre) {
            return ResponseEntity.status(404).body(new ContatoResponse(nre.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ContatoResponse> create(@RequestBody ContatoDTO contato) {
        try {
            contato = service.createContato(contato);
            return ResponseEntity.ok(new ContatoResponse(contato));
        } catch (IllegalStateException ise) {
            return ResponseEntity.badRequest().body(new ContatoResponse(ise.getMessage()));
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ContatoResponse> updateProfessional(@PathVariable Long id, @RequestBody ContatoDTO contato) {
        try {
            contato = service.updateContato(id, contato);
            return ResponseEntity.ok(new ContatoResponse(contato, "Contato atualizado com sucesso!"));
        } catch (NoResultException e) {
            return ResponseEntity.status(404).body(new ContatoResponse(e.getMessage()));
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ContatoResponse> deleteProfissional(@PathVariable Long id) {
        try {
            service.deleteContato(id);
            return ResponseEntity.ok(new ContatoResponse("Contato deletado com sucesso!"));
        } catch (NoResultException e) {
            return ResponseEntity.status(404).body(new ContatoResponse(e.getMessage()));
        }
    }

}
