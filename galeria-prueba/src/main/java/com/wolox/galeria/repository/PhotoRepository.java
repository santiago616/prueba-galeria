package com.wolox.galeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wolox.galeria.entity.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>{

}
