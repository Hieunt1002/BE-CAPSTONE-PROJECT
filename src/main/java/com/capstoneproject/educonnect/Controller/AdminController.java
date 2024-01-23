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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capstoneproject.educonnect.DTO.AdminStatisticsByMonthDTO;
import com.capstoneproject.educonnect.DTO.AdminStatisticsByYearDTO;
import com.capstoneproject.educonnect.DTO.ListStaffByAdminDTO;
import com.capstoneproject.educonnect.DTO.PieChartDTO;
import com.capstoneproject.educonnect.DTO.RadarChartDTO;
import com.capstoneproject.educonnect.DTO.USalaryStaffDTO;
import com.capstoneproject.educonnect.Entity.User;
import com.capstoneproject.educonnect.Repository.UserRepository;
import com.capstoneproject.educonnect.Service.AdminService;
import com.capstoneproject.educonnect.Service.FileService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	private final FileService fileService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/addstaff")
	public ResponseEntity<String> addstaff(@RequestParam String fullName,@RequestParam String email,@RequestParam String password, 
			@RequestParam String phone,@RequestParam int gender, @RequestPart(value = "file", required = false) MultipartFile file, 
			@RequestParam String birthdate,@RequestParam String city,@RequestParam String wards,@RequestParam float salary,
			int experience) {
		User result = userRepository.findUserByEmail(email);
		if(result == null) {
			if(file != null) {
				fileService.save(file);
				adminService.AddStaff(fullName, email, password, phone, gender, file.getOriginalFilename(), birthdate, city, wards, salary, experience);
			}else {
				adminService.AddStaff(fullName, email, password, phone, gender, null, birthdate, city, wards, salary, experience);
			}
			
			return new ResponseEntity<>("User registered success!", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/piechart")
	public List<PieChartDTO> piechart(){
		return adminService.pieChart();
	}
	
	@GetMapping("/radarchart")
	public List<RadarChartDTO> radarchart(){
		return adminService.radarChartAdmin();
	}
	
	@GetMapping("/adminstatisticscurrentmonth")
	public AdminStatisticsByMonthDTO adminStatisticsByCurrentMonth() {
		return adminService.adminStatisticsByCurrentMonth();
	}
	
	@GetMapping("/adminstatisticspreviousmonth")
	public AdminStatisticsByMonthDTO adminStatisticsByPreviousMonth() {
		return adminService.adminStatisticsByPreviousMonth();
	}
	
	@GetMapping("/adminstatisticsyear")
	public List<AdminStatisticsByYearDTO> adminStatisticsByYear(){
		return adminService.adminStatisticsByYear();
	}
	
	@GetMapping("/staff")
	public List<ListStaffByAdminDTO> listStaff(@RequestParam(required = false) Integer page) {
		int pageNumber = (page != null) ? page : 1;
		int pages = (pageNumber - 1) * 10;
		return adminService.listStaff(pages);
	}
	
	@PutMapping("/staff/updatesalary")
	public void updateSalaryStaff(@RequestBody USalaryStaffDTO uSalaryStaffDTO) {
		adminService.updateSalaryStaff(uSalaryStaffDTO);
	}
	
	@DeleteMapping("/staff/deletestaff/{id}")
	public void deleteStaff(@PathVariable("id") int staffid) {
		adminService.deleteStaff(staffid);
	}
	
	@GetMapping("/totalpage")
	public long totalpgae() {
		return adminService.totalpage();
	}
}
