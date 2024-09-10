package com.siripireddy.quartzscheduler.web;

import com.siripireddy.quartzscheduler.payload.EmailRequest;
import com.siripireddy.quartzscheduler.payload.EmailResponse;
import com.siripireddy.quartzscheduler.quartz.job.EmailJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.UUID;

@RestController
//@Slf4j
public class EmailSchedulerController {

    @Autowired
    private Scheduler scheduler;

    @PostMapping(value ="/schedule/mail",produces = "application/json")
    public ResponseEntity<EmailResponse> scheduleEmail(@RequestBody EmailRequest emailRequest) {

        try {
            ZonedDateTime dataTime = ZonedDateTime.of(emailRequest.getDateTime(), emailRequest.getTimeZone());
            if (dataTime.isBefore(ZonedDateTime.now())) {
                EmailResponse response = new EmailResponse(false, "Date time must be after current Time");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(response);
            }

            JobDetail detail = buildJobDetail(emailRequest);
            Trigger trigger = buildTrigger(detail, dataTime);
            scheduler.scheduleJob(detail, trigger);

            EmailResponse response = new EmailResponse(true, detail.getKey().getName(), detail.getKey().getGroup(),
                    "Email Scheduled Successfully");
            return ResponseEntity.ok(null);
        } catch (SchedulerException exception) {
            //log.error("Error while scheduling email" + exception);
            EmailResponse response = new EmailResponse(false, "Error while scheduling job, retry again");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);


        }


    }

    @GetMapping("/get")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Test API");
    }


    private JobDetail buildJobDetail(EmailRequest emailRequest) {

        JobDataMap dataMap = new JobDataMap();
        dataMap.put("email", emailRequest.getEmail());
        dataMap.put("subject", emailRequest.getSubject());
        dataMap.put("body", emailRequest.getBody());

        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(UUID.randomUUID().toString())
                .withDescription("Send Email Job")
                .usingJobData(dataMap)
                .storeDurably()
                .build();

    }

    private Trigger buildTrigger(JobDetail detail, ZonedDateTime startAt) {

        return TriggerBuilder.newTrigger().
                forJob(detail)
                .withIdentity(detail.getKey().getName(), "email-trigger")
                .withDescription("send Email Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();

    }
}
