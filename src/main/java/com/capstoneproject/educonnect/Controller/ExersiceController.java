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

import com.capstoneproject.educonnect.DTO.AddClassRoomDTO;
import com.capstoneproject.educonnect.DTO.AddExerciseDTO;
import com.capstoneproject.educonnect.DTO.AddVideoDTO;
import com.capstoneproject.educonnect.DTO.ClassRoomEDTO;
import com.capstoneproject.educonnect.DTO.DetaiHomeworkOfStudentDTO;
import com.capstoneproject.educonnect.DTO.ExserciseDTO;
import com.capstoneproject.educonnect.DTO.FilesDTO;
import com.capstoneproject.educonnect.DTO.HomeWorkDTO;
import com.capstoneproject.educonnect.DTO.HomeworkAddDTO;
import com.capstoneproject.educonnect.DTO.HomeworkEDTO;
import com.capstoneproject.educonnect.DTO.HomeworkResDTO;
import com.capstoneproject.educonnect.DTO.ListScoreClassroomFollowExerciseByStudentDTO;
import com.capstoneproject.educonnect.DTO.ScoreClassroomDTO;
import com.capstoneproject.educonnect.DTO.ScoreExerciseDTO;
import com.capstoneproject.educonnect.DTO.UClassRoomDTO;
import com.capstoneproject.educonnect.DTO.UExerciseDTO;
import com.capstoneproject.educonnect.DTO.UVideoDTO;
import com.capstoneproject.educonnect.DTO.VideoDTO;
import com.capstoneproject.educonnect.Property.ResponseMessage;
import com.capstoneproject.educonnect.Service.ExserciseService;
import com.capstoneproject.educonnect.Service.FileService;

import io.jsonwebtoken.io.IOException;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/exersice")
@AllArgsConstructor
public class ExersiceController {

	private final FileService fileService;

	@Autowired
	private ExserciseService exserciseService;

	@GetMapping("/findexersice")
	public List<ExserciseDTO> findallExsercise(@RequestParam int bookid) {
		return exserciseService.findExersice(bookid);
	}

	@GetMapping("/getExerciseById/{exerciseId}")
	public ExserciseDTO getExerciseById(@PathVariable int exerciseId) {
		return exserciseService.getExerciseById(exerciseId);
	}

	@GetMapping("/fileexercise")
	public List<FilesDTO> fileexercise(@RequestParam int bookid) {
		return exserciseService.listFileFollowExercise(bookid);
	}

	@GetMapping("/getfileexercise")
	public List<FilesDTO> fileexerciseid(@RequestParam int bookid) {
		return exserciseService.listFileFollowExerciseid(bookid);
	}

	@GetMapping("/videoexercise")
	public List<VideoDTO> videoexercise(@RequestParam int bookid) {
		return exserciseService.listVideoFollowExercise(bookid);
	}

	@GetMapping("/getvideoexercise")
	public List<VideoDTO> videoexerciseid(@RequestParam int bookid) {
		return exserciseService.listVideoFollowExerciseid(bookid);
	}

	@GetMapping("/scoreexercise")
	public List<ScoreExerciseDTO> scoreeoFollowExercise(@RequestParam int bookid) {
		return exserciseService.listScoreFollowExercise(bookid);
	}

	@GetMapping("/homeworkexercise")
	public List<HomeWorkDTO> homeworkexercise(@RequestParam int bookid) {
		return exserciseService.listHomeworkExersice(bookid);
	}

	@GetMapping("/homeworkviewbytutor")
	public List<HomeworkEDTO> homeworkViewByTutor(@RequestParam int bookid) {
		return exserciseService.homeworkViewByTutor(bookid);
	}

	@GetMapping("/getHomeworkByExercise")
	public List<HomeworkResDTO> listHomeworkByExercise(@RequestParam int exerciseId) {
		return exserciseService.listHomeworkByExercise(exerciseId);
	}

	@GetMapping("/homework/detailhomework")
	public DetaiHomeworkOfStudentDTO detailHomeworkOfStudent(@RequestParam int homeworkid) {
		return exserciseService.detailHomeworkOfStudent(homeworkid);
	}

	
	@GetMapping("/classroomexercise")
	public List<ClassRoomEDTO> classroomexercise(@RequestParam int bookid) {
		return exserciseService.listClassRoomExersice(bookid);
	}

	@GetMapping("/getclassroomexercise")
	public List<ClassRoomEDTO> classroomexerciseid(@RequestParam int bookid) {
		return exserciseService.listClassRoomExersiceid(bookid);
	}
  
	@GetMapping("/scoreclassroom/{bookid}")
	public List<ListScoreClassroomFollowExerciseByStudentDTO> scoreclassroomExercise(
			@PathVariable("bookid") int bookid) {
		return exserciseService.listScoreClassroomFollowExerciseByStudentDTOs(bookid);
	}

