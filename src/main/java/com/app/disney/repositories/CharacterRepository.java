package com.app.disney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.disney.models.Characters;

public interface CharacterRepository extends JpaRepository<Characters, Long> {

}
