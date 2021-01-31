package com.wolox.galeria.service;

import java.util.Optional;

import com.wolox.galeria.entity.Post;


public interface PostService {
	
	public Iterable<Post> findAll();
	
	public Optional<Post> findById(Long id);
	
	public Post save(Post post);

}
