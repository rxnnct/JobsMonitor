package ru.rxnnct.jobsmonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.rxnnct.jobsmonitor.domain.JobsQty;
import ru.rxnnct.jobsmonitor.domain.ProxyProperty;
import ru.rxnnct.jobsmonitor.domain.SourceGetMethod;
import ru.rxnnct.jobsmonitor.repo.JobsQtyRepo;
import ru.rxnnct.jobsmonitor.repo.ProxyPropertyRepo;
import ru.rxnnct.jobsmonitor.repo.SourceGetMethodRepo;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

@Component
@EnableScheduling
public class HeadhunterJobsQtyGrabber extends BaseGrabber {
    private final SourceGetMethodRepo sourceGetMethodRepo;
    private final ProxyPropertyRepo proxyPropertyRepo;
    private final JobsQtyRepo jobsQtyRepo;

    @Autowired
    public HeadhunterJobsQtyGrabber(SourceGetMethodRepo sourceGetMethodRepo, ProxyPropertyRepo proxyPropertyRepo, JobsQtyRepo jobsQtyRepo) {
        this.sourceGetMethodRepo = sourceGetMethodRepo;
        this.proxyPropertyRepo = proxyPropertyRepo;
        this.jobsQtyRepo = jobsQtyRepo;
    }

    @Override
    @Transactional
    @Scheduled(cron = "${headhunterJobsQtyGrabberSchedulerCronExpression}")
    public void grab() {
        List<SourceGetMethod> sourceGetMethods;
        sourceGetMethods = sourceGetMethodRepo.findAll();
        Page<ProxyProperty> proxyProperties = proxyPropertyRepo.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "id")));
        ProxyProperty proxyProperty = proxyProperties.getContent().get(0); //works with main property (first)
        sourceGetMethods.forEach(sourceGetMethod -> {
            String currentUrl;
            currentUrl = sourceGetMethod.getUrl();
            try {
                SimpleClientHttpRequestFactory clientHttpReq = new SimpleClientHttpRequestFactory();
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyProperty.getIp(), proxyProperty.getPort().intValue()));
                clientHttpReq.setProxy(proxy);
                RestTemplate restTemplate = new RestTemplate(clientHttpReq);
                ExternalJson externalJson = restTemplate.getForObject(currentUrl, ExternalJson.class);
                if (externalJson != null) {
                    JobsQty jobsQty = new JobsQty(sourceGetMethod.getName(), externalJson.getFound());
                    jobsQtyRepo.save(jobsQty);
                } else {
                    saveErrorLog("BAD RESPONSE (" + currentUrl + ")");
                    //todo: save previous data (duplicatePreviousData())
                }
            } catch (ResourceAccessException e) {
                saveErrorLog("BAD PROXY (" + proxyProperty.getIp() + ")");
                //todo: save previous data (duplicatePreviousData())
            }
            try {
                Thread.sleep(proxyProperty.getDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

//    private void duplicatePreviousData(JobsQty jobsQty){
//                List<JobsQty> jobsQties;
//                jobsQties = jobsQtyRepo.findAll();
//                int i = jobsQties.size();
//                boolean done = false;
//                while (!done || i==0){
//                    if (---.getName().equals(jobsQties.get(i).getName())){
//                        JobsQty jobsQty = new JobsQty(---.getName(), jobsQties.get(i).getQty());
//                        jobsQtyRepo.save(jobsQty);
//                        done = true;
//                    }
//                    i--;
//                }
//    }
}