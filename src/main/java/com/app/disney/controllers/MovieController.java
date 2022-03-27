package com.app.disney.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.disney.models.Genre;
import com.app.disney.models.Movie;
import com.app.disney.security.dto.GenreDTO;
import com.app.disney.security.dto.Message;
import com.app.disney.security.dto.MovieDTO;
import com.app.disney.service.impl.GenreServiceImpl;
import com.app.disney.service.impl.MovieServiceImpl;


@RestController
@RequestMapping(value = "/movie")
public class MovieController {
	@Autowired
	private MovieServiceImpl movieService;
	
	@Autowired
	private GenreServiceImpl genreService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<?> create(@Valid@RequestBody MovieDTO movieDTO,BindingResult result) {
		// validaciones:
		if (result.hasErrors()) {
			return new ResponseEntity<Message>(new Message(result.getFieldError().getDefaultMessage()),
							HttpStatus.BAD_REQUEST);
				}

		// convert DTO to entity
		
		Movie movieRequest = modelMapper.map(movieDTO, Movie.class);
		movieRequest.setEnable(true);
        Genre genre = this.genreService.findByName(movieDTO.getGenre());
        List<Movie> listaP= genre.getMovies();
        listaP.add(movieRequest);
        genre.setMovies(listaP);
        this.genreService.save(genre);
        Movie movie= movieService.save(movieRequest);

		// convert entity to DTO
        MovieDTO movieResp = modelMapper.map(movie, MovieDTO.class);

		return new ResponseEntity<MovieDTO>(movieResp, HttpStatus.CREATED);
	}
}
