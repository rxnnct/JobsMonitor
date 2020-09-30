package ru.rxnnct.jobsmonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rxnnct.jobsmonitor.domain.SourceGetMethod;
import ru.rxnnct.jobsmonitor.repo.SourceGetMethodRepo;

import java.util.List;

@Component
@EnableScheduling
public class HeadhunterJobsNumberGrabber {

    private final SourceGetMethodRepo sourceGetMethodRepo;

    @Autowired
    public HeadhunterJobsNumberGrabber(SourceGetMethodRepo sourceGetMethodRepo) {
        this.sourceGetMethodRepo = sourceGetMethodRepo;
    }

    @Scheduled(cron = "${headhunterJobsNumberGrabberSchedulerCronExpression}")
    public void reportCurrentTime() {
        List<SourceGetMethod> sourceGetMethods;
        sourceGetMethods = sourceGetMethodRepo.findAll();
        sourceGetMethods.forEach(sourceGetMethod -> {
            String currentUrl;
            currentUrl = sourceGetMethod.getUrl();
            System.out.println(currentUrl);
        });
    }


}
