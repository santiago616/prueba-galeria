package com.wolox.galeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wolox.galeria.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
