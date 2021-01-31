package com.wolox.galeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wolox.galeria.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
