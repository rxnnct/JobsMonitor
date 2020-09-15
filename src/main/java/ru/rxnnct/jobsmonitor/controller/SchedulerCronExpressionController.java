package ru.rxnnct.jobsmonitor.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rxnnct.jobsmonitor.domain.SchedulerCronExpression;
import ru.rxnnct.jobsmonitor.repo.SchedulerCronExpressionRepo;

import java.util.List;

@RestController
@RequestMapping("api/scheduler-cron-expressions")
public class SchedulerCronExpressionController {
    private final SchedulerCronExpressionRepo schedulerCronExpressionRepo;

    @Autowired
    public SchedulerCronExpressionController(SchedulerCronExpressionRepo schedulerCronExpressionRepo) {
        this.schedulerCronExpressionRepo = schedulerCronExpressionRepo;
    }

    @GetMapping
    public List<SchedulerCronExpression> list() {
        return schedulerCronExpressionRepo.findAll();
    }

    @GetMapping("{id}")
    public SchedulerCronExpression getOne(@PathVariable("id") SchedulerCronExpression schedulerCronExpression) {
        return schedulerCronExpression;
    }

    @PostMapping
    public SchedulerCronExpression create(@RequestBody SchedulerCronExpression schedulerCronExpression) {
        return schedulerCronExpressionRepo.save(schedulerCronExpression);
    }

    @PutMapping("{id}")
    public SchedulerCronExpression update(
            @PathVariable("id") SchedulerCronExpression schedulerCronExpressionFromDb,
            @RequestBody SchedulerCronExpression schedulerCronExpression
    ) {
        BeanUtils.copyProperties(schedulerCronExpression, schedulerCronExpressionFromDb, "id");

        return schedulerCronExpressionRepo.save(schedulerCronExpressionFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") SchedulerCronExpression schedulerCronExpression) {
        schedulerCronExpressionRepo.delete(schedulerCronExpression);
    }
}
