package com.wolox.galeria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wolox.galeria.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

	@Query("select p from Permission p where p.album.id =:#{#permission.album.id} and p.user.id =:#{#permission.user.id}")
	Permission findByAlbumAndSharedUser(@Param("permission") Permission permission);
	
	@Query("select p from Permission p where p.album.id =?1 and p.modify =?2")
	List<Permission> getUsersByPermissionAndAlbum(Long idAlbum,String permissionType);

}
