package com.wolox.galeria.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.wolox.galeria.dto.CommentDto;
import com.wolox.galeria.dto.PostDto;
import com.wolox.galeria.entity.Comment;
import com.wolox.galeria.service.CommentService;

@RestController
@RequestMapping("/galeria/commentarios")
public class CommentController {
	
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired
	private CommentService commentService;
	
	
	@PostMapping
	public ResponseEntity<?> createComment(@RequestBody Comment comment){
		return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(comment));		
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<?> readExternalComment(@PathVariable(value = "id") Long commentId){
		try {
		Comment oComment = restTemplate().getForObject("https://jsonplaceholder.typicode.com/comments/"+commentId, Comment.class);
		return ResponseEntity.ok(oComment);
		}catch(Exception e) {
			e.printStackTrace(); 
			return ResponseEntity
		            .status(HttpStatus.ACCEPTED)
		            .body("Comentario no encontrado en el servicio externo");
			}
	}	
	
	@RequestMapping("/listar")
	public Comment[] readExternalsComments(){
		Comment[] oComment = restTemplate().getForObject("https://jsonplaceholder.typicode.com/comments/", Comment[].class);
		return oComment;
	}	

	@RequestMapping("/buscarCommentUsuario")
	public ResponseEntity<?> seachCommentsByNameOrUser(@RequestBody CommentDto commentDto){
		try {
		List<Comment> oComments = new ArrayList<Comment>();
		if(commentDto.getName() != null) {
			ResponseEntity<List<Comment>> oComment =
					restTemplate().exchange("https://jsonplaceholder.typicode.com/comments?name="+commentDto.getName(),
			                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Comment>>() {
			            });	
			oComments.addAll(oComment.getBody());
			return ResponseEntity.ok(oComments);
		}
		if(commentDto.getUserId() != null) {
			PostDto[] oPost = restTemplate().getForObject("https://jsonplaceholder.typicode.com/posts?userId="+commentDto.getUserId()
			, PostDto[].class);			
			for(PostDto post: oPost) {
				ResponseEntity<List<Comment>> rateResponse =
						restTemplate().exchange("https://jsonplaceholder.typicode.com/comments?postId="+post.getId(),
				                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Comment>>() {
				            });
				oComments.addAll(rateResponse.getBody());
			}
		}
		return ResponseEntity.ok(oComments);
		
		}catch(Exception e) {
		e.printStackTrace(); 
		return ResponseEntity
	            .status(HttpStatus.ACCEPTED)
	            .body("Usuario o nombre no valido");
		}
	}	

}
