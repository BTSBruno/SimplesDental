package br.com.brunots.simplesdental.beckendtest.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cargo;
    private LocalDateTime nascimento;
    private LocalDateTime createdDate;
    private Boolean ativo;
    
}