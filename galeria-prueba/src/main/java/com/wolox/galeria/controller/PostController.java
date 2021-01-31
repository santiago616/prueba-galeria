package com.wolox.galeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.wolox.galeria.dto.PostDto;
import com.wolox.galeria.entity.Post;
import com.wolox.galeria.service.PostService;

@RestController
@RequestMapping("/galeria/posts")
public class PostController {
	
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired
	private PostService postService;
	
	
	@PostMapping
	public ResponseEntity<?> createPost(@RequestBody Post post){
		return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(post));		
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<?> readExternalPost(@PathVariable(value = "id") Long postId){
		try {
		PostDto oPost = restTemplate().getForObject("https://jsonplaceholder.typicode.com/posts/"+postId, PostDto.class);
		return ResponseEntity.ok(oPost);
		}catch(Exception e) {
			e.printStackTrace(); 
			return ResponseEntity
		            .status(HttpStatus.ACCEPTED)
		            .body("Post no encontrado en el servicio externo");
			}
	}	
	
	@RequestMapping("/listar")
	public PostDto[] readExternalsPosts(){
		PostDto[] oPost = restTemplate().getForObject("https://jsonplaceholder.typicode.com/posts/", PostDto[].class);
		return oPost;
	}	

}
