package com.app.disney.service;

import java.util.Optional;

import com.app.disney.models.Genre;

public interface GenreService {
	public Iterable<Genre> findAll();
	
	public Optional<Genre> findById(Long id);
	
	public Genre save(Genre genre);
	
	public Genre update(Genre genre,Long id);
	
	public void deleteById(Long id);

	public Optional<Genre> findByName(String name);
}
