package com.wolox.galeria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wolox.galeria.entity.User;
import com.wolox.galeria.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

}
