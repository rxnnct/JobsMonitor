package ru.rxnnct.jobsmonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rxnnct.jobsmonitor.repo.JobsQtyRepo;
import ru.rxnnct.jobsmonitor.repo.SourceGetMethodRepo;

@Component
public class JobsQtyGoogleChartsDataHandler {
    private final JobsQtyRepo jobsQtyRepo;
    private final SourceGetMethodRepo sourceGetMethodRepo;

    @Autowired
    public JobsQtyGoogleChartsDataHandler(JobsQtyRepo jobsQtyRepo, SourceGetMethodRepo sourceGetMethodRepo) {
        this.jobsQtyRepo = jobsQtyRepo;
        this.sourceGetMethodRepo = sourceGetMethodRepo;
    }

    public String makeGoogleChartsData(){
        String result;
        result = "Date_";
        //header loop
        //data loop

        return result;
//        return "Date_Java_Python,2001_1000_400,2002_1300_500";
    }
}
