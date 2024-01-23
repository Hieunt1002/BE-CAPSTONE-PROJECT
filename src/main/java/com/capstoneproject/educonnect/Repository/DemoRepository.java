package com.capstoneproject.educonnect.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.CourseDTO;
import com.capstoneproject.educonnect.DTO.DetailDemoDTO;
import com.capstoneproject.educonnect.DTO.ListDemoDTO;
import com.capstoneproject.educonnect.DTO.LopDTO;

@Repository
public interface DemoRepository {
	List<ListDemoDTO> listdemo(int classid, int page);
	
	List<ListDemoDTO> listAllDemos(int classcourseid);
	
	List<ListDemoDTO> listAllDemo();
		
	List<ListDemoDTO> listDemoWithId(int classid);
	
	DetailDemoDTO detaildemo (int demoid);
	
	List<ListDemoDTO> listDemoWithString(int courseid);
	
	List<ListDemoDTO> listDemoWithAll(int classid, int courseName);
	
	long totalpage(int classcourseid);	
	
	List<LopDTO> classentity();
	
	List<CourseDTO> listcourse();

}
