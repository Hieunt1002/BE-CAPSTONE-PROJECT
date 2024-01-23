package com.capstoneproject.educonnect.Job;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.capstoneproject.educonnect.DTO.InforuserDTO;
import com.capstoneproject.educonnect.Entity.Student;
import com.capstoneproject.educonnect.Repository.SchedulesRepostory;
import com.capstoneproject.educonnect.Repository.StudentRepository;
import com.capstoneproject.educonnect.Service.TutorService;

@Component
public class MailCloseCourse implements Job {

	private final JavaMailSender mailSender;
    private final SchedulesRepostory schedulesRepostory;
    private final StudentRepository studentRepository;
    
    @Autowired
	private TutorService tutorService;
    
    @Autowired
    public MailCloseCourse(JavaMailSender mailSender, SchedulesRepostory schedulesRepostory, StudentRepository studentRepository) {
        this.mailSender = mailSender;
        this.schedulesRepostory = schedulesRepostory;
        this.studentRepository = studentRepository;
    }
    
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String dateString = today.format(formatter);
    
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		List<Student> students = studentRepository.findAll();
    	for (Student student : students) {
    		List<InforuserDTO> mail = schedulesRepostory.sendmailclosecousestudent(student.getStudentid(), dateString);
    		for (InforuserDTO inforuserDTO : mail) {
    			if (inforuserDTO != null && inforuserDTO.getEmail() != null) {
                    SimpleMailMessage mailMessage = new SimpleMailMessage();
                    mailMessage.setFrom("EDU-CONNECT <capstoneeduconnect@gmail.com>");
                    mailMessage.setTo(inforuserDTO.getEmail());
                    mailMessage.setSubject("Thông báo từ EDU-CONNECT");
                    mailMessage.setText(getStudentCancellationEmailContent(inforuserDTO.getCourses(), inforuserDTO.getFullname()));
                    mailSender.send(mailMessage);
                }
			}
		}
    	for (Student student : students) {
    		List<InforuserDTO> mail = schedulesRepostory.sendmailclosecousetutor(student.getStudentid(), dateString);
    		for (InforuserDTO inforuserDTO : mail) {
    			if (inforuserDTO != null && inforuserDTO.getEmail() != null) {
    				tutorService.updatesalary(inforuserDTO.getEmail());
                    SimpleMailMessage mailMessage = new SimpleMailMessage();
                    mailMessage.setFrom("EDU-CONNECT <capstoneeduconnect@gmail.com>");
                    mailMessage.setTo(inforuserDTO.getEmail());
                    mailMessage.setSubject("Thông báo từ EDU-CONNECT");
                    mailMessage.setText(getTutorCancellationEmailContent(inforuserDTO.getCourses(), inforuserDTO.getFullname()));
                    mailSender.send(mailMessage);
                }
			}
		}
	}
	private String getStudentCancellationEmailContent(String course, String fullname) {
        return "Chào em,\n\n"
                + "EDU-CONNECT Thông báo bạn đã kết thúc khóa học "+course+":\n\n"
                + "Hiện tại ngày "+dateString+" bạn đã kết thúc khóa học "+course+".\n\n"
                + "Dạy bởi gia sự : "+fullname+" .\n\n"
                + "Cảm ơn bạn đã tin tưởng đến chúng tôi chúc bạn có một ngày vui vẻ.\n\n"
                + "Trân trọng,\n"
                + "EDU-CONNECT";
    }
    private String getTutorCancellationEmailContent(String course, String fullname) {
        return "Chào thầy/cô,\n\n"
        		+ "EDU-CONNECT Thông báo bạn đã kết thúc khóa học "+course+":\n\n"
        		+ "Hiện tại ngày "+dateString+" bạn đã kết thúc khóa học "+course+".\n\n"
                + "Học sinh : "+fullname+" .\n\n"
                + "Cảm ơn bạn đã tin tưởng đến chúng tôi chúc bạn có một ngày vui vẻ.\n\n"
                + "Trân trọng,\n"
                + "EDU-CONNECT";
    }

}
