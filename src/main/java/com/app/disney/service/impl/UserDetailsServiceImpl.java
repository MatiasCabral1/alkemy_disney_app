package com.app.disney.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.disney.models.PrincipalUser;
import com.app.disney.models.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserServiceImpl userService;

	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		User usuario = userService.getByUsername(nombreUsuario);
		return PrincipalUser.build(usuario);
	}

}
