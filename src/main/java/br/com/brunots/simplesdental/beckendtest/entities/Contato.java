package br.com.brunots.simplesdental.beckendtest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String contato;
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

}