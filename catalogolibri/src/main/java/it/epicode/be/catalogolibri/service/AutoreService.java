package it.epicode.be.catalogolibri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.catalogolibri.exception.CatalogoException;
import it.epicode.be.catalogolibri.model.Autore;
import it.epicode.be.catalogolibri.repository.AutoreRepository;

@Service
public class AutoreService {
	
	@Autowired
	private AutoreRepository autoreRepository;
	
	
	public Optional<Autore> findById(Long id){
		return autoreRepository.findById(id);
	}

	public Optional<Autore> findByNome(String nome){
		return autoreRepository.findByNome(nome);
	}

	public List<Autore> findAll(){
		return autoreRepository.findAll();
	}
	
	public Autore save(Autore autore) {
		return autoreRepository.save(autore);
	}
	
	public Autore update(Long id, Autore autore) {
		Optional<Autore> autoreResult = autoreRepository.findById(id);
		if (autoreResult.isPresent()) {
			Autore autoreUpdate = autoreResult.get();
			autoreUpdate.setNome(autore.getNome());
			autoreUpdate.setCognome(autore.getCognome());
			autoreRepository.save(autoreUpdate);
			return autoreUpdate;
			
		} else {
			throw new CatalogoException("Autore non aggiornato");
		}

	}
	public void delete(Long id) {
		autoreRepository.deleteById(id);
	}
	
}
