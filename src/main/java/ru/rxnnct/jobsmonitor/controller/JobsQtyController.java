package ru.rxnnct.jobsmonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rxnnct.jobsmonitor.repo.JobsQtyRepo;
import ru.rxnnct.jobsmonitor.service.JobsQtyConverter;

@RestController
@RequestMapping("api/jobs-qty")
public class JobsQtyController {
    private final JobsQtyRepo jobsQtyRepo;
    private final ApplicationContext context;

    @Autowired
    public JobsQtyController(JobsQtyRepo jobsQtyRepo, ApplicationContext context) {
        this.jobsQtyRepo = jobsQtyRepo;
        this.context = context;
    }

    @GetMapping
    public String stats() {
        return context.getBean(JobsQtyConverter.class).convertToGoogleChartsArray(jobsQtyRepo.findAll());
    }
}
