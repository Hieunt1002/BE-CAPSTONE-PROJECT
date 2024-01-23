package com.capstoneproject.educonnect.Controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.capstoneproject.educonnect.DTO.ViewFileDTO;
import com.capstoneproject.educonnect.Entity.Files;
import com.capstoneproject.educonnect.Property.ResponseMessage;
import com.capstoneproject.educonnect.Service.FileService;
import com.capstoneproject.educonnect.Service.ViewFileService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/edu/file")
public class FileController {

	private final FileService fileService;
	@Autowired
	private ViewFileService viewFileService;
	
	private static final String UPLOAD_DIR_IMAGE = "E:/Project Capstone/BE/BE-CAPSTONE-PROJECT/uploads/";

	@PostMapping("/upload/discount")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		int message = 0;
		String pathImage = UPLOAD_DIR_IMAGE + "discount" ;
		try {

			 message = fileService.saveImage(file,pathImage, file.getBytes());
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(String.valueOf(message)));
		} catch (Exception e) {
			message = -1;
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(String.valueOf(message)));
		}
	}
	@DeleteMapping("/deleteFile/discount/{filename:.+}")
	public ResponseEntity<ResponseMessage> deleteFile(@PathVariable String filename) {
		int message = 0;
		String pathImage = UPLOAD_DIR_IMAGE + "discount" ;
		try {

			 message = fileService.deleteFile(pathImage,filename);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(String.valueOf(message)));
		} catch (Exception e) {
			message = -1;
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(String.valueOf(message)));
		}
	}
	
	
	@GetMapping("/files")
	public ResponseEntity<List<Files>> getListFiles() {
		List<Files> fileInfos = fileService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();

			return new Files(0, 0, filename, url, 0);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = fileService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@GetMapping("/fileuser/{filename:.+}/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getFileUser(@PathVariable String filename,@PathVariable int id) {
		Resource file = fileService.loadUser(filename, id);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("/viewFile")
	public ViewFileDTO ViewFile(@RequestParam int fileId) {
		return viewFileService.ViewFile(fileId);
	}
	
	@PostMapping("/uploadImage/{userid}")
	public ResponseEntity<ResponseMessage> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable int userid) {
		String message = "";
		String pathImage = UPLOAD_DIR_IMAGE + userid ;
		try {

//			 message = fileService.saveImage(file,pathImage, file.getBytes());
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}
	@PostMapping("/uploadImages")
	public ResponseEntity<ResponseMessage> uploadImages(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			fileService.save(file);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}
	
	@GetMapping("/fileImg/{userid}/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename,@PathVariable int userid) throws IOException {
		String pathImage = UPLOAD_DIR_IMAGE + userid ;
		Path filePath = Paths.get(pathImage).resolve(filename);
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	

}
