package com.example.demo.model;

public class JwtResponse {
	String token;

	public JwtResponse() {
		
	}

	public String getToken() {
		return token;
	}

	public JwtResponse(String token) {
		this.token = token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
