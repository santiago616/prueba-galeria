package com.wolox.galeria.service;

import java.util.Optional;

import com.wolox.galeria.entity.Album;

public interface AlbumService {
	
	public Iterable<Album> findAll();
	
	public Optional<Album> findById(Long id);
	
	public Album save(Album album);
	

}
