package com.app.disney.security.dto;

import java.util.Date;

public class MovieDTO {
	private Long id;
	private String image;
	private String title;
	private Date creationDate;
	private int score;
	private boolean enable;
	private String genre;
	
	public MovieDTO() {}
	
	public MovieDTO(String image, String title, Date creationDate, int score, boolean enable, String genre) {
		this.image = image;
		this.title = title;
		this.creationDate = creationDate;
		this.score = score;
		this.enable = enable;
		this.genre = genre;
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
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
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
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}
