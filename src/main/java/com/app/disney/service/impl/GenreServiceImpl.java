package com.app.disney.service.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.disney.models.Genre;
import com.app.disney.repositories.GenreRepository;
import com.app.disney.service.GenreService;
@Service
public class GenreServiceImpl implements GenreService{
	@Autowired
	private GenreRepository genreRepo;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Genre> findAll() {
		return genreRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Genre> findById(Long id) {
		return genreRepo.findById(id);
	}

	@Override
	public Genre save(Genre genre) {
		return genreRepo.save(genre);
	}

	@Override
	public Genre update(Genre genreRequest, Long id) {
		Optional<Genre> genre = genreRepo.findById(id);
		if (genre.isPresent()) {
			genre.get().setEnable(genreRequest.isEnable());
			genre.get().setImage(genreRequest.getImage());
			genre.get().setName(genreRequest.getName());
			return genreRepo.save(genre.get());
		} else
			return null;
	}

	@Override
	public void deleteById(Long id) {
		genreRepo.deleteById(id);
		
	}
	
	public boolean existsByName(String name) {
		return genreRepo.existsByName(name);
	}

	public Optional<Genre> findByName(String name) {
		return this.genreRepo.findByName(name);
	}

	public List<Genre> findAllByMoviesIdAndEnable(Long movieId) {
		return this.genreRepo.findAllByMoviesIdAndEnable(movieId, true);
	}

}
