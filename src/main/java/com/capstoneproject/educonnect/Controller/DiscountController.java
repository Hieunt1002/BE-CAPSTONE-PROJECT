package com.capstoneproject.educonnect.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.educonnect.DTO.CourseDTO;
import com.capstoneproject.educonnect.DTO.DiscountCourseDTO;
import com.capstoneproject.educonnect.DTO.DiscountDTO;
import com.capstoneproject.educonnect.Entity.Discount;
import com.capstoneproject.educonnect.Repository.DiscountQueryRepository;
import com.capstoneproject.educonnect.Service.DiscountService;

@RestController
@RequestMapping("/discount")
public class DiscountController {

	@Autowired
	private DiscountService discountService;
	
	@Autowired
	private DiscountQueryRepository discountQueryRepository;

	@PostMapping("/listdiscount")
	public ResponseEntity<Map<String, Object>> findallDiscount(@RequestBody Map<String, Object> mapRequest) {
		String title = mapRequest.get("title").equals("") ? null : mapRequest.get("title").toString();
		int pageNo = Integer.parseInt(mapRequest.get("pageNo").equals("") ? "0" : mapRequest.get("pageNo").toString());
		Map<String, Object>responseData  =  discountService.ListAllDiscount(title, pageNo);

		return ResponseEntity.ok(responseData);
	}
	
	@GetMapping("/listdiscounts")
	public List<Discount> listdiscount (){ 
		return discountQueryRepository.findAllDiscountloestartdateAndgoeenddate();
	}
	
	
	
	@GetMapping("/listdiscountforcourse")
	public List<Discount> listdiscountforcourse (){
		return discountQueryRepository.findAllDiscountforcourse();
	}

	@PostMapping("/adddiscount")
	public ResponseEntity<Map<String, Object>> adddiscount(@RequestBody DiscountDTO discount) {
		Map<String, Object>responseData  = discountService.saveDiscount(discount);
		return ResponseEntity.ok(responseData);
	}

	@PostMapping("/deleteDiscount")
	public ResponseEntity<Map<String, Object>> deleteDiscount(@RequestBody List<Integer> listDeleteDiscount) {
		Map<String, Object>responseData  = new HashMap<>();
		int check = discountService.deleteDiscount(listDeleteDiscount);
		responseData.put("message", check > 0 ? "Success" : "Fail");
		return ResponseEntity.ok(responseData);
	}
	
	@PutMapping("/updateDiscount")
	public ResponseEntity<Map<String, Object>> updateDiscount(@RequestBody DiscountDTO discount) {
		Map<String, Object>responseData  = new HashMap<>();
			int check = discountService.updateDiscount(discount);
			responseData.put("message", check > 0 ? "Success" : "Fail");
		return ResponseEntity.ok(responseData);
	}
	
	
	@PostMapping("/detailDiscount")
	public ResponseEntity<Discount> detailDiscount(@RequestBody Map<String, Object> mapRequest) {
		int discountId = Integer.valueOf(mapRequest.get("discountId").toString());
		Discount responseData  =  discountService.detailDiscount(discountId);
		return ResponseEntity.ok(responseData);
	}
	
	@PostMapping("/adddiscourse")
	public ResponseEntity<Map<String, Object>> adddiscourse(@RequestBody DiscountCourseDTO discount) {
		Map<String, Object>responseData  = new HashMap<>();
			int check = discountService.adddiscourse(discount);
			responseData.put("message", check > 0 ? "Success" : "Fail");
		return ResponseEntity.ok(responseData);
	}
	
	@GetMapping("/listcoursebydiscourseid")
	public ResponseEntity<List<CourseDTO>> listcoursebydiscourseid(@RequestParam int discountid) {
		List<CourseDTO> responseData  =  discountService.listcoursebydiscourseid(discountid);

		return ResponseEntity.ok(responseData);
	}
}
