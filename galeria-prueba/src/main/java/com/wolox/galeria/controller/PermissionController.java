package com.wolox.galeria.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.wolox.galeria.entity.Permission;
import com.wolox.galeria.entity.User;
import com.wolox.galeria.service.PermissionService;


@RestController
@RequestMapping("/galeria/permisos")
public class PermissionController {
	
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Autowired
	private PermissionService permissionService;
	
	
	@PostMapping
	public ResponseEntity<?> createPermission(@RequestBody Permission permission){
		Optional<Permission> permissionSearch = permissionService.findById(permission.getId());
		if(permissionSearch.isPresent()) {
			return ResponseEntity.ok("El permiso ya se encuentra creado");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Permisos asignados con exito al usuario "
	+permissionService.save(permission).getUser().getUsername());		
	} 
	
	@PutMapping("/actualizarPermisos")
	public ResponseEntity<?> updatePermission( @RequestBody Permission permission) {
		Permission permissionSearch = permissionService.findByAlbumAndSharedUser(permission);
		try {
			permissionSearch.setModify(permission.getModify());
			Permission updatedPermission = permissionService.save(permissionSearch);
			return ResponseEntity.ok("Permisos cambiados con exito para el album "
					+updatedPermission.getAlbum().getTitle()
						+" y el usuario "+updatedPermission.getUser().getUsername());
		}catch(Exception e) {
			e.printStackTrace(); 
			return ResponseEntity
		            .status(HttpStatus.ACCEPTED)
		            .body("Permisos no encontrados");
		}
	}
	
	@GetMapping("/obtenerUsuarios/{albumId}/{permissionType}")
	public ResponseEntity<?> getUsersByPermissionAndAlbum(@PathVariable(value = "albumId") Long albumId
			,@PathVariable(value = "permissionType") String permissionType) {

		List<User> usersForPermission = new ArrayList<>();
		List<Permission> permissionsSearch = permissionService.getUsersByPermissionAndAlbum(albumId, permissionType);
		for(Permission p : permissionsSearch) {
			usersForPermission.add(p.getUser());	
		}
		return ResponseEntity.ok(usersForPermission);
	}
	

}
