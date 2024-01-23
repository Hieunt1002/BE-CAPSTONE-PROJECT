package com.capstoneproject.educonnect.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstoneproject.educonnect.DTO.CourseDTO;
import com.capstoneproject.educonnect.DTO.DetailDemoDTO;
import com.capstoneproject.educonnect.DTO.ListDemoDTO;
import com.capstoneproject.educonnect.DTO.LopDTO;
import com.capstoneproject.educonnect.Repository.DemoRepository;

@Service
public class DemoService {
	
	@Autowired
	private DemoRepository demoRepository;
	
	public List<ListDemoDTO> listdemobyclasscourse(int classcourseid, int page){
		return demoRepository.listdemo(classcourseid, page);
	}
	
	public List<ListDemoDTO> listAllDemo(){
		return demoRepository.listAllDemo();
	}
	
	public List<ListDemoDTO> listAllDemos(int classcourseid){
		return demoRepository.listAllDemos(classcourseid);
	}
	
	public List<ListDemoDTO> listDemoWithId(int classid) {
		return demoRepository.listDemoWithId(classid);
	}
	
	public List<ListDemoDTO> listDemoWithString(int course) {
		return demoRepository.listDemoWithString(course);
	}
	
	public List<ListDemoDTO> listDemoWithAll(int classid, int courseName) {
		return demoRepository.listDemoWithAll(classid, courseName);
	}
	
	public long totalpage(int classcourseid) {
		return demoRepository.totalpage(classcourseid);
	}
	
	public List<LopDTO> listclass(){
		return demoRepository.classentity();
	}
	
	public List<CourseDTO> listcourse(){
		return demoRepository.listcourse();
	}
	
	public DetailDemoDTO detaildemo(int demoid) {
		return demoRepository.detaildemo(demoid);
	}
	
}
