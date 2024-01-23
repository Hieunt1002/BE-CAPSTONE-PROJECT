package com.capstoneproject.educonnect.Repository;

import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.ViewFileDTO;

@Repository
public interface ViewFileRepository {
	
	ViewFileDTO ViewFile(int fileId);
}
