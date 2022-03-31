package com.app.disney.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.app.disney.models.Characters;

public interface CharacterRepository extends JpaRepository<Characters, Long> {
	@Query(value = "SELECT * FROM Characters ch WHERE ch.name =?1 and ch.enable = 1", nativeQuery = true)
	List<Characters> getByName(String name);
	
	@Query(value = "SELECT * FROM Characters ch WHERE ch.age =?1 and ch.enable = 1", nativeQuery = true)
	List<Characters> getByAge(int age);
	
	@Query(value = "SELECT * FROM Characters ch WHERE ch.weight =?1 and ch.enable = 1", nativeQuery = true)
	List<Characters> getByWeight(double weight);
	
	List<Characters> findAllByMoviesIdAndEnable(Long id,boolean enable);
	
	@Query(value = "SELECT * FROM Characters ch WHERE ch.name =?1 and ch.enable = 1", nativeQuery = true)
	Optional<Characters> findByNameAndEnable(String name);
	
	
}
