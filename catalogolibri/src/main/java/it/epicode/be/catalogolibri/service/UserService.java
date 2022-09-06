package it.epicode.be.catalogolibri.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.catalogolibri.exception.CatalogoException;
import it.epicode.be.catalogolibri.model.security.User;
import it.epicode.be.catalogolibri.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public Optional<User> findByUserName(String username) {
		return userRepository.findByUserName(username);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User update(Long id, User user) {
		Optional<User> utenteResult = userRepository.findById(id);

		if (utenteResult.isPresent()) {
			User utenteUpdate = utenteResult.get();
			
			utenteUpdate.setUserName(user.getUserName());
			utenteUpdate.setEmail(user.getEmail());
			utenteUpdate.setPassword(user.getPassword());
			utenteUpdate.setActive(user.isActive());
			userRepository.save(utenteUpdate);
			return utenteUpdate;
		} else {
			throw new CatalogoException("Utente non aggiornato");
		}

	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}

}

