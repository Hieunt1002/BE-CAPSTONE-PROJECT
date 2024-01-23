package com.capstoneproject.educonnect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.educonnect.DTO.ViewFileDTO;
import com.capstoneproject.educonnect.Repository.ViewFileRepository;

@Service
public class ViewFileService {
	@Autowired
	private ViewFileRepository FileRepository;
	
	public ViewFileDTO ViewFile(int fileId) {
		return FileRepository.ViewFile(fileId);
	}
}
