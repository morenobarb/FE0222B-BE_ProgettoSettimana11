package it.epicode.be.catalogolibri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.catalogolibri.exception.CatalogoException;
import it.epicode.be.catalogolibri.model.Libro;
import it.epicode.be.catalogolibri.repository.LibroRepository;

@Service
public class LibroService {

	@Autowired
	LibroRepository libroRepository;

	public Optional<Libro> findById(Long id) {
		return libroRepository.findById(id);
	}

	public List<Libro> findByTitolo(String titolo) {
		return libroRepository.findByTitolo(titolo);
	}

	public List<Libro> findAll() {
		return libroRepository.findAll();
	}

	public List<Libro> findByAutore(String autore) {
		return libroRepository.findByAutoriCognome(autore);
	}

	public List<Libro> findByCategoria(String categoria) {
		return libroRepository.findByCategorieNome(categoria);
	}

	public Libro save(Libro libro) {
		return libroRepository.save(libro);
	}

	public Libro update(Long id, Libro libro) {
		Optional<Libro> libroResult = libroRepository.findById(id);
		if (libroResult.isPresent()) {
			Libro libroUpdate = libroResult.get();
			libroUpdate.setTitolo(libro.getTitolo());
			libroUpdate.setAnnoPubblicazione(libro.getAnnoPubblicazione());
			libroUpdate.setPrezzo(libro.getPrezzo());
			libroUpdate.setAutori(libro.getAutori());
			libroUpdate.setCategorie(libro.getCategorie());
			libroRepository.save(libroUpdate);
			return libroUpdate;
		} else {
			throw new CatalogoException("Libro non aggiornato");
		}

	}

	public void delete(Long id) {
		libroRepository.deleteById(id);
	}
}
