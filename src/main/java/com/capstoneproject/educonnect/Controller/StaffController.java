package com.capstoneproject.educonnect.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.capstoneproject.educonnect.DTO.AcceptFileDTO;
import com.capstoneproject.educonnect.DTO.AcceptTrylearnDTO;
import com.capstoneproject.educonnect.DTO.AcceptTutorDTO;
import com.capstoneproject.educonnect.DTO.AcceptutorRegisterDTO;
import com.capstoneproject.educonnect.DTO.AddLinkMeetDTO;
import com.capstoneproject.educonnect.DTO.CountTutorAndStudentByClassDTO;
import com.capstoneproject.educonnect.DTO.DiscountDTO;
import com.capstoneproject.educonnect.DTO.ListStudentOfStaffDTO;
import com.capstoneproject.educonnect.DTO.ListTutorOfStaffDTO;
import com.capstoneproject.educonnect.DTO.ListTutorRegistersForLessonsDTO;
import com.capstoneproject.educonnect.DTO.ListWaitForConfirmTutorDTO;
import com.capstoneproject.educonnect.DTO.ManageStudentRegistrationForCoursesDTO;
import com.capstoneproject.educonnect.DTO.PayForTuttorDTO;
import com.capstoneproject.educonnect.DTO.PieChartDTO;
import com.capstoneproject.educonnect.DTO.RadarChartDTO;
import com.capstoneproject.educonnect.DTO.StaffStatisticsByMonthDTO;
import com.capstoneproject.educonnect.DTO.StaffStatisticsByYearDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileClasscourseTutorDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileStudentDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileStudentTrylearnDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileTimelineStudentDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileTimelineTutorDTO;
import com.capstoneproject.educonnect.DTO.StaffViewProfileTutorDTO;
import com.capstoneproject.educonnect.DTO.USalaryTutorDTO;
import com.capstoneproject.educonnect.DTO.ViewStaffDTO;
import com.capstoneproject.educonnect.DTO.ViewStudentTrylearnDTO;
import com.capstoneproject.educonnect.Entity.Discount;
import com.capstoneproject.educonnect.Service.FileService;
import com.capstoneproject.educonnect.Service.StaffService;
import com.capstoneproject.educonnect.Service.StaffServiceH;
import com.capstoneproject.educonnect.Service.ViewStaffService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/staffsconnect")
public class StaffController {

	private final StaffService staffService;
	private final ViewStaffService viewStaffService;
	private final FileService fileService;

	@Autowired
	private final StaffServiceH staffServiceH;
	
	@GetMapping("/staffstatisticscurrentmonth")
	public StaffStatisticsByMonthDTO staffStatisticsByCurrentMonth(@RequestParam Long staffId) {
		return staffServiceH.staffStatisticsByCurrentMonth(staffId);
	}
	
	@GetMapping("/countstudentandtutor")
	public List<CountTutorAndStudentByClassDTO> countstudentandtutor(){
		return staffServiceH.countstudentandtutor();
	}
	
	@GetMapping("/piechart")
	public List<PieChartDTO> piechart(){
		return staffServiceH.pieChart();
	}
	
	@GetMapping("/radarchart")
	public List<RadarChartDTO> radarchart(){
		return staffServiceH.radarChart();
	}
	
	@GetMapping("/staffstatisticspreviousmonth")
	public StaffStatisticsByMonthDTO staffStatisticsByPreviousMonth(@RequestParam Long staffId) {
		return staffServiceH.staffStatisticsByPreviousMonth(staffId);
	}
	
	@GetMapping("/staffstatisticsyear")
	public List<StaffStatisticsByYearDTO> staffStatisticsByYear(){
		return staffServiceH.staffStatisticsByYear();
	}
	
	
	@GetMapping("/managestudent")
	public List<ManageStudentRegistrationForCoursesDTO> listManageStudent(@RequestParam(required = false) Integer page) {
		int pageNumber = (page != null) ? page : 1;
		int pages = (pageNumber - 1) * 10;
		return staffServiceH.listManageStudent(pages);
	}
	
