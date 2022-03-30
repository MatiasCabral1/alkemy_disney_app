package com.app.disney.security.dto.movie;

public class MovieFilterReturnDTO {
	String image;
	String title;
	String date;
	
	
	public MovieFilterReturnDTO() {
	}
	public MovieFilterReturnDTO(String image, String title, String date) {
		this.image = image;
		this.title = title;
		this.date = date;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
