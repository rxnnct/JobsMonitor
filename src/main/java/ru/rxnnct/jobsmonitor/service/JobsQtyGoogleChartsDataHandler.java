package ru.rxnnct.jobsmonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rxnnct.jobsmonitor.domain.JobsQty;
import ru.rxnnct.jobsmonitor.domain.SourceGetMethod;
import ru.rxnnct.jobsmonitor.repo.JobsQtyRepo;
import ru.rxnnct.jobsmonitor.repo.SourceGetMethodRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        String result = "Date";
        List<SourceGetMethod> sourceGetMethods;
        sourceGetMethods = sourceGetMethodRepo.findAll();
        for (SourceGetMethod sourceGetMethod : sourceGetMethods) {
            result = result + "_" + sourceGetMethod.getName();
        }
        List<JobsQty> jobsQties;
        jobsQties = jobsQtyRepo.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate;
        String lastDate = LocalDateTime.MIN.toString();
        for (JobsQty jobsQty : jobsQties){
            currentDate = jobsQty.getRecordDateTime().format(formatter);
            if (currentDate.equals(lastDate)){
                result += "_" + jobsQty.getQty().toString();
            } else {
                result += "," + currentDate + "_" + jobsQty.getQty().toString();
            }
            lastDate = currentDate;
        }
        System.out.println(result);
        return result;
    }
}
