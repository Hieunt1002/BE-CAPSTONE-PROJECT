package com.capstoneproject.educonnect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstoneproject.educonnect.DTO.ListTutorRegistersForLessonsDTO;
import com.capstoneproject.educonnect.Entity.Files;

@Repository
public interface ListTutorRegistersForLessons extends JpaRepository<Files, Integer> {
    @Query(value = "SELECT b.tutorid, u.fullname as tutorName, f.fileid, f.namefile, f.files, f.status, ex.exerciseid, "
    		+ "ex.title, b.classcourseid, CONCAT_WS(' ', co.coursename, ca.class) AS coursename " +
            "FROM files f " +
            "JOIN exercise ex ON ex.exerciseid = f.exerciseid " +
            "JOIN booking b ON b.bookid = ex.bookid " +
            "JOIN tutor t ON t.tutorid = b.tutorid " +
            "JOIN user u ON u.userid = t.tutorid " +
            "JOIN staff sf ON sf.staffid = t.staffid " +
            "JOIN classcourse cc ON cc.classcourseid = b.classcourseid " +
            "JOIN teach te ON te.tutorid = b.tutorid AND te.classcourseid = b.classcourseid " +
            "JOIN course co ON co.courseid = cc.courseid "+ 
            "join class ca on ca.classid = cc.classid " +
            "WHERE f.status = 3 AND sf.staffid = :staffid " +
            "LIMIT 10 OFFSET :page", nativeQuery = true)
    List<ListTutorRegistersForLessonsDTO> getListTutorRegistersForLessons(@Param("staffid") int staffid, @Param("page") int page);
    
    
    @Query(value = "SELECT COUNT(*)" +
            "FROM files f " +
            "JOIN exercise ex ON ex.exerciseid = f.exerciseid " +
            "JOIN booking b ON b.bookid = ex.bookid " +
            "JOIN tutor t ON t.tutorid = b.tutorid " +
            "JOIN user u ON u.userid = t.tutorid " +
            "JOIN staff sf ON sf.staffid = t.staffid " +
            "JOIN classcourse cc ON cc.classcourseid = b.classcourseid " +
            "JOIN teach te ON te.tutorid = b.tutorid AND te.classcourseid = b.classcourseid " +
            "JOIN course co ON co.courseid = cc.courseid " +
            "WHERE f.status = 3 AND sf.staffid = :staffid", nativeQuery = true)
    long countTutorRegistersForLessons(@Param("staffid") int staffid);
}
