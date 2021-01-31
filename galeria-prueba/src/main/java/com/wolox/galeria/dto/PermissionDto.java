package com.wolox.galeria.dto;


import com.wolox.galeria.entity.Album;

public class PermissionDto {
	
	private Long id;
	
	private Long sharedUserId;
	
	private String modify;
	
	private Album album;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSharedUserId() {
		return sharedUserId;
	}

	public void setSharedUserId(Long sharedUserId) {
		this.sharedUserId = sharedUserId;
	}

	public String getModify() {
		return modify;
	}

	public void setModify(String modify) {
		this.modify = modify;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	
}
