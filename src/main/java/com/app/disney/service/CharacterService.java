package com.app.disney.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.app.disney.models.Characters;
import com.app.disney.security.dto.CharacterReturnDTO;

public interface CharacterService {
	ArrayList<CharacterReturnDTO> listAll();
	Characters save(Characters character);
	Characters update(Characters character);
	void delete(Characters character);
	List<Characters> getByName(String name);
	List<Characters> getByIdMovie(Long id);
	List<Characters> getByAge(int age);
	List<Characters> getByWeight(double weight);
}
