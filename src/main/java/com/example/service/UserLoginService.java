package com.example.service;

import com.example.model.auth.User;

public interface UserLoginService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
