package ru.rxnnct.jobsmonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
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
//    @Transactional
    public void grab() {
        List<SourceGetMethod> sourceGetMethods;
        sourceGetMethods = sourceGetMethodRepo.findAll();
        sourceGetMethods.forEach(sourceGetMethod -> {
            String currentUrl;
            currentUrl = sourceGetMethod.getUrl();
            System.out.println(currentUrl);
        });
    }


}
