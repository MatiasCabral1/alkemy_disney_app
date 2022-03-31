package com.app.disney.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.disney.models.Characters;
import com.app.disney.models.Movie;
import com.app.disney.security.dto.CharacterDTO;
import com.app.disney.security.dto.CharacterFilterReturnDTO;
import com.app.disney.security.dto.Message;
import com.app.disney.service.CharacterService;
import com.app.disney.service.MovieService;

@RestController
@RequestMapping(value = "/characters")
public class CharactersController {
	 @Autowired
	 CharacterService characterService;
	 @Autowired
	 private ModelMapper modelMapper;
	 @Autowired
	 private MovieService movieService;
	 
	 @GetMapping
	 public ResponseEntity<?> getAll(){
		 try {
			List<Characters> listCharacters=  this.characterService.listAll();
			List<CharacterFilterReturnDTO> listReturn = Arrays.asList(modelMapper.map(listCharacters,CharacterFilterReturnDTO[].class)); 
			if(listCharacters.isEmpty())
				return new ResponseEntity<Message> (new Message("No se encontraron personajes registrados"),HttpStatus.BAD_REQUEST); 
			return new ResponseEntity<List<CharacterFilterReturnDTO>> (listReturn,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Message> (new Message("Ocurrio un error al obtener el listado"),HttpStatus.BAD_REQUEST);
		}
		 
	 }
	 
	 @GetMapping("details/{id}")
	 public ResponseEntity<?> details(@PathVariable("id") Long id){
		 try {
			 Optional<Characters> character = this.characterService.findById(id);
			 CharacterDTO characterResponse = modelMapper.map(character.get(), CharacterDTO.class);
			 if(character.isEmpty())
					return new ResponseEntity<Message> (new Message("No se encuentra el personaje con id: "+ id),HttpStatus.BAD_REQUEST); 
			return new ResponseEntity<CharacterDTO> (characterResponse,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Message> (new Message("Se produjo un error"),HttpStatus.BAD_REQUEST);
		}
	 }
	 @PostMapping("/save")
	 public ResponseEntity<?> save(@Valid@RequestBody CharacterDTO characterDTO, BindingResult result){
		 try {
			 if(result.hasErrors())
				 return new ResponseEntity<Message> (new Message(result.getFieldError().getDefaultMessage()),HttpStatus.BAD_REQUEST);
			 
			 Characters characterRequest = modelMapper.map(characterDTO, Characters.class);
			 characterRequest.getMovies().clear();
			 
			 //nota: la pelicula debe crearse previamente, si no existe una pelicula no se puede asociar/crearse el personaje.
			 //recorremos lista de peliculas del dto. la buscamos en la base de datos y agregamos a request con los datos llenos.
			 for(Movie movie: characterDTO.getMovies()) {
				 Optional<Movie> movieResp = this.movieService.findByTitle(movie.getTitle());
				 if(!movieResp.isEmpty()) {
					 characterRequest.getMovies().add(movieResp.get());
				 }
			 }
			 characterRequest.setEnable(true);
			 this.characterService.save(characterRequest);
			 
			 CharacterDTO characterResp = modelMapper.map(characterRequest, CharacterDTO.class);
			 
			 return new ResponseEntity<CharacterDTO> (characterResp,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Message> (new Message("Se produjo un error"),HttpStatus.BAD_REQUEST);
		}
		 
	 }
	 
	 @PutMapping("/update")
	 public ResponseEntity<?> update(@RequestBody Characters character){
		 try {
			return new ResponseEntity<Characters> (this.characterService.update(character),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Message> (new Message("Se produjo un error al actualizar"),HttpStatus.BAD_REQUEST);
		}
	 }
	 
	 @GetMapping("/age/{age}")
	 public ResponseEntity<?> getCharactersByAge(@PathVariable("age") int age){
		 try {
			 List<Characters> request = this.characterService.getByAge(age);
			 
			 List<CharacterFilterReturnDTO> listReturn = Arrays.asList(modelMapper.map(request,CharacterFilterReturnDTO[].class)); 
			if(request.isEmpty())
				return new ResponseEntity<Message> (new Message("No se encontraron personajes con la edad ingresada"),HttpStatus.BAD_REQUEST);
			return new ResponseEntity<List<CharacterFilterReturnDTO>> (listReturn,HttpStatus.OK);
		 } catch (Exception e) {
			return new ResponseEntity<Message> (new Message("Se produjo un error"),HttpStatus.BAD_REQUEST);
		}
	 }
	 @GetMapping("/name/{name}")
	 public ResponseEntity<?> getCharactersByName(@PathVariable("name") String name){
		 try {
			 List<Characters> listCharacters = this.characterService.getByName(name);
				List<CharacterFilterReturnDTO> listReturn = Arrays.asList(modelMapper.map(listCharacters,CharacterFilterReturnDTO[].class)); 
			if(listCharacters.isEmpty())
				return new ResponseEntity<Message> (new Message("No se encontraron personajes con el nombre ingresado"),HttpStatus.BAD_REQUEST);
			return new ResponseEntity<List<CharacterFilterReturnDTO>> (listReturn,HttpStatus.OK);
		 } catch (Exception e) {
			return new ResponseEntity<Message> (new Message("Se produjo un error"),HttpStatus.BAD_REQUEST);
		}
	 }
	 @GetMapping("/movie/{id}")
	public ResponseEntity<?> getCharactersByMovie(@PathVariable("id") Long id) {
		try {
			List<Characters> listCharacters = this.characterService.findAllByMoviesIdAndEnable(id);
			List<CharacterFilterReturnDTO> listReturn = Arrays.asList(modelMapper.map(listCharacters,CharacterFilterReturnDTO[].class)); 
			if (listCharacters.isEmpty())
				return new ResponseEntity<Message>(
						new Message("No se encontraron personajes para la pelicula ingresada"), HttpStatus.BAD_REQUEST);
			return new ResponseEntity<List<CharacterFilterReturnDTO>>(listReturn, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Message>(new Message("Se produjo un error"), HttpStatus.BAD_REQUEST);
		}
	}
	 
	 @GetMapping("/weight/{weight}")
	 public ResponseEntity<?> getCharactersByWeight(@PathVariable("weight") double weight){
		 try {
			 List<Characters> listCharacters = this.characterService.getByWeight(weight);
			 List<CharacterFilterReturnDTO> listReturn = Arrays.asList(modelMapper.map(listCharacters,CharacterFilterReturnDTO[].class));
			if(listCharacters.isEmpty())
				return new ResponseEntity<Message> (new Message("No se encontraron personajes con el peso ingresado"),HttpStatus.BAD_REQUEST);
			return new ResponseEntity<List<CharacterFilterReturnDTO>> (listReturn,HttpStatus.OK);
		 } catch (Exception e) {
			return new ResponseEntity<Message> (new Message("Se produjo un error"),HttpStatus.BAD_REQUEST);
		}
	 }
	 
	 @DeleteMapping("/{id}")
		public ResponseEntity<?> delete(@PathVariable(value = "id") Long characterId) {
		 try {
				Optional<Characters> character = characterService.findById(characterId);
				if(character.isPresent()) {
					this.characterService.delete(characterId);
					return new ResponseEntity<Message>(new Message("Personaje "+character.get().getName() +" eliminado!"), HttpStatus.OK);
				}
				else {
					return new ResponseEntity<Message>(new Message("El personaje no existe"), HttpStatus.BAD_REQUEST);
				}
		} catch (Exception e) {
			return new ResponseEntity<Message> (new Message("Se produjo un error"),HttpStatus.BAD_REQUEST);
		}
			
	 }
}
