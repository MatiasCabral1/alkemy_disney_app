package com.app.disney.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.disney.models.Rol;
import com.app.disney.security.enums.RolName;
import com.app.disney.service.impl.RolService;

@Component
public class SetData implements CommandLineRunner{
	@Autowired
	RolService rolService;
	
	@Override
	public void run(String... args) throws Exception {
		if(this.rolService.getAll().isEmpty()) {
			this.rolService.save(new Rol(RolName.ROLE_USER));
			this.rolService.save(new Rol(RolName.ROLE_ADMIN));
		}
	}

}
