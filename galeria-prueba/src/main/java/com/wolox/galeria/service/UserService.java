package com.wolox.galeria.service;

import java.util.Optional;
import com.wolox.galeria.entity.User;

public interface UserService {
	
	public Iterable<User> findAll();
	
	public Optional<User> findById(Long id);
	
	public User save(User user);
	

}
