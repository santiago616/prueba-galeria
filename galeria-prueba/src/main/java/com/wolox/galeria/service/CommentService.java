package com.wolox.galeria.service;

import java.util.Optional;

import com.wolox.galeria.entity.Comment;


public interface CommentService {

	public Iterable<Comment> findAll();
	
	public Optional<Comment> findById(Long id);
	
	public Comment save(Comment comment);

}
