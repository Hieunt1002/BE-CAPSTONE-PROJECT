package com.capstoneproject.educonnect.Repository.Impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.capstoneproject.educonnect.Service.FileService;

@Transactional
@Service
public class FileServiceImpl implements FileService {

	private final Path root = Paths.get("uploads");

	@Override
	public void init() {
		try {
			Files.createDirectories(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	@Override
	public void save(MultipartFile file) {
		try {

			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			if (e instanceof FileAlreadyExistsException) {
				throw new RuntimeException("A file of that name already exists.");
			}

			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteAll(String filename) {
		Path file = root.resolve(filename);
		try {
			FileSystemUtils.deleteRecursively(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}

	@Override
	public int saveImage(MultipartFile file, String pathImage, byte[] byteCapacity) {
		String message = "";
		try {
			Path userFolder = Paths.get(pathImage);
			if (Files.notExists(userFolder)) {
				Files.createDirectories(userFolder);
			}
			Path filePath = userFolder.resolve(file.getOriginalFilename());
			if (Files.exists(filePath)) {
				message = "File with the same name already exists: " + file.getOriginalFilename();
				return -1;
			}

			Files.copy(file.getInputStream(), filePath);
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return 1;
		} catch (IOException e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
			return 0;
		}

	}

	@Override
	public void saveUser(MultipartFile file, int userId) {
		String user = root.resolve(String.valueOf(userId)).toString();
		Path userPath = Paths.get(user);
		try {
			Files.createDirectories(userPath);
			Files.copy(file.getInputStream(), userPath.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			if (e instanceof FileAlreadyExistsException) {
				throw new RuntimeException("A file with that name already exists.");
			}
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Resource loadUser(String filename, int userid) {
		try {
			String user = root.resolve(String.valueOf(userid)).toString();
			Path userPath = Paths.get(user);
			Path file = userPath.resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteUser(String filename, int userid) {
		String user = root.resolve(String.valueOf(userid)).toString();
		Path userPath = Paths.get(user);
		Path file = userPath.resolve(filename);
		try {
			FileSystemUtils.deleteRecursively(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int deleteFile(String filePath, String fileName) {
		 try {
	            Path filePathDelete = Paths.get(filePath, fileName);

	            // Kiểm tra xem tệp tin tồn tại trước khi xóa
	            if (Files.exists(filePathDelete)) {
	                Files.delete(filePathDelete);
	                return 1;
	            } else {
	            	return 0;
	            }
	        } catch (IOException e) {
	           return 0;
	        }
		
	}

}
