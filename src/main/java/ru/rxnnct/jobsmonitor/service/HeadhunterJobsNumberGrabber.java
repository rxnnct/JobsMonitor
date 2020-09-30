package ru.rxnnct.jobsmonitor.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableScheduling
public class HeadhunterJobsNumberGrabber {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "${headhunterJobsNumberGrabberSchedulerCronExpression}")
    public void reportCurrentTime() {
        System.out.println(dateFormat.format(new Date()) + ": Test!");
    }
}
