package br.com.brunots.simplesdental.beckendtest.repositories;

import br.com.brunots.simplesdental.beckendtest.entities.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}
