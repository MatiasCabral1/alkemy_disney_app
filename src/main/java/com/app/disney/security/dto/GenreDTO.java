package com.app.disney.security.dto;

public class GenreDTO {
	private Long id;
	private String name;
	private String image;
	private boolean enable;
	
	public GenreDTO() {}
	
	public GenreDTO(String name, String image, boolean enable) {
		this.name = name;
		this.image = image;
		this.enable = enable;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
}
