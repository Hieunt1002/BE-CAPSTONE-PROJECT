package com.capstoneproject.educonnect.DTO;

public class TimeBookDTO {
	
	private int studentid;
	
	private int timeId;
	
	private int lessonid;

	public TimeBookDTO() {

	}

	public TimeBookDTO(int studentid, int timeId, int lessonid) {
		this.studentid = studentid;
		this.timeId = timeId;
		this.lessonid = lessonid;
	}

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public int getTimeId() {
		return timeId;
	}

	public void setTimeId(int timeId) {
		this.timeId = timeId;
	}

	public int getLessonid() {
		return lessonid;
	}

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}
	
}
