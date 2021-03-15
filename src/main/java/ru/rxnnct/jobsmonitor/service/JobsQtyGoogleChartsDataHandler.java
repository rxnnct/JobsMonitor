package ru.rxnnct.jobsmonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.rxnnct.jobsmonitor.domain.JobsQty;
import ru.rxnnct.jobsmonitor.domain.SourceGetMethod;
import ru.rxnnct.jobsmonitor.repo.JobsQtyRepo;
import ru.rxnnct.jobsmonitor.repo.SourceGetMethodRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

/**
 * Converts data for charts.
 */

@Component
public class JobsQtyGoogleChartsDataHandler {
    private final JobsQtyRepo jobsQtyRepo;
    private final SourceGetMethodRepo sourceGetMethodRepo;
    @Value("${googleChartsDateFormat}")
    private String googleChartsDateFormat;

    @Autowired
    public JobsQtyGoogleChartsDataHandler(JobsQtyRepo jobsQtyRepo, SourceGetMethodRepo sourceGetMethodRepo) {
        this.jobsQtyRepo = jobsQtyRepo;
        this.sourceGetMethodRepo = sourceGetMethodRepo;
    }

    public String makeGoogleChartsData(){
        List<SourceGetMethod> sourceGetMethods;
        sourceGetMethods = sourceGetMethodRepo.findAll();
        List<JobsQty> jobsQties;
        jobsQties = jobsQtyRepo.findAll();
        return listsToResultString(sourceGetMethods, jobsQties);
    }

    public String listsToResultString(List<SourceGetMethod> sourceGetMethods, List<JobsQty> jobsQties){
        StringBuilder result = new StringBuilder("Date");
        for (SourceGetMethod sourceGetMethod : sourceGetMethods) {
            result.append("_").append(sourceGetMethod.getName());
        }
        Collections.sort(jobsQties);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(googleChartsDateFormat);
        String currentDate;
        String lastDate = LocalDateTime.MIN.toString();
        for (JobsQty jobsQty : jobsQties){
            currentDate = jobsQty.getRecordDateTime().format(formatter);
            if (currentDate.equals(lastDate)){
                result.append("_").append(jobsQty.getQty().toString());
            } else {
                result.append(",").append(currentDate).append("_").append(jobsQty.getQty().toString());
            }
            lastDate = currentDate;
        }

        return result.toString();
    }
}
