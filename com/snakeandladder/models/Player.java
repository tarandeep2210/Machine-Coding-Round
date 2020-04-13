package com.snakeandladder.models;

import java.util.UUID;

public class Player {
	private String name;
	private String id;
	
	
	public Player(String name){
		this.name = name;
		this.id = UUID.randomUUID().toString();
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
