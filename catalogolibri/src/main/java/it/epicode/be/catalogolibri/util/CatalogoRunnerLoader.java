package it.epicode.be.catalogolibri.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.be.catalogolibri.model.Autore;
import it.epicode.be.catalogolibri.model.Categoria;
import it.epicode.be.catalogolibri.model.Libro;
import it.epicode.be.catalogolibri.repository.AutoreRepository;
import it.epicode.be.catalogolibri.repository.CategoriaRepository;
import it.epicode.be.catalogolibri.repository.LibroRepository;

@Component
public class CatalogoRunnerLoader implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepo;

	@Autowired
	private AutoreRepository autoreRepo;

	@Autowired
	private LibroRepository libroRepo;

	@Override
	public void run(String... args) throws Exception {

		List<Categoria> categorie = initCategorie();
		Categoria drammatico = categorie.get(0);
		Categoria fantasy = categorie.get(1);
		categoriaRepo.save(drammatico);
		categoriaRepo.save(fantasy);

		List<Categoria> listaCategoria1 = new ArrayList<>();
		List<Categoria> listaCategoria2 = new ArrayList<>();
		listaCategoria1.add(drammatico);
		listaCategoria2.add(fantasy);

		List<Autore> autori = initAutori();
		Autore mario = autori.get(0);
		Autore gigi = autori.get(1);
		autoreRepo.save(mario);
		autoreRepo.save(gigi);

		List<Autore> listaAutori1 = new ArrayList<>();
		List<Autore> listaAutori2 = new ArrayList<>();
		listaAutori1.add(mario);
		listaAutori2.add(gigi);

		List<Libro> libri = initLibri();

		Libro listaLibri1 = libri.get(0);
		Libro listaLibri2 = libri.get(1);
		listaLibri1.setAutori(listaAutori1);
		listaLibri1.setCategorie(listaCategoria1);
		listaLibri2.setAutori(listaAutori2);
		listaLibri2.setCategorie(listaCategoria2);
		libroRepo.save(listaLibri1);
		libroRepo.save(listaLibri2);

	}

	private List<Libro> initLibri() {
		List<Libro> listaLibri = new ArrayList<>();

		Libro libro1 = new Libro();
		Libro libro2 = new Libro();
		libro1.setTitolo("Triste ma vero");
		libro1.setAnnoPubblicazione(1985);
		libro1.setPrezzo(12.50);
		libro2.setTitolo("Eragon");
		libro2.setAnnoPubblicazione(1990);
		libro2.setPrezzo(17.90);

		listaLibri.add(libro1);
		listaLibri.add(libro2);

		return listaLibri;
	}

	private List<Categoria> initCategorie() {
		List<Categoria> categorie = new ArrayList<>();
		Categoria drammatico = new Categoria();
		Categoria fantasy = new Categoria();
		drammatico.setNome("Drammatico");
		fantasy.setNome("Fantasy");

		categorie.add(drammatico);
		categorie.add(fantasy);

		return categorie;
	}

	private List<Autore> initAutori() {
		List<Autore> listaAutori = new ArrayList<>();

		Autore autore1 = new Autore();
		Autore autore2 = new Autore();
		autore1.setNome("Mario");
		autore1.setCognome("Rossi");
		autore2.setNome("Gigi");
		autore2.setCognome("Prugna");

		listaAutori.add(autore1);
		listaAutori.add(autore2);

		return listaAutori;

	}

}