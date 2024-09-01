package com.siripireddy.quartzscheduler.payload;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
@Setter
@Getter
public class EmailRequest   {

        private String email;

        private String subject;

        private String body;

        private LocalDateTime dateTime;

        private ZoneId timeZone;
}
