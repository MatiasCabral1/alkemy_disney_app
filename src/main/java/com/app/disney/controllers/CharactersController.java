package com.app.disney.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.disney.models.Characters;
import com.app.disney.security.dto.CharacterDTO;
import com.app.disney.security.dto.CharacterReturnDTO;
import com.app.disney.security.dto.Message;
import com.app.disney.service.CharacterService;

@RestController
@RequestMapping(value = "/characters")
public class CharactersController {
	 @Autowired
	 CharacterService characterService;
	 
	 @GetMapping
	 public ResponseEntity<?> getAll(){
		 try {
			 return new ResponseEntity<List<CharacterReturnDTO>> (this.characterService.listAll(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Message> (new Message("Ocurrio un error al obtener el listado"),HttpStatus.BAD_REQUEST);
		}
		 
	 }
	 
	 @PostMapping("/save")
	 public ResponseEntity<?> save(@Valid@RequestBody CharacterDTO characterDTO, BindingResult result){
		 if(result.hasErrors())
			 return new ResponseEntity<Message> (new Message(result.getFieldError().getDefaultMessage()),HttpStatus.BAD_REQUEST);
		 
		 Characters newCharacter = new Characters(characterDTO.getName(),characterDTO.getAge(),characterDTO.getWeight(),characterDTO.getHistory(),true,characterDTO.getImage());
		 this.characterService.save(newCharacter);
		 return new ResponseEntity<Message> (new Message("Personaje registrado exitosamente"),HttpStatus.OK);
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
			if(request.isEmpty())
				return new ResponseEntity<Message> (new Message("No se encontraron personajes con la edad ingresada"),HttpStatus.BAD_REQUEST);
			return new ResponseEntity<List<Characters>> (request,HttpStatus.OK);
		 } catch (Exception e) {
			return new ResponseEntity<Message> (new Message("Se produjo un error"),HttpStatus.BAD_REQUEST);
		}
	 }
	 @GetMapping("/name/{name}")
	 public ResponseEntity<?> getCharactersByName(@PathVariable("name") String name){
		 try {
			 List<Characters> request = this.characterService.getByName(name);
			if(request.isEmpty())
				return new ResponseEntity<Message> (new Message("No se encontraron personajes con el nombre ingresado"),HttpStatus.BAD_REQUEST);
			return new ResponseEntity<List<Characters>> (request,HttpStatus.OK);
		 } catch (Exception e) {
			return new ResponseEntity<Message> (new Message("Se produjo un error"),HttpStatus.BAD_REQUEST);
		}
	 }
	 @GetMapping("/movie/{id}")
	 public ResponseEntity<?> getCharactersByMovie(@PathVariable("id") Long id){
		 try {
			 List<Characters> request = this.characterService.getByIdMovie(id);
			if(request.isEmpty())
				return new ResponseEntity<Message> (new Message("No se encontraron personajes para la pelicula ingresada"),HttpStatus.BAD_REQUEST);
			return new ResponseEntity<List<Characters>> (request,HttpStatus.OK);
		 } catch (Exception e) {
			return new ResponseEntity<Message> (new Message("Se produjo un error"),HttpStatus.BAD_REQUEST);
		}
	 }
	 @GetMapping("/weight/{weight}")
	 public ResponseEntity<?> getCharactersByWeight(@PathVariable("weight") double weight){
		 try {
			 List<Characters> request = this.characterService.getByWeight(weight);
			if(request.isEmpty())
				return new ResponseEntity<Message> (new Message("No se encontraron personajes con el peso ingresado"),HttpStatus.BAD_REQUEST);
			return new ResponseEntity<List<Characters>> (request,HttpStatus.OK);
		 } catch (Exception e) {
			return new ResponseEntity<Message> (new Message("Se produjo un error"),HttpStatus.BAD_REQUEST);
		}
	 }
}
