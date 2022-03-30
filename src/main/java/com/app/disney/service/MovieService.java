package com.app.disney.service;

import java.util.Optional;

import com.app.disney.models.Movie;

public interface MovieService {
	public Iterable<Movie> findAll();
	
	public Optional<Movie> findById(Long id);
	
	public Movie save(Movie movie);
	
	public Movie update(Movie movie,Long id);
	
	public void deleteById(Long id);

	public Optional<Movie> findByTitle(String title);

}
