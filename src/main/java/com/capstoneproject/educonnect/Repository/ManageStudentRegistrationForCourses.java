package com.capstoneproject.educonnect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.ManageStudentRegistrationForCoursesDTO;
import com.capstoneproject.educonnect.Entity.BookingEntity;

@Repository
public interface ManageStudentRegistrationForCourses extends JpaRepository<BookingEntity, Integer> {
	@Query(value = "SELECT b.bookid, b.linkmeet, b.studentid, u1.fullname AS studentName, b.tutorid, u2.fullname AS tutorName, cc.classcourseid, CONCAT_WS(' ', co.coursename, ca.class) AS coursename, b.dateregister, b.enddate, b.datepay , b.status, "
			+ "CASE " + "    WHEN CURDATE() > b.enddate THEN 'Đã hoàn thành' "
			+ "    WHEN CURDATE() <= b.enddate AND CURDATE() >= b.startdate THEN 'Đang học' "
			+ "	   WHEN CURDATE() < b.startdate THEN 'Chuẫn bị bắt đầu'" 
			+ "    ELSE 'Đã nghỉ học' " 
			+ "END AS trangthai "
			+ "FROM booking b " + "JOIN student s ON s.studentid = b.studentid "
			+ "JOIN user u1 ON u1.userid = s.studentid " 
			+ "JOIN tutor t ON t.tutorid = b.tutorid "
			+ "JOIN user u2 ON u2.userid = t.tutorid " 
			+ "JOIN classcourse cc ON cc.classcourseid = b.classcourseid "
			+ "JOIN class ca on ca.classid = cc.classid "
			+ "JOIN course co ON co.courseid = cc.courseid "
			+ "order by b.bookid desc "
			+ "LIMIT 10 OFFSET :page " , nativeQuery = true)
	List<ManageStudentRegistrationForCoursesDTO> getList(@Param("page") int page);
	
	@Query(value = "SELECT b.bookid, b.linkmeet, b.studentid, u1.fullname AS studentName, b.tutorid, u2.fullname AS tutorName, cc.classcourseid, CONCAT_WS(' ', co.coursename, ca.class) AS coursename, b.dateregister, b.enddate, b.datepay, b.status, "
			+ "CASE " + "    WHEN CURDATE() > b.enddate THEN 'Đã hoàn thành' "
			+ "    WHEN CURDATE() <= b.enddate THEN 'Đang học' "
			+ "	   WHEN CURDATE() > b.startdate THEN 'Chuẫn bị bắt đầu'" 
			+ "    ELSE 'Đã nghỉ học' " 
			+ "END AS trangthai "
			+ "FROM booking b " + "JOIN student s ON s.studentid = b.studentid "
			+ "JOIN user u1 ON u1.userid = s.studentid " 
			+ "JOIN tutor t ON t.tutorid = b.tutorid "
			+ "JOIN user u2 ON u2.userid = t.tutorid " 
			+ "JOIN classcourse cc ON cc.classcourseid = b.classcourseid "
			+ "JOIN class ca on ca.classid = cc.classid "
			+ "JOIN course co ON co.courseid = cc.courseid "
			+ "where b.bookid = :bookid " , nativeQuery = true)
	ManageStudentRegistrationForCoursesDTO detailregister(@Param("bookid") int bookid);

	@Query(value = "SELECT COUNT(*) " 
			+ "FROM booking b " 
			+ "JOIN student s ON s.studentid = b.studentid "
			+ "JOIN user u1 ON u1.userid = s.studentid " 
			+ "JOIN tutor t ON t.tutorid = b.tutorid "
			+ "JOIN user u2 ON u2.userid = t.tutorid " 
			+ "JOIN classcourse cc ON cc.classcourseid = b.classcourseid "
			+ "JOIN course co ON co.courseid = cc.courseid ", nativeQuery = true)
	long countStudentRegistrationForCourses();
	
	@Query(value = "SELECT COUNT(*) " 
			+ "FROM booking b " 
			+ "JOIN student s ON s.studentid = b.studentid "
			+ "JOIN user u1 ON u1.userid = s.studentid " 
			+ "JOIN tutor t ON t.tutorid = b.tutorid "
			+ "JOIN user u2 ON u2.userid = t.tutorid " 
			+ "JOIN classcourse cc ON cc.classcourseid = b.classcourseid "
			+ "JOIN course co ON co.courseid = cc.courseid "
			+ "Where b.linkmeet is null ", nativeQuery = true)
	long countStudentRegistration();

}
