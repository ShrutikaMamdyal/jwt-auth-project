package com.example.demo.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if(username .equals("shrutika")) {
			return new User("shrutika","Admin@123",new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("Username not found");
		}
	}
	
}
