package com.app.disney.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.app.disney.models.Characters;
import com.app.disney.security.dto.CharacterDTO;
import com.app.disney.security.dto.CharacterReturnDTO;

public interface CharacterService {
	ArrayList<CharacterReturnDTO> listAll();
	Characters save(Characters character);
	Characters update(Characters character);
	void delete(Long id);
	List<Characters> getByName(String name);
	List<Characters> getByAge(int age);
	List<Characters> getByWeight(double weight);
	List<Characters> findAllByIdMovie(Long id);
	Optional<Characters> findById(Long characterId);
}