	@GetMapping("/detailmanagestudent")
	public ManageStudentRegistrationForCoursesDTO detailmanagestudent(@RequestParam int bookid) {
		return staffServiceH.detailManageStudent(bookid);
	}
	
	@GetMapping("/totalpageStudent")
	public long totalpgaeStudent() {
		return staffServiceH.totalPageStudentRegistrationForCourses();
	}
	
	
	
	@GetMapping("/totalPageStudentRegistration")
	public long totalPageStudentRegistration() {
		return staffServiceH.totalPageStudentRegistration();
	}
	
	//số lượng tutor đợi duyệt file
	@GetMapping("/countTutorRegistersForLessons")
	public long countTutorRegistersForLessons(@RequestParam int staffid) {
		return staffServiceH.countTutorRegistersForLessons(staffid);
	}
	
	@GetMapping("/listtutorregistersforlessons")
	public List<ListTutorRegistersForLessonsDTO> listTutorRegistersForLessons(@RequestParam(required = false) Integer page,
																			@RequestParam int staffid) {
		int pageNumber = (page != null) ? page : 1;
		int pages = (pageNumber - 1) * 10;
	    return staffServiceH.listTutorRegistersForLessons(staffid,pages);
	}
	
	@GetMapping("/totalpageTutor")
	public long totalpgaeTutor(@RequestParam int staffid) {
		return staffServiceH.totalPageTutorRegistersForLessons(staffid);
	}
	
	
	//Số lượng tutor chưa được duyêt
	@GetMapping("/countWaitForConfirmTutor")
	public long countWaitForConfirmTutor() {
		return staffServiceH.countWaitForConfirmTutor();
	}
	
	@GetMapping("/listwaitforconfirm")
	public List<ListWaitForConfirmTutorDTO> listWaitForConfirmTutor(@RequestParam(required = false) Integer page){
		int pageNumber = (page != null) ? page : 1;
		int pages = (pageNumber - 1) * 10;
		return staffServiceH.listWaitForConfirmTutor(pages);
	}
	
	@GetMapping("/pageforwaittutor")
	public long pageforwaittutor() {
		return staffServiceH.pageForConfirmTutor();
	}
	
	@PutMapping("/corfimrtutor")
	public void confirmTutor(@RequestParam int tutorid, @RequestParam int staffid, @RequestParam float price) {
		staffServiceH.confirmTutor(tutorid, staffid, price);
	}
	
	@PostMapping("/addClasscourseForTutor")
	public ResponseEntity<String> addClasscourseForTutor(@RequestParam int classcourseid, @RequestParam int tutorid) {
	    Boolean result = staffServiceH.addClasscourseForTutor(tutorid, classcourseid);
	    if (result) {
	        return ResponseEntity.ok("Create classcourse for tutor success!");
	    } else {
	    	return ResponseEntity.ok("Failed to create classcourse for tutor.");
	    }
	}
	
	@GetMapping("/discount")
	public List<Discount> getListDiscount() {
		List<Discount> list = new ArrayList<>();
		list = staffService.getListDiscount();
		return list;
	}

	@GetMapping("/ViewInfoStaff")
	public ViewStaffDTO ViewInfoStaff(@RequestParam int staffId) {
		return viewStaffService.ViewInfoStaff(staffId);
	}

	@PutMapping("/UpdateStaff")
	public void UpdateStaff(@RequestParam int staffid, @RequestParam String fullname,
			@RequestPart(value = "file", required = false) MultipartFile file,
			@RequestParam String birthdate, @RequestParam String city, @RequestParam String wards) {
		ViewStaffDTO staff = viewStaffService.ViewInfoStaff(staffid);
		if(file == null || file.isEmpty()) {
			viewStaffService.UpdateStaff(staffid, fullname, staff.getImg() == null ? null : staff.getImg(), birthdate, city, wards);
		}else {
			if(staff.getImg() != null) {
				fileService.deleteUser(staff.getImg(), staffid);
				fileService.saveUser(file, staffid);
				viewStaffService.UpdateStaff(staffid, fullname, file.getOriginalFilename(), birthdate, city, wards);
			}else {
				fileService.saveUser(file, staffid);
				viewStaffService.UpdateStaff(staffid, fullname, file.getOriginalFilename(), birthdate, city, wards);
			}
		}
	}

