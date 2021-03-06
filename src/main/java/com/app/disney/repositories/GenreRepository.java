package com.app.disney.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.disney.models.Characters;
import com.app.disney.models.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>{
	public boolean existsByName(String name);
	public Optional<Genre> findByName(String name);
	public List<Genre> findAllByMoviesIdAndEnable(Long id,boolean enable);
}
