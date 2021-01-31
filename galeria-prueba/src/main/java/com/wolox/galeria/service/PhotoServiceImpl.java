package com.wolox.galeria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wolox.galeria.entity.Photo;
import com.wolox.galeria.repository.PhotoRepository;

@Service
public class PhotoServiceImpl implements PhotoService{

	
	@Autowired
	private PhotoRepository photoRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Photo> findAll() {
		return photoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Photo> findById(Long id) {
		return photoRepository.findById(id);
	}

	@Override
	@Transactional
	public Photo save(Photo photo) {
		return photoRepository.save(photo);
	}

}
