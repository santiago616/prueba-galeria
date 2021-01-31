package com.wolox.galeria.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

import com.wolox.galeria.dto.AlbumDto;
import com.wolox.galeria.dto.PhotoDto;
import com.wolox.galeria.dto.UserDto;
import com.wolox.galeria.entity.User;
import com.wolox.galeria.service.UserService;

@RestController
@RequestMapping("/galeria/usuarios")
public class UserController {
	
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user){
		Optional<User> userSearch = userService.findById(user.getId());
		if(userSearch.isPresent()) {
			return ResponseEntity.ok("El usuario ya se encuentra creado");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));		
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<?> readExternalUser(@PathVariable(value = "id") Long userId){
		try {
		UserDto oUser = restTemplate().getForObject("https://jsonplaceholder.typicode.com/users/"+userId, UserDto.class);
		if(oUser == null) {
            throw new RuntimeException("User id not found -"+userId);
        }
		return ResponseEntity.ok(oUser);
		}catch(Exception e) {
			e.printStackTrace(); 
			return ResponseEntity
		            .status(HttpStatus.ACCEPTED)
		            .body("Usuario no encontrado en el servicio externo");
			}
	}	
	
	@RequestMapping("/listar")
	public UserDto[] readExternalUser1(){
		UserDto[] oUser = restTemplate().getForObject("https://jsonplaceholder.typicode.com/users/", UserDto[].class);
		return oUser;
	}
	
	@RequestMapping("/buscarAlbumsUsuario/{id}")
	public ResponseEntity<?> seachAlbumsByUser(@PathVariable(value = "id") Long userId){
		try {
		AlbumDto[] oUser = restTemplate().getForObject("https://jsonplaceholder.typicode.com/albums?userId="+userId, AlbumDto[].class);
		return ResponseEntity.ok(oUser);
		}catch(Exception e) {
			e.printStackTrace(); 
			return ResponseEntity
		            .status(HttpStatus.ACCEPTED)
		            .body("Album no encontrado en el servicio externo");
			}
	}	
	
	@RequestMapping("/buscarFotosUsuario/{id}")
	public ResponseEntity<?> seachPhotosByUser(@PathVariable(value = "id") Long userId){
		try {
		List<PhotoDto> oPhotos = new ArrayList<PhotoDto>();
		AlbumDto[] oAlbums = restTemplate().getForObject("https://jsonplaceholder.typicode.com/albums?userId="+userId, AlbumDto[].class);
		for(AlbumDto album: oAlbums) {
			ResponseEntity<List<PhotoDto>> rateResponse =
					restTemplate().exchange("https://jsonplaceholder.typicode.com/photos?albumId="+album.getId(),
			                    HttpMethod.GET, null, new ParameterizedTypeReference<List<PhotoDto>>() {
			            });
			oPhotos.addAll(rateResponse.getBody());
		}
		return ResponseEntity.ok(oPhotos);
		}catch(Exception e) {
			e.printStackTrace(); 
			return ResponseEntity
		            .status(HttpStatus.ACCEPTED)
		            .body("Usuario no encontrado en el servicio externo");
			}
	}	
	
}
