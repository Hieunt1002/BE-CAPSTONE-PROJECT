package com.capstoneproject.educonnect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capstoneproject.educonnect.DTO.CourseDTO;
import com.capstoneproject.educonnect.DTO.DetailDemoDTO;
import com.capstoneproject.educonnect.DTO.ListDemoDTO;
import com.capstoneproject.educonnect.DTO.LopDTO;
import com.capstoneproject.educonnect.Entity.ClassCourse;
import com.capstoneproject.educonnect.Entity.Demo;
import com.capstoneproject.educonnect.Repository.ClassCourseRepository;
import com.capstoneproject.educonnect.Repository.IDemoRepository;
import com.capstoneproject.educonnect.Service.DemoService;
import com.capstoneproject.educonnect.Service.FileService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/demo")
@AllArgsConstructor
public class DemoController {
	
	private final FileService fileService;
	
	@Autowired
	private IDemoRepository iDemoRepository;

	@Autowired
	private DemoService demoService;
	
	@Autowired
	private ClassCourseRepository classCourseRepository;
	
	@GetMapping("/listdemobyclasscourse")
	public List<ListDemoDTO> listdemobyclasscourse (@RequestParam int classcourseid,
													@RequestParam(required = false) Integer page) {
		int pageNumber = (page != null) ? page : 1;
		int pages = (pageNumber - 1) * 10;
		return demoService.listdemobyclasscourse(classcourseid, pages);	
	}
	
	@GetMapping("/listdemo")
	public List<ListDemoDTO> listDemoAndFilt (@RequestParam(defaultValue = "0", required = false) int classid,
												@RequestParam(defaultValue = "0", required = false) int courseName) {
		if (classid == 0 && courseName == 0) {
			return demoService.listAllDemo();
		} else if(courseName == 0) {
			return demoService.listDemoWithId(classid);
		} else if(classid == 0) {
			return demoService.listDemoWithString(courseName);
		} else {
			return demoService.listDemoWithAll(classid, courseName);
		}	
		
	}
	
	@GetMapping("/listclass")
	public List<LopDTO> listclass(){
		return demoService.listclass();
	}
	
	@GetMapping("/listcourse")
	public List<CourseDTO> listcourse(){
		return demoService.listcourse();
	}
	
	@GetMapping("/totalpagedemo")
	public long totalpagedemo(@RequestParam int classcourseid) {
		return demoService.totalpage(classcourseid);
	}
	
	@GetMapping("/detaildemo")
	public DetailDemoDTO detaildemo(@RequestParam int demoid) {
		return demoService.detaildemo(demoid);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteDemo(@PathVariable("id") int demoid){
		iDemoRepository.deleteById(demoid);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@PostMapping("/adddemo")
	public ResponseEntity<String> addDemo(@RequestParam int classid, @RequestParam int coureid, @RequestParam String demo,
			@RequestParam String linkDemo, @RequestPart(value = "file", required = false) MultipartFile file){
		Demo demo1 = new Demo();
		String message = "";
		try {
			Demo check = iDemoRepository.findDemoByDemo(demo);
			if(check == null) {
				ClassCourse result = classCourseRepository.findClassCourseByClassidAndCourseid(classid, coureid);
				fileService.save(file);
				demo1.setClasscourseid(result.getClassCourseId());
				demo1.setDemo(demo);
				demo1.setFiles(linkDemo);
				demo1.setImg(file.getOriginalFilename());
				iDemoRepository.save(demo1);
				message = "Tạo mô phỏng đã thành công!";
				return new ResponseEntity<>(message, HttpStatus.OK);
			}else {
				message = "Tên mô phỏng đó đã sẵn sàng!";
				return new ResponseEntity<>(message, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			message = "Could not upload the image: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/updatedemo")
	public ResponseEntity<String> updateDemo(@RequestParam int demoid, 
			@RequestParam int classid, @RequestParam int coureid, @RequestParam String demo,
			@RequestParam String linkDemo, @RequestPart(value = "file", required = false) MultipartFile file){
		
		Demo demoDetail = iDemoRepository.findById(demoid).orElse(null);
		
		Demo demo1 = new Demo();
		String message = "";
		try {
			if (file == null || file.isEmpty()) {
				ClassCourse result = classCourseRepository.findClassCourseByClassidAndCourseid(classid, coureid);
				demo1.setDemoId(demoid);
				demo1.setClasscourseid(result.getClassCourseId());
				demo1.setDemo(demo);
				demo1.setFiles(linkDemo);
				demo1.setImg(demoDetail.getImg());
				iDemoRepository.save(demo1);
				message = "Update demo success!";
				return new ResponseEntity<>(message, HttpStatus.OK);
			} else {
				ClassCourse result = classCourseRepository.findClassCourseByClassidAndCourseid(classid, coureid);
				fileService.deleteAll(demoDetail.getImg());
				fileService.save(file);
				demo1.setDemoId(demoid);
				demo1.setClasscourseid(result.getClassCourseId());
				demo1.setDemo(demo);
				demo1.setFiles(linkDemo);
				demo1.setImg(file.getOriginalFilename());
				iDemoRepository.save(demo1);
				message = "Update demo success!";
				return new ResponseEntity<>(message, HttpStatus.OK);
			}
		} catch (Exception e) {
			message = "Could not upload the image: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/listAllDemo/{id}")
	public List<ListDemoDTO> listAllDemo (@PathVariable("id") int id) {
		return demoService.listAllDemos(id);
	}

}
