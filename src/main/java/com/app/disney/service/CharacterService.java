package com.app.disney.service;

import java.util.List;
import java.util.Optional;
import com.app.disney.models.Characters;

public interface CharacterService {
	List<Characters> listAll();
	Characters save(Characters character);
	Characters update(Characters character);
	void delete(Long id);
	List<Characters> getByName(String name);
	List<Characters> getByAge(int age);
	List<Characters> getByWeight(double weight);
	List<Characters> findAllByIdMovie(Long id);
	Optional<Characters> findById(Long characterId);
}
