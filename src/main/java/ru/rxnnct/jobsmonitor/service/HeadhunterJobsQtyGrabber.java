package ru.rxnnct.jobsmonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.rxnnct.jobsmonitor.domain.SourceGetMethod;
import ru.rxnnct.jobsmonitor.repo.ProxyPropertyRepo;
import ru.rxnnct.jobsmonitor.repo.SourceGetMethodRepo;

import java.util.List;

@Component
@EnableScheduling
public class HeadhunterJobsQtyGrabber {

    private final int REQUEST_DELAY = 5000; //To prevent potential blocking by an external service
    private final SourceGetMethodRepo sourceGetMethodRepo;
    private final ProxyPropertyRepo proxyPropertyRepo;

    @Autowired
    public HeadhunterJobsQtyGrabber(SourceGetMethodRepo sourceGetMethodRepo, ProxyPropertyRepo proxyPropertyRepo) {
        this.sourceGetMethodRepo = sourceGetMethodRepo;
        this.proxyPropertyRepo =  proxyPropertyRepo;
    }

    //old code
//    @Scheduled(cron = "${headhunterJobsQtyGrabberSchedulerCronExpression}")
////    @Transactional
//    public void grab() {
//        List<SourceGetMethod> sourceGetMethods;
//        sourceGetMethods = sourceGetMethodRepo.findAll();
//        sourceGetMethods.forEach(sourceGetMethod -> {
//            String currentUrl;
//            currentUrl = sourceGetMethod.getUrl();
//            System.out.println(currentUrl);
//        });
//    }

    @Scheduled(cron = "${headhunterJobsQtyGrabberSchedulerCronExpression}")
//    @Transactional
    public void grab() {
        List<SourceGetMethod> sourceGetMethods;
        sourceGetMethods = sourceGetMethodRepo.findAll();
        sourceGetMethods.forEach(sourceGetMethod -> {
            String currentUrl;
            try {
                Thread.sleep(REQUEST_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentUrl = sourceGetMethod.getUrl();
            System.out.println(currentUrl + System.currentTimeMillis());
        });
    }
}