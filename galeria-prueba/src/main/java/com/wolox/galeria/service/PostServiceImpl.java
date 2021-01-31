package com.wolox.galeria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wolox.galeria.entity.Post;
import com.wolox.galeria.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;


	@Override
	@Transactional(readOnly = true)
	public Iterable<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Post> findById(Long id) {
		return postRepository.findById(id);
	}

	@Override
	@Transactional
	public Post save(Post post) {
		return postRepository.save(post);
	}

}
