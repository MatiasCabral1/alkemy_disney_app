package com.app.disney.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.disney.models.Characters;
import com.app.disney.repositories.CharacterRepository;
import com.app.disney.service.CharacterService;
import com.app.disney.service.MovieService;
@Service
public class CharacterServiceImpl implements CharacterService{
	@Autowired
	CharacterRepository characterRepository;
	@Autowired
	MovieService movieService;
	
	@Override
	public List<Characters> listAll() {
		return this.characterRepository.findAll();
	}

	@Override
	public Characters save(Characters character) {
		return this.characterRepository.save(character);
	}

	@Override
	public Characters update(Characters character) {
		character.setEnable(true);
		return this.characterRepository.save(character);
	}

	@Override
	public void delete(Long id) {
		this.characterRepository.deleteById(id);
	}
	@Override
	public List<Characters> getByName(String name) {
		return this.characterRepository.getByName(name);
	}

	@Override
	public List<Characters> getByAge(int age) {
		return this.characterRepository.getByAge(age);
	}

	@Override
	public List<Characters> getByWeight(double weight) {
		return this.characterRepository.getByWeight(weight);
	}

	@Override
	public Optional<Characters> findById(Long characterId) {
		return this.characterRepository.findById(characterId);
	}

	@Override
	public List<Characters> findAllByMoviesIdAndEnable(Long movieId) {
		return this.characterRepository.findAllByMoviesIdAndEnable(movieId, true);
	}

	
}
