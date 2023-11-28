package br.com.brunots.simplesdental.beckendtest.dtos;

import br.com.brunots.simplesdental.beckendtest.entities.Contato;
import br.com.brunots.simplesdental.beckendtest.entities.Profissional;
import br.com.brunots.simplesdental.beckendtest.repositories.ProfissionalRepository;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Data
public class ContatoDTO {

    private Long id;
    private String nome;
    private String contato;
    private LocalDateTime createdDate;
    private Long profissional;

    public static ContatoDTO fromEntity(Contato contato) {
        return fromEntity(contato, null, null);
    }

    public static ContatoDTO fromEntity(Contato contato, String q, List<String> fields) {
        if (nonNull(q)
                && (isNull(contato.getNome()) || !contato.getNome().contains(q))
                && (isNull(contato.getContato()) || !contato.getContato().contains(q))
                && (isNull(contato.getProfissional()) || isNull(contato.getProfissional().getNome()) || !contato.getProfissional().getNome().contains(q))
                && (isNull(contato.getProfissional()) || isNull(contato.getProfissional().getCargo()) || !contato.getProfissional().getCargo().contains(q))) {
            return null;
        }
        boolean temDados = false;
        ContatoDTO result = new ContatoDTO();
        if (isNull(fields) || (fields.contains("id") && nonNull(contato.getId()))) {
            temDados = true;
            result.setId(contato.getId());
        }
        if (isNull(fields) || (fields.contains("nome") && nonNull(contato.getNome()))) {
            temDados = true;
            result.setNome(contato.getNome());
        }
        if (isNull(fields) || (fields.contains("contato") && nonNull(contato.getContato()))) {
            temDados = true;
            result.setContato(contato.getContato());
        }
        if (isNull(fields) || (fields.contains("profissional") && nonNull(contato.getProfissional()))) {
            temDados = true;
            result.setProfissional(contato.getProfissional().getId());
        }
        if (isNull(fields) || (fields.contains("createdDate") && nonNull(contato.getCreatedDate()))) {
            temDados = true;
            result.setCreatedDate(contato.getCreatedDate());
        }
        return temDados ? result : null;
    }

    public Contato toEntity() {
        Contato result = new Contato();
        result.setId(this.getId());
        result.setNome(this.getNome());
        result.setContato(this.getContato());
        result.setCreatedDate(this.getCreatedDate());
        return result;
    }

}
