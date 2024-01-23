package com.capstoneproject.educonnect.Job;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.capstoneproject.educonnect.DTO.EmailDTO;
import com.capstoneproject.educonnect.Entity.ClassCourse;
import com.capstoneproject.educonnect.Entity.Student;
import com.capstoneproject.educonnect.Entity.Tutor;
import com.capstoneproject.educonnect.Repository.JPAClassCourseRepository;
import com.capstoneproject.educonnect.Repository.SchedulesRepostory;
import com.capstoneproject.educonnect.Repository.StudentRepository;
import com.capstoneproject.educonnect.Repository.TutorBatchRepository;

@Component
public class EmailSendingJob implements Job {

	private final JavaMailSender mailSender;
	private final SchedulesRepostory schedulesRepostory;
	private final StudentRepository studentRepository;
	private final TutorBatchRepository tutorBatchRepository;
	@Autowired
	private JPAClassCourseRepository jpaClassCourseRepository;

	@Autowired
	public EmailSendingJob(JavaMailSender mailSender, SchedulesRepostory schedulesRepostory,
			StudentRepository studentRepository, TutorBatchRepository tutorBatchRepository) {
		this.mailSender = mailSender;
		this.schedulesRepostory = schedulesRepostory;
		this.studentRepository = studentRepository;
		this.tutorBatchRepository = tutorBatchRepository;
	}

	LocalDate today = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", new Locale("vi"));
	String dateString = today.format(formatter);

	LocalTime currentHourMinute = LocalTime.now();
	LocalTime updatedTime = currentHourMinute.plusHours(1).plusMinutes(45);
	DateTimeFormatter formatters = DateTimeFormatter.ofPattern("HH:mm", new Locale("vi"));
	String formattedHourMinute = currentHourMinute.format(formatters);
	String formattedHourMinutes = updatedTime.format(formatters);
	LocalTime currentTimePlus5Minutes = currentHourMinute.plusMinutes(10);
	String formattedTimePlus5Minutes = currentTimePlus5Minutes.format(formatters);
	
	String timeline = formattedTimePlus5Minutes;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		List<Student> students = studentRepository.findAll();
		List<ClassCourse> result = jpaClassCourseRepository.findAll();
		for (ClassCourse classCourse : result) {
			for (Student student : students) {
				EmailDTO mail = schedulesRepostory.sendmail(student.getStudentid(), dateString,
						timeline, classCourse.getClassCourseId());
				EmailDTO mails = schedulesRepostory.sendmailstudent(student.getStudentid(), dateString,
						timeline, classCourse.getClassCourseId());
				if (mail != null && mail.getEmail() != null) {
					SimpleMailMessage mailMessage = new SimpleMailMessage();
					mailMessage.setFrom("EDU-CONNECT <capstoneeduconnect@gmail.com>");
					mailMessage.setTo(mail.getEmail());
					mailMessage.setSubject("Thông báo từ EDU-CONNECT");
					mailMessage.setText(getStudentCancellationEmailContent());

					mailSender.send(mailMessage);
				}else if(mails != null && mail.getEmail() != null) {
					SimpleMailMessage mailMessage = new SimpleMailMessage();
					mailMessage.setFrom("EDU-CONNECT <capstoneeduconnect@gmail.com>");
					mailMessage.setTo(mails.getEmail());
					mailMessage.setSubject("Thông báo từ EDU-CONNECT");
					mailMessage.setText(getStudentCancellationEmailContent());

					mailSender.send(mailMessage);
				}
			}
		}
		List<Tutor> tutors = tutorBatchRepository.findAll();
		for (ClassCourse classCourse : result) {
			for (Tutor tutor : tutors) {
				EmailDTO mail = schedulesRepostory.sendmailtutor(tutor.getTutorId(), dateString,
						timeline, classCourse.getClassCourseId());
				EmailDTO mails = schedulesRepostory.sendmailtutorchange(tutor.getTutorId(), dateString,
						timeline, classCourse.getClassCourseId());
				if (mail != null && mail.getEmail() != null) {
					SimpleMailMessage mailMessage = new SimpleMailMessage();
					mailMessage.setFrom("EDU-CONNECT <capstoneeduconnect@gmail.com>");
					mailMessage.setTo(mail.getEmail());
					mailMessage.setSubject("Thông báo từ EDU-CONNECT");
					mailMessage.setText(getTutorCancellationEmailContent());

					mailSender.send(mailMessage);
				}else if(mails != null && mails.getEmail() != null) {
					SimpleMailMessage mailMessage = new SimpleMailMessage();
					mailMessage.setFrom("EDU-CONNECT <capstoneeduconnect@gmail.com>");
					mailMessage.setTo(mails.getEmail());
					mailMessage.setSubject("Thông báo từ EDU-CONNECT");
					mailMessage.setText(getTutorCancellationEmailContent());

					mailSender.send(mailMessage);
				}
			}
		}
	}

	private String getStudentCancellationEmailContent() {
		return "Chào em,\n\n" + "EDU-CONNECT Thông báo lịch học của bạn:\n\n" 
				+ "Hiện tại ngày " + dateString +" " + formattedTimePlus5Minutes
				+ " giờ bạn có 1 tiết học vui lòng bạn tham gia lớp đầy đủ.\n\n" 
				+ "Trân trọng,\n"
				+ "EDU-CONNECT";
	}

	private String getTutorCancellationEmailContent() {
		return "Chào thầy/cô,\n\n" + "EDU-CONNECT Thông báo lịch dạy của bạn:\n\n" 
				+ "Hiện tại ngày "+ dateString + " " + formattedTimePlus5Minutes
				+ " giờ bạn có 1 tiết dạy vui lòng bạn tham gia lớp đầy đủ.\n\n" 
				+ "Trân trọng,\n"
				+ "EDU-CONNECT";
	}
}