package com.wolox.galeria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wolox.galeria.entity.Comment;
import com.wolox.galeria.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;

	@Transactional(readOnly = true)
	public Iterable<Comment> findAll() {
		return commentRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Comment> findById(Long id) {
		return commentRepository.findById(id);
	}
	
	@Transactional
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

}
