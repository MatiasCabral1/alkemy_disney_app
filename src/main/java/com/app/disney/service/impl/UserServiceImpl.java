package com.app.disney.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.disney.models.User;
import com.app.disney.repositories.UserRepository;
import com.app.disney.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	public User getByUsername(String username) {
		return this.userRepository.getByUsername(username).get();
	}

	public User save(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User getById(Long id) {
		User user=this.userRepository.findById(id).get();
		return user;
	}

	@Override
	public List<User> getAll() {
		return this.userRepository.findAll();
	}

	@Override
	public boolean existsByUsername(String username) {
		return this.userRepository.existsByUsername(username);
	}
	
	


}
