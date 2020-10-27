package ru.rxnnct.jobsmonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.rxnnct.jobsmonitor.service.HeadhunterJobsQtyGrabber;

@RestController
@RequestMapping("api/admin/run-headhunter-jobs-qty-grabber")
public class RunHeadhunterJobsQtyGrabberController {
    @Autowired
    private ApplicationContext context;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String runOnce() {
        context.getBean(HeadhunterJobsQtyGrabber.class).grab();
        return "";
    }
}
