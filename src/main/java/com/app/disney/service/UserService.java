package com.app.disney.service;

import java.util.List;

import com.app.disney.models.User;

public interface UserService {
	User getById(Long id);
	User save(User user);
	List<User> getAll();
	boolean existsByUsername(String username);
}
