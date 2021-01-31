package com.wolox.galeria.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.wolox.galeria.dto.AlbumDto;
import com.wolox.galeria.entity.Album;
import com.wolox.galeria.service.AlbumService;


@RestController
@RequestMapping("/galeria/albums")
public class AlbumController {

	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired
	private AlbumService albumService;

	
	 
	@PostMapping
	public ResponseEntity<?> createAlbum(@RequestBody Album album){ 
		Optional<Album> albumSearch = albumService.findById(album.getId());
		if(albumSearch.isPresent()) {
			return ResponseEntity.ok("El album ya se encuentra creado");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(albumService.save(album));		
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<?> readExternalAlbum(@PathVariable(value = "id") Long albumId){
		try {
		AlbumDto oAlbum = restTemplate().getForObject("https://jsonplaceholder.typicode.com/albums/"+albumId, AlbumDto.class);
		return ResponseEntity.ok(oAlbum);
		}catch(Exception e) {
		e.printStackTrace(); 
		return ResponseEntity
	            .status(HttpStatus.ACCEPTED)
	            .body("Album no encontrado en el servicio externo");
		}
	}	
	
	@RequestMapping("/listar")
	public AlbumDto[] readExternalsAlbums(){
		AlbumDto[] oAlbum = restTemplate().getForObject("https://jsonplaceholder.typicode.com/albums/", AlbumDto[].class);
		return oAlbum;
	}	
	
	@GetMapping("/permisosAlbum/{id}")
	public ResponseEntity<Optional<Album>> searchPermissionsByAlbumId(@PathVariable(value = "id") Long albumId) {
		Optional<Album> album = albumService.findById(albumId);
		return ResponseEntity.ok().body(album); 
	}
}
