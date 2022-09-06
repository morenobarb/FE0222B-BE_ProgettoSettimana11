package it.epicode.be.catalogolibri.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.catalogolibri.model.Autore;

public interface AutoreRepository extends JpaRepository<Autore, Long> {
	Page<Autore> findByNome(String nome, Pageable pageable);

	Optional<Autore> findByNome(String nome);

}
