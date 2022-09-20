package com.ruchira.learn.springsecurity.model;

import javax.persistence.Entity;

@Entity
public class Role extends BaseModel {

	private String username;
	private String name;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
