package com.app.disney.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.disney.models.Characters;
import com.app.disney.repositories.CharacterRepository;
import com.app.disney.security.dto.CharacterReturnDTO;
import com.app.disney.service.CharacterService;
@Service
public class CharacterServiceImpl implements CharacterService{
	@Autowired
	CharacterRepository characterRepository;
	@Override
	public ArrayList<CharacterReturnDTO> listAll() {
		ArrayList<CharacterReturnDTO> listReturn = new ArrayList<CharacterReturnDTO>();
		try {
			List<Characters> list = this.characterRepository.findAll();
			//creo una lista con elementos de tipo characterReturnDTO-> {image, name}; valor de retorno solicitado
			for(Characters ch: list) {
				listReturn.add(new CharacterReturnDTO(ch.getImage(),ch.getName()));
			}
			return listReturn;
		} catch (Exception e) {
			//retorno la lista vacia.
			return listReturn;
		}
		
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
	public void delete(Characters character) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Characters> getByName(String name) {
		return this.characterRepository.getByName(name);
	}

	@Override
	public List<Characters> getByIdMovie(Long id) {
		return this.characterRepository.getByIdMovie(id);
	}

	@Override
	public List<Characters> getByAge(int age) {
		return this.characterRepository.getByAge(age);
	}

	@Override
	public List<Characters> getByWeight(double weight) {
		return this.characterRepository.getByWeight(weight);
	}
	
}
