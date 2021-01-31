package com.wolox.galeria.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "permissions")
public class Permission implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "idUser", nullable = true, updatable = true)
    private User user;
	
	private String modify;
	
    @ManyToOne
    @JoinColumn(name = "idAlbum", nullable = true, updatable = true)
	private Album album;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Album getAlbum() {
		return album;
	}

	public String getModify() {
		return modify;
	}

	public void setModify(String modify) {
		this.modify = modify;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	
	
}
