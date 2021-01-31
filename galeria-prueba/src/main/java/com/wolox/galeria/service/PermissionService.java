package com.wolox.galeria.service;

import java.util.List;
import java.util.Optional;

import com.wolox.galeria.entity.Permission;

public interface PermissionService {
	

	public Iterable<Permission> findAll();
	
	public Optional<Permission> findById(Long id);
	
	public Permission save(Permission permission);
	
	public Permission findByAlbumAndSharedUser(Permission permission);
	
	public List<Permission> getUsersByPermissionAndAlbum(Long idAlbum,String permissionType);

}
