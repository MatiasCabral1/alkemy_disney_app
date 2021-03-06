package com.app.disney.security.dto.movie;

import java.util.Date;
import java.util.List;

import com.app.disney.models.Characters;
import com.app.disney.models.Genre;

public class MovieDTO {
	private Long id;
	private String image;
	private String title;
	private String creationDate;
	private int score;
	private boolean enable;
	private List<Genre> genres;
	private List<Characters> characters;
	
	public List<Characters> getCharacters() {
		return characters;
	}
	public void setCharacters(List<Characters> characters) {
		this.characters = characters;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
}
