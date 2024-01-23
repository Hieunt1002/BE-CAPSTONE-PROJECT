package com.capstoneproject.educonnect.Config;

import java.text.ParseException;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import com.capstoneproject.educonnect.Job.EmailSendingJob;
import com.capstoneproject.educonnect.Job.MailCloseCourse;

@Configuration
public class QuartzConfiguration {

	@Autowired
    private EmailSendingJob emailSendingJob;
	
	@Autowired
	private MailCloseCourse mailCloseCourse;

	@Bean
    public JobDetailFactoryBean emailSendingJobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(EmailSendingJob.class);
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }
    
    @Bean
    public JobDetailFactoryBean mailCloseCourseDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(MailCloseCourse.class);
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }
    
    @Bean
    public CronTriggerFactoryBean emailSendingJobTrigger(JobDetail emailSendingJobDetail) throws ParseException {
        CronTriggerFactoryBean triggerFactory = new CronTriggerFactoryBean();
        triggerFactory.setJobDetail(emailSendingJobDetail);
        triggerFactory.setStartDelay(1000L);
        triggerFactory.setCronExpression("0 50 * * * ? *");
        return triggerFactory;
    }

    @Bean
    public CronTriggerFactoryBean mailCloseCourseTrigger(JobDetail mailCloseCourseDetail) throws ParseException {
        CronTriggerFactoryBean triggerFactory = new CronTriggerFactoryBean();
        triggerFactory.setJobDetail(mailCloseCourseDetail);
        triggerFactory.setStartDelay(1000L);
        triggerFactory.setCronExpression("0 20 17 * * ?");
        return triggerFactory;
    }
}