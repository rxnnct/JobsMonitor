package ru.rxnnct.jobsmonitor.service;

import org.springframework.stereotype.Component;
import ru.rxnnct.jobsmonitor.domain.JobsQty;

import java.util.List;

@Component
public class JobsQtyConverter {
    public String convertToGoogleChartsArray(List<JobsQty> list){
        //code

        //test
        return "Data_Java_Python,2001_1000_400,2002_1300_500";
    }
}
