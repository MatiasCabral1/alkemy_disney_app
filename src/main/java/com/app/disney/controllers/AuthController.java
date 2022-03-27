package com.app.disney.controllers;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.disney.models.Rol;
import com.app.disney.models.User;
import com.app.disney.security.dto.JwtDTO;
import com.app.disney.security.dto.LoginUserDTO;
import com.app.disney.security.dto.Message;
import com.app.disney.security.dto.NewUserDTO;
import com.app.disney.security.enums.RolName;
import com.app.disney.security.jwt.JwtProvider;
import com.app.disney.service.UserService;
import com.app.disney.service.impl.RolService;
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	PasswordEncoder passEncoder;
	@Autowired
	RolService rolService;
	@Autowired
	UserService userService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody NewUserDTO userDTO,BindingResult result){
		//validaciones
		try {
			if(result.hasErrors())
				return new ResponseEntity<Message>(new Message(result.getFieldError().getDefaultMessage()),HttpStatus.BAD_REQUEST);
			if(this.userService.existsByUsername(userDTO.getUsername()))
				return new ResponseEntity<Message>(new Message("El usuario ingresado ya se encuentra registrado"),HttpStatus.BAD_REQUEST);
			//Creo un user con la pass hasheada
			User user = new User(userDTO.getUsername(),passEncoder.encode(userDTO.getPassword()));
			//agrego el rol por default "USER"  y lo guardo.
			Set<Rol> roles = new HashSet<>();
			roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
			user.setRoles(roles);
			userService.save(user);
			return new ResponseEntity<Message> (new Message("Usuario registrado"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Message> (new Message("Se produjo un error al registrar el usuario."),HttpStatus.BAD_REQUEST);
		}
		
	}
	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@Valid @RequestBody LoginUserDTO user,BindingResult result ){
		//validaciones
		if(result.hasErrors()) {
			return new ResponseEntity<Message>(new Message(result.getFieldError().getDefaultMessage()),HttpStatus.BAD_REQUEST);
		}
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		JwtDTO jwtDto = new JwtDTO(jwt);
		return new ResponseEntity<JwtDTO>(jwtDto,HttpStatus.OK);
	}
	//verificar refresh
	@PostMapping("/refresh")
	public ResponseEntity<?> refresh(@RequestBody JwtDTO jwtDto) throws ParseException {
		String token = jwtProvider.refreshToken(jwtDto);
		JwtDTO jwt = new JwtDTO(token);
		return new ResponseEntity<JwtDTO>(jwt, HttpStatus.OK);
	
	}
}
