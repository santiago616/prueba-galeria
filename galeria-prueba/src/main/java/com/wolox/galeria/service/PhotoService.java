package com.wolox.galeria.service;

import java.util.Optional;

import com.wolox.galeria.entity.Photo;

public interface PhotoService {
	
	public Iterable<Photo> findAll();
	
	public Optional<Photo> findById(Long id);
	
	public Photo save(Photo photo);

}
