package br.com.brunots.simplesdental.beckendtest.repositories;

import br.com.brunots.simplesdental.beckendtest.entities.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
