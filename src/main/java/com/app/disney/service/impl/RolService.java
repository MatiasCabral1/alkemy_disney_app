package com.app.disney.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.disney.models.Rol;
import com.app.disney.repositories.RolRepository;
import com.app.disney.security.enums.RolName;

@Service
@Transactional
public class RolService {
	@Autowired
	RolRepository rolRepository;

	public ArrayList<Rol> getAll() {
		return (ArrayList<Rol>) rolRepository.findAll();
	}

	public Optional<Rol> getByRolName(RolName rolNombre) {
		return rolRepository.findByRolNombre(rolNombre);
	}

	public void save(Rol rol) {
		rolRepository.save(rol);
	}
}
