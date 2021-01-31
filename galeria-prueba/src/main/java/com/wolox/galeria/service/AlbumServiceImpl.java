package com.wolox.galeria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wolox.galeria.entity.Album;
import com.wolox.galeria.repository.AlbumRepository;

@Service
public class AlbumServiceImpl implements AlbumService{
	
	@Autowired
	private AlbumRepository albumRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Album> findAll() {
		return albumRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Album> findById(Long id) {
		return albumRepository.findById(id);
	}

	@Override
	@Transactional
	public Album save(Album album) {
		return albumRepository.save(album);
	}


}
