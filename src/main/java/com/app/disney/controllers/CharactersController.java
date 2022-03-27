package com.app.disney.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping(value = "/character")
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
}