	@PostMapping("/addhomework")
	public ResponseEntity<ResponseMessage> addHomework(@RequestParam int exerciseid, @RequestParam int demoid,
			@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam String title,
			@RequestParam String startDate, @RequestParam String endDate, @RequestParam int tutorid) {
		String message = "";
		try {
			if (file != null && !file.isEmpty()) {
				fileService.saveUser(file, tutorid);
			}

			exserciseService.AddHomeWorkTutor(exerciseid, demoid, file != null ? file.getOriginalFilename() : null,
					title, startDate, endDate);
			message = "Thêm bài tập về nhà thành công: " + title;
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Không thể tải lên tệp: " + (file != null ? file.getOriginalFilename() : "") + ". Lỗi: "
					+ e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@PostMapping("/addhomework2")
	public void addhomework2(@RequestBody HomeworkAddDTO homework) throws IOException {
		String fileName = "";

		int exerciseid = homework.getExerciseid();
		int demoid = homework.getDemoid();
		String title = homework.getTitle();
		String startDate = homework.getStartDate();
		String endDate = homework.getEndDate();
		exserciseService.AddHomeWorkTutor(exerciseid, demoid, fileName, title, startDate, endDate);
	}

	@PostMapping("/addfile")
	public ResponseEntity<ResponseMessage> addFiles(@RequestParam int exerciseid, @RequestParam int tutorid,
			@RequestParam String nameFile, @RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			if (file != null && !file.isEmpty()) {
				fileService.saveUser(file, tutorid);
				exserciseService.AddFileTutor(exerciseid, nameFile, file.getOriginalFilename());
			}
			message = "Thêm Files thành công: " + nameFile;
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Không thể tải lên tệp: " + (file.getOriginalFilename()) + ". Lỗi: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@PostMapping("/addclassroom")
	public ResponseEntity<ResponseMessage> addclassroom(@RequestBody AddClassRoomDTO addClassRoomDTO) {
		String message = "";
		try {
			exserciseService.AddClassRoomTutor(addClassRoomDTO);
			message = "Thêm bài kiểm tra thành công: " + addClassRoomDTO.getNameClassroom();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Không thể tải bài tập: " + (addClassRoomDTO.getNameClassroom()) + ". Lỗi: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@PostMapping("/addvideo")
	public ResponseEntity<ResponseMessage> addvideo(@RequestBody AddVideoDTO addVideoDTO) {
		String message = "";
		try {
			exserciseService.AddVideoTutor(addVideoDTO);
			message = "Thêm video thành công: " + addVideoDTO.getNameVideo();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Không thể tải lên tệp: " + (addVideoDTO.getNameVideo()) + ". Lỗi: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@DeleteMapping("/deleteclassroom/{id}")
	public ResponseEntity<ResponseMessage> deleteclassroom(@PathVariable("id") int classroomid) {
		String message = "";
		try {
			exserciseService.deleteclassroom(classroomid);
			message = "Xóa bài kiểm tra thành công ";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Không thể xóa bài kiểm tra, Lỗi: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@PostMapping("/addsubmithomework")
	public void addsubmithomework(@RequestParam int homeworkid, @RequestParam int studentid,
			@RequestPart("file") MultipartFile file) {
		fileService.saveUser(file, studentid);
		exserciseService.SubmitHomework(homeworkid, file.getOriginalFilename());
	}

	@DeleteMapping("/deletehomework/{id}/{tutorid}")
	public ResponseEntity<String> deletehomwork(@PathVariable("id") int homeworkid,
			@PathVariable("tutorid") int tutorid) {
		DetaiHomeworkOfStudentDTO result = exserciseService.detailHomeworkOfStudent(homeworkid);
		if (result.getFilesHomework() != null) {
			fileService.deleteUser(result.getFilesHomework(), tutorid);
			exserciseService.deletehomework(homeworkid);
		} else {
			exserciseService.deletehomework(homeworkid);
		}
		return ResponseEntity.ok("Xóa Thành công");
	}

	@DeleteMapping("/deletefile/{id}/{tutorid}")
	public ResponseEntity<String> deletefile(@PathVariable("id") int fileid, @PathVariable("tutorid") int tutorid) {
		String files = exserciseService.file(fileid);
		if (files != null) {
			fileService.deleteUser(files, tutorid);
			exserciseService.deletefile(fileid);
		} else {
			exserciseService.deletefile(fileid);
		}
		return ResponseEntity.ok("Xóa Thành công");
	}

	@DeleteMapping("/deletevideo/{id}")
	public ResponseEntity<String> deletefile(@PathVariable("id") int videoid) {
		exserciseService.deletevideo(videoid);
		return ResponseEntity.ok("Xóa Thành công");
	}

	@PostMapping("/addscoreclassroom")
	public int addscoreclassroom(@RequestBody ScoreClassroomDTO scoreClassroomDTO) {
		return exserciseService.ScoreClassroom(scoreClassroomDTO);
	}	

	@PostMapping("/addexercise")
	public void addexercise(@RequestBody AddExerciseDTO addExerciseDTO) {
		exserciseService.addExercise(addExerciseDTO);
	}

	@PutMapping("/updateexercise")
	public void updateexercise(@RequestBody UExerciseDTO uExerciseDTO) {
		exserciseService.UpdateExercise(uExerciseDTO);
	}

	@DeleteMapping("/deleteexercise/{id}")
	public void deleteExercise(@PathVariable("id") int exerciseId) {
		exserciseService.deleteExercise(exerciseId);
	}

	@PutMapping("/updatehomework")
	public ResponseEntity<ResponseMessage> updateHomework(@RequestParam int homeworkid, @RequestParam int tutorid,
			@RequestParam int demoid, @RequestPart(value = "file", required = false) MultipartFile file,
			@RequestParam String title, @RequestParam String startDate, @RequestParam String endDate) {
		String message = "";
		try {
			DetaiHomeworkOfStudentDTO result = exserciseService.detailHomeworkOfStudent(homeworkid);

			if (file != null) {
				if (result.getFilesHomework() != null) {
					fileService.deleteUser(result.getFilesHomework(), tutorid);
				}
				fileService.saveUser(file, tutorid);
			}

			exserciseService.UpdateHomeWord(homeworkid, demoid,
					file != null ? file.getOriginalFilename() : result.getFilesHomework(), title, startDate, endDate);

			message = "Cập nhật bài tập về nhà thành công: " + title;
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Không thể tải lên tệp: " + (file != null ? file.getOriginalFilename() : "") + ". Lỗi: "
					+ e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@PutMapping("/updatevideo")
	public ResponseEntity<ResponseMessage> updatevideo(@RequestBody UVideoDTO uVideoDTO) {
		String message = "";
		try {
			exserciseService.UpdateVideo(uVideoDTO);
			message = "Cập nhật video thành công: " + uVideoDTO.getNameVideo();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Không thể tải video: " + (uVideoDTO.getNameVideo()) + ". Lỗi: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@PutMapping("/updatefile")
	public ResponseEntity<ResponseMessage> updatefile(@RequestParam int fileid, @RequestParam String nameFile,
			@RequestPart(value = "file", required = false) MultipartFile file, @RequestParam int tutorid) {
		String message = "";
		try {
			String files = exserciseService.file(fileid);
			if (files != null) {
				fileService.deleteUser(files, tutorid);
				fileService.saveUser(file, tutorid);
				exserciseService.UpdateFiles(fileid, nameFile, file.getOriginalFilename());
			} else {
				fileService.saveUser(file, tutorid);
				exserciseService.UpdateFiles(fileid, nameFile, file.getOriginalFilename());
			}

			message = "Cập nhật file thành công: " + nameFile;
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Không thể tải bài tập: " + (file.getOriginalFilename()) + ". Lỗi: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@PutMapping("/updateclassroom")
	public ResponseEntity<ResponseMessage> updateclassroom(@RequestBody UClassRoomDTO uClassRoomDTO) {
		String message = "";
		try {
			exserciseService.UpdateClassRoom(uClassRoomDTO);
			message = "Cập nhật bài kiểm tra thành công: " + uClassRoomDTO.getNameClassroom();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Không thể tải bài tập: " + (uClassRoomDTO.getNameClassroom()) + ". Lỗi: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@PutMapping("/fileexercise/file/{fileid}")
	public void watchedFile(@PathVariable int fileid) {
		exserciseService.watchedFiles(fileid);
	}

	@PutMapping("/videoexercise/video/{videoid}")
	public boolean watchedVideo(@PathVariable int videoid) {
		exserciseService.watchedVideo(videoid);
		return true;
	}

	@PutMapping("/updatesubmit")
	public void updatesubmit(@RequestParam int homeworkid, @RequestParam int studentid,
			@RequestPart("file") MultipartFile file) {
		DetaiHomeworkOfStudentDTO detail = exserciseService.detailHomeworkOfStudent(homeworkid);
		fileService.deleteUser(detail.getFileSubmid(), studentid);
		fileService.saveUser(file, studentid);
		exserciseService.updatesubmit(homeworkid, file.getOriginalFilename());
	}
	@PutMapping("/updateScoresubmit")
	public int updateScoresubmit(@RequestParam float score, @RequestParam int submitid) {
		return exserciseService.updateScoresubmit(score, submitid);
	}
}
