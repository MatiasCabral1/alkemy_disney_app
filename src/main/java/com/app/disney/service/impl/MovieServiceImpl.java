package com.app.disney.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.disney.models.Characters;
import com.app.disney.models.Genre;
import com.app.disney.models.Movie;
import com.app.disney.repositories.CharacterRepository;
import com.app.disney.repositories.MovieRepository;
import com.app.disney.security.dto.movie.MovieDTO;
import com.app.disney.service.GenreService;
import com.app.disney.service.MovieService;
@Service
public class MovieServiceImpl implements MovieService{
	@Autowired
	private MovieRepository movieRepo;
	@Autowired
	private GenreService genreService;
	@Autowired
	private CharacterRepository characterRepository;
	@Override
	public List<Movie> findAll() {
		return movieRepo.findAll();
	}

	@Override
	public Optional<Movie> findById(Long id) {

		return movieRepo.findById(id);
	}

	@Override
	public Movie save(Movie movie) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
		String dateString = sdf.format(new Date());
		movie.setCreationDate(dateString);
		return movieRepo.save(movie);
	}

	@Override
	public Movie update(Movie movieRequest, Long id) {
		Optional<Movie> movie = movieRepo.findById(id);
		if (movie.isPresent()) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
			String dateString = sdf.format(new Date());
			movie.get().setCreationDate(dateString);
			movie.get().setImage(movieRequest.getImage());
			movie.get().setScore(movieRequest.getScore());
			movie.get().setTitle(movieRequest.getTitle());
			movie.get().setEnable(movieRequest.isEnable());
			return movieRepo.save(movie.get());
		} else
			return null;
	}

	@Override
	public void deleteById(Long id) {
		movieRepo.deleteById(id);

	}

	public List<Movie> findAllOrderByAsc() {
		return movieRepo.findAllOrderByAsc();
	}

	public List<Movie> findAllOrderByDesc() {
		return movieRepo.findAllOrderByDesc();
	}

	public List<Movie> findAllByTitle(String title) {
		return movieRepo.findAllByTitleAndEnable(title,true);
	}

	public Optional<Movie> findByTitle(String title) {
		return movieRepo.findByTitleAndEnable(title, true);
	}

	public void setGenres(MovieDTO movieRequest,Movie movieReq) {
		for (Genre genre: movieRequest.getGenres()) {
			Optional<Genre> genreResp = this.genreService.findByName(genre.getName());
			if(!genreResp.isEmpty()) {
				genreResp.get().getMovies().add(movieReq);
				this.genreService.save(genreResp.get());
			}else {
				Genre newGenre = new Genre(genre.getName(),genre.getImage(),true);
				newGenre.getMovies().add(movieReq);
				this.genreService.save(newGenre);
			}
		}
	}
	//recibe una pelicula con su listado de personajes.
	//si los personajes ingresados no existen los crea.
	//vincula la pelicula a los personajes.
	public void saveCharacters(MovieDTO moviedto) {
		Optional<Movie> newMovie = this.movieRepo.findByTitleAndEnable(moviedto.getTitle(), true);
		if(!moviedto.getCharacters().isEmpty()) {
			for(Characters c: moviedto.getCharacters()) {
				Optional<Characters> characterQuery = this.characterRepository.findByNameAndEnable(c.getName());
				if(characterQuery.isEmpty()) {
					c.setEnable(true);
					this.characterRepository.save(c);
				}else {
					characterQuery.get().getMovies().add(newMovie.get());
					this.movieRepo.save(newMovie.get());
				}
			}
			
		}
	}


}
