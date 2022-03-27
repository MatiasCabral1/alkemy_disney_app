package com.app.disney.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.disney.models.Genre;
import com.app.disney.models.Movie;
import com.app.disney.repositories.MovieRepository;
import com.app.disney.service.MovieService;
@Service
public class MovieServiceImpl implements MovieService{
	@Autowired
	private MovieRepository movieRepo;

	@Override
	public Iterable<Movie> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Movie> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie save(Movie movie) {
		return this.movieRepo.save(movie);
	}

	@Override
	public Movie update(Movie movie, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	

}
