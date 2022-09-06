package it.epicode.be.catalogolibri.model;

import java.util.ArrayList;
import javax.persistence.JoinColumn;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
	private Long id;
	private String titolo;
	private int annoPubblicazione;
	private double prezzo;

	@ManyToMany
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JoinTable(name = "libro_autore", joinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "autore_id", referencedColumnName = "id"))
	private List<Autore> autori = new ArrayList<>();

	@ManyToMany
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JoinTable(name = "libro_categoria", joinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "id"))
	private List<Categoria> categorie = new ArrayList<>();

}
