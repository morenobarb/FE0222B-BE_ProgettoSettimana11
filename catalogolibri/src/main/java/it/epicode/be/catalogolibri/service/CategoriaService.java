package it.epicode.be.catalogolibri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.catalogolibri.exception.CatalogoException;
import it.epicode.be.catalogolibri.model.Categoria;
import it.epicode.be.catalogolibri.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Optional<Categoria> findById(Long id) {
		return categoriaRepository.findById(id);
	}

	public Optional<Categoria> findByNome(String nome) {
		return categoriaRepository.findByNome(nome);
	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Long id, Categoria categoria) {
		Optional<Categoria> categoriaResult = categoriaRepository.findById(id);
		if (categoriaResult.isPresent()) {
			Categoria categoriaUpdate = categoriaResult.get();
			categoriaUpdate.setNome(categoria.getNome());
			categoriaRepository.save(categoriaUpdate);
			return categoriaUpdate;
		} else {
			throw new CatalogoException("Categoria non trovata");
		}
	}

	public void delete(Long id) {
		categoriaRepository.deleteById(id);
	}
}
