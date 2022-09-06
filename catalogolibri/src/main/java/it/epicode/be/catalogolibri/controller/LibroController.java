package it.epicode.be.catalogolibri.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.be.catalogolibri.model.Libro;
import it.epicode.be.catalogolibri.service.LibroService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class LibroController {

	@Autowired
	private LibroService libroService;

	
	@GetMapping(path = "/libro")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<List<Libro>> findAll() {
		List<Libro> findAll = libroService.findAll();
		if (findAll != null) {

			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/libro/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Libro> findById(@PathVariable Long id) {
		Optional<Libro> find = libroService.findById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		}
	}
	@GetMapping(path = "/libro/autore/{cognome}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<List<Libro>> findByAutoriCognome(@PathVariable String cognome) {
		List<Libro> findAutore = libroService.findByAutore(cognome);

		if (findAutore != null) { 
			return new ResponseEntity<>(findAutore, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = "/libro/categoria/{categoria}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<List<Libro>> findByCategoria(@PathVariable String categoria) {
		List<Libro> findCategoria = libroService.findByCategoria(categoria);

		if (findCategoria != null) { 
			return new ResponseEntity<>(findCategoria, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "libro")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Libro> save(@RequestBody Libro libro) {
		Libro save = libroService.save(libro);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	
	@PutMapping(path = "/libro/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Libro> update(@PathVariable Long id, @RequestBody Libro libro) {
		Libro save = libroService.update(id, libro);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "/libro/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		if (libroService.findById(id).isEmpty()) {
			return new ResponseEntity<>("Il libro non esiste", HttpStatus.NOT_FOUND);
		}

		libroService.delete(id);
		return new ResponseEntity<>("Libro cancellato", HttpStatus.OK);
	}

}
