package com.app.disney.service;

import java.util.ArrayList;
import java.util.List;

import com.app.disney.models.Characters;
import com.app.disney.security.dto.CharacterReturnDTO;

public interface CharacterService {
	ArrayList<CharacterReturnDTO> listAll();
	Characters save(Characters character);
	Characters update(Characters character);
	void delete(Characters character);
}
