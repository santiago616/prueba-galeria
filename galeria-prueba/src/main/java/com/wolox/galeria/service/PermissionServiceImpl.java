package com.wolox.galeria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wolox.galeria.entity.Permission;
import com.wolox.galeria.repository.PermissionRepository;

@Service
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	private PermissionRepository permissionRepository;

	@Transactional(readOnly = true)
	public Iterable<Permission> findAll() {
		return permissionRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Permission> findById(Long id) {
		return permissionRepository.findById(id);
	}
	
	@Transactional
	public Permission save(Permission permission) {
		return permissionRepository.save(permission);
	}

	@Override
	public Permission findByAlbumAndSharedUser(Permission permission) {
		return permissionRepository.findByAlbumAndSharedUser(permission);
	}

	@Override
	public List<Permission> getUsersByPermissionAndAlbum(Long idAlbum, String permissionType) {
		return permissionRepository.getUsersByPermissionAndAlbum(idAlbum, permissionType);
	}


}
