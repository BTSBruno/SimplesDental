package br.com.brunots.simplesdental.beckendtest.dtos;

import br.com.brunots.simplesdental.beckendtest.entities.Profissional;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Data
public class ProfissionalDTO {

    private Long id;
    private String nome;
    private String cargo;
    private LocalDateTime nascimento;
    private LocalDateTime createdDate;

    public static ProfissionalDTO fromEntity(Profissional profissional) {
        return fromEntity(profissional, null, null);
    }

    public static ProfissionalDTO fromEntity(Profissional profissional, String q, List<String> fields) {
        if (nonNull(profissional.getAtivo()) && !profissional.getAtivo()) {
            return null;
        }
        if (nonNull(q) && (isNull(profissional.getNome()) || !profissional.getNome().contains(q)) && (isNull(profissional.getCargo()) || !profissional.getCargo().contains(q))) {
            return null;
        }
        boolean temDados = false;
        ProfissionalDTO result = new ProfissionalDTO();
        if (isNull(fields) || (fields.contains("id") && nonNull(profissional.getId()))) {
            temDados = true;
            result.setId(profissional.getId());
        }
        if (isNull(fields) || (fields.contains("nome") && nonNull(profissional.getNome()))) {
            temDados = true;
            result.setNome(profissional.getNome());
        }
        if (isNull(fields) || (fields.contains("cargo") && nonNull(profissional.getCargo()))) {
            temDados = true;
            result.setCargo(profissional.getCargo());
        }
        if (isNull(fields) || (fields.contains("nascimento") && nonNull(profissional.getNascimento()))) {
            temDados = true;
            result.setNascimento(profissional.getNascimento());
        }
        if (isNull(fields) || (fields.contains("createdDate") && nonNull(profissional.getCreatedDate()))) {
            temDados = true;
            result.setCreatedDate(profissional.getCreatedDate());
        }
        return temDados ? result : null;
    }

    public Profissional toEntity() {
        Profissional result = new Profissional();
        result.setId(this.getId());
        result.setNome(this.getNome());
        result.setCargo(this.getCargo());
        result.setNascimento(this.getNascimento());
        result.setCreatedDate(this.getCreatedDate());
        return result;
    }

}
