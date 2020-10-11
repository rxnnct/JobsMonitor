package ru.rxnnct.jobsmonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rxnnct.jobsmonitor.domain.JobsQty;
import ru.rxnnct.jobsmonitor.repo.JobsQtyRepo;

import java.util.List;

@RestController
@RequestMapping("api/admin/jobs-qty")
public class JobsQtyController {
    private final JobsQtyRepo jobsQtyRepo;

    @Autowired
    public JobsQtyController(JobsQtyRepo jobsQtyRepo) {
        this.jobsQtyRepo = jobsQtyRepo;
    }

    @GetMapping
    public List<JobsQty> list() {
        return jobsQtyRepo.findAll();
    }

    @GetMapping("{id}")
    public JobsQty getOne(@PathVariable("id") JobsQty jobsQty) {
        return jobsQty;
    }
}
