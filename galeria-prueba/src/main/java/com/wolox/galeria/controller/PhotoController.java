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

import com.wolox.galeria.entity.Photo;
import com.wolox.galeria.service.PhotoService;

@RestController
@RequestMapping("/galeria/photos")
public class PhotoController {
	
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired
	private PhotoService photoService;
	
	
	@PostMapping
	public ResponseEntity<?> createPhoto(@RequestBody Photo photo){
		return ResponseEntity.status(HttpStatus.CREATED).body(photoService.save(photo));		
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<?> readExternalPhoto(@PathVariable(value = "id") Long photoId){
		try {
		Photo oPhoto = restTemplate().getForObject("https://jsonplaceholder.typicode.com/photos/"+photoId, Photo.class);
		
		return ResponseEntity.ok(oPhoto);
		}catch(Exception e) {
		e.printStackTrace(); 
		return ResponseEntity
	            .status(HttpStatus.ACCEPTED)
	            .body("Foto no encontrada en el servicio externo");
		}
	}	
	
	@RequestMapping("/listar")
	public Photo[] readExternalsPhotos(){
		Photo[] oPhoto = restTemplate().getForObject("https://jsonplaceholder.typicode.com/photos/", Photo[].class);
		return oPhoto;
	}	

}
