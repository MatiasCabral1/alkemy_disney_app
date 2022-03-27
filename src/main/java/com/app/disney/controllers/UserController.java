package com.app.disney.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.disney.models.User;
import com.app.disney.service.UserService;


@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping(path= "/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") Long id){
		return new ResponseEntity<User> (this.userService.getById(id),HttpStatus.OK);
	}
	
	@GetMapping
	public List<User> getAllUsers() {
	// get list of all users
	return this.userService.getAll();
	}
}
