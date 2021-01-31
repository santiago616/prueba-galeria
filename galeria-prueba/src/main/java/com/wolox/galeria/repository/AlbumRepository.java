package com.wolox.galeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wolox.galeria.entity.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long>{

}
