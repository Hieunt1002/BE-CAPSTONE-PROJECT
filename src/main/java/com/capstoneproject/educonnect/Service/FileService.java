package com.capstoneproject.educonnect.Service;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	public void init();

	public void save(MultipartFile file);
	
	public void saveUser(MultipartFile file, int userid);

	public Resource load(String filename);
	
	public Resource loadUser(String filename, int userid);

	public void deleteAll(String filename);
	
	public void deleteUser(String filename, int userid);

	public Stream<Path> loadAll();
	
	public int saveImage(MultipartFile file, String pathImage, byte[] byteCapacity);
	
	public int deleteFile(String filePath, String fileName);
}
