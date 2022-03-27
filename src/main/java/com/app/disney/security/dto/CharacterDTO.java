package com.app.disney.security.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CharacterDTO {
	@NotBlank
	private String name;
	@NotNull
	private int age;
	@NotNull
	private double weight ;
	@NotNull
	private String history;
	@NotNull
	private String image;

	
	public CharacterDTO() {
	}

	public CharacterDTO(String name, int age, double weight, String history, String image) {
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.history = history;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
