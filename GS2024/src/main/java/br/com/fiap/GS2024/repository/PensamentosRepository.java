package br.com.fiap.GS2024.repository;

import br.com.fiap.GS2024.model.Pensamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PensamentosRepository extends JpaRepository<Pensamentos, Long> {
}