	@PutMapping("/discount/updateDiscount/{id}")
	public int putDiscount(@PathVariable long id, @RequestBody DiscountDTO discount) {
		int check = staffService.updateDiscount(discount);
		if (check > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@GetMapping("/studentTrylearning/{page}")
	public List<ViewStudentTrylearnDTO> viewStudentTrylearn(@PathVariable Integer page) {
		int pageNumber = (page != null) ? page : 1;
		int pages = (pageNumber - 1) * 10;
		return staffServiceH.viewStudentTrylearn(pages);
	}
	
	@GetMapping("/counttrylearn")
	public long counttrylearn() {
		return staffServiceH.counttrylearn();
	}
	
	@GetMapping("/counttrylearnpage")
	public long counttrylearnpage() {
		return staffServiceH.counttrylearnpage();
	}

	@GetMapping("/studentTrylearning/viewprofile/{trylearningid}")
	public StaffViewProfileStudentTrylearnDTO staffViewProfileStudentTrylearnDTO(@PathVariable int trylearningid) {
		return staffServiceH.staffViewProfileStudentTrylearnDTO(trylearningid);
	}
	
	@GetMapping("/tutor/{staffid}/{page}")
	public List<ListTutorOfStaffDTO> listTutorOfStaff(@PathVariable int staffid, @PathVariable Integer page) {
		int pageNumber = (page != null) ? page : 1;
		int pages = (pageNumber - 1) * 10;
		return staffServiceH.listTutorOfStaff(staffid, pages);
	}
	
	@GetMapping("/totaltutor")
	public long totaltutor (@RequestParam int staffid) {
		return staffServiceH.totalpagetutor(staffid);
	}

	@PutMapping("/tutor/updatesalary")
	public void updateSalary(@RequestBody USalaryTutorDTO uSalaryTutorDTO) {
		staffServiceH.updateSalary(uSalaryTutorDTO);
	}

	@GetMapping("/tutor/block/{tutorid}")
	public boolean blockTutor(@PathVariable int tutorid) {
		return staffServiceH.blockTutor(tutorid);
	}

	@GetMapping("/tutor/viewprofile/{tutorid}")
	public StaffViewProfileTutorDTO staffViewProfileTutor(@PathVariable int tutorid) {
		return staffServiceH.staffViewProfileTutor(tutorid);
	}

	@GetMapping("/tutor/viewprofile/classcourse/{tutorid}")
	public List<StaffViewProfileClasscourseTutorDTO> staffViewProfileClasscourseTutor(@PathVariable int tutorid) {
		return staffServiceH.staffViewProfileClasscourseTutor(tutorid);
	}

	@GetMapping("/tutor/viewprofile/timeline/{tutorid}")
	public List<StaffViewProfileTimelineTutorDTO> staffViewProfileTimelineTutor(@PathVariable int tutorid) {
		return staffServiceH.staffViewProfileTimelineTutor(tutorid);
	}
	
	@GetMapping("/learntime")
	public List<StaffViewProfileTimelineTutorDTO> learntime(@RequestParam int bookid){
		return staffServiceH.learntimestudentandtutor(bookid);
	}

	@GetMapping("/student")
	public List<ListStudentOfStaffDTO> listStudentOfStaff(@RequestParam Integer page) {
		int pageNumber = (page != null) ? page : 1;
		int pages = (pageNumber - 1) * 10;
		return staffServiceH.listStudentOfStaff(pages);
	}

	@GetMapping("/student/block/{studentid}")
	public ResponseEntity<String> blockStudent(@PathVariable int studentid) {
		boolean result = staffServiceH.blockStudent(studentid);
		if(result == true) {
			return ResponseEntity.ok("Thành công");
		}
		return ResponseEntity.ok("Người này đã bị chặn trước đó");
	}

	@GetMapping("/student/viewprofile/{studentid}")
	public StaffViewProfileStudentDTO staffViewProfileStudent(@PathVariable int studentid) {
		return staffServiceH.staffViewProfileStudent(studentid);
	}

	@GetMapping("/student/viewprofile/timeline/{studentid}")
	public List<StaffViewProfileTimelineStudentDTO> staffViewProfileTimelineStudent(
			@PathVariable("studentid") int studentid) {
		return staffServiceH.staffViewProfileTimelineStudent(studentid);
	}
	
	@GetMapping("/payfortutor")
	public List<PayForTuttorDTO> payfortutor(@RequestParam int staffid, @RequestParam(required = false) Integer page){
		int pageNumber = (page != null) ? page : 1;
		int pages = (pageNumber - 1) * 10;
		return staffServiceH.payfortutor(staffid, pages);
	}
	
	@GetMapping("/totalpay")
	public long totalpay (@RequestParam int staffid) {
		return staffServiceH.totalpay(staffid);
	}
	
	@GetMapping("/pagestudent")
	public long pagestudent () {
		return staffServiceH.pagestudent();
	}
	
	@GetMapping("/totalpayment")
	public long totalpayment (@RequestParam int staffid) {
		return staffServiceH.totalpayment(staffid);
	}
	
	@PutMapping("/accept/{tutorid}")
	public void accept(@PathVariable int tutorid) {
		staffServiceH.accept(tutorid);
	}
	
	@PutMapping("/accepttrylean")
	public ResponseEntity<String> accepttrylean(@RequestBody AcceptTrylearnDTO acceptTrylearnDTO) {
	    staffServiceH.accpettrylean(acceptTrylearnDTO.getStudentid(), acceptTrylearnDTO.getStatus(),
	            acceptTrylearnDTO.getDate(), acceptTrylearnDTO.getEmailstudent(), acceptTrylearnDTO.getEmailtutor(), acceptTrylearnDTO.getText());
	    if(acceptTrylearnDTO.getStatus() == 1) {
	    	return ResponseEntity.ok("Bạn đã phê duyệt yêu cầu thành công");
	    }else {
	    	return ResponseEntity.ok("Bạn đã từ chối yêu cầu thành công");
	    }
	}
	
	@PutMapping("/addlinkmeet")
	public ResponseEntity<String> addlinkmeet(@RequestBody AddLinkMeetDTO addLinkMeetDTO) {
		viewStaffService.addlinkmeet(addLinkMeetDTO);
	    	return ResponseEntity.ok("Bạn đã thêm thành công");
	}
	
	@PutMapping("/acceptfile")
	public ResponseEntity<String> acceptfile(@RequestBody AcceptFileDTO acceptFileDTO) {
		staffServiceH.acceptfile(acceptFileDTO);
	    return ResponseEntity.ok("Thành công");
	}
	
	@PutMapping("/accepttutor")
	public ResponseEntity<String> acceptutor(@RequestBody AcceptTutorDTO acceptTutorDTO){
		staffServiceH.accepttutor(acceptTutorDTO);
		return ResponseEntity.ok("Thành công");
	}
	
	@PutMapping("/accepttutorfinal")
	public ResponseEntity<String> accepttutorfinal(@RequestBody AcceptutorRegisterDTO acceptTutorDTO){
		staffServiceH.accepttutorfinal(acceptTutorDTO);
		return ResponseEntity.ok("Thành công");
	}
	
	@DeleteMapping("/deletetutorregister/{tutorid}")
	public ResponseEntity<String> acceptutor(@PathVariable int tutorid){
		staffServiceH.deletetutorRegister(tutorid);
		return ResponseEntity.ok("Xóa thành công");
	}
}
