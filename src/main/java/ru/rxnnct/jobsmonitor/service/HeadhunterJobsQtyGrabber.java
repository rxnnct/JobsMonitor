package ru.rxnnct.jobsmonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
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
public class HeadhunterJobsQtyGrabber {

    private final SourceGetMethodRepo sourceGetMethodRepo;
    private final ProxyPropertyRepo proxyPropertyRepo;
    private final JobsQtyRepo jobsQtyRepo;

    @Autowired
    public HeadhunterJobsQtyGrabber(SourceGetMethodRepo sourceGetMethodRepo, ProxyPropertyRepo proxyPropertyRepo, JobsQtyRepo jobsQtyRepo) {
        this.sourceGetMethodRepo = sourceGetMethodRepo;
        this.proxyPropertyRepo = proxyPropertyRepo;
        this.jobsQtyRepo = jobsQtyRepo;
    }

//    @Scheduled(cron = "${headhunterJobsQtyGrabberSchedulerCronExpression}")
    @Transactional
    public void grab() {
        List<SourceGetMethod> sourceGetMethods;
        sourceGetMethods = sourceGetMethodRepo.findAll();
        List<ProxyProperty> proxyProperties;
        //todo: find first
        proxyProperties = proxyPropertyRepo.findAll();
        ProxyProperty proxyProperty = proxyProperties.get(0); //works with main property (first)
        sourceGetMethods.forEach(sourceGetMethod -> {
            String currentUrl;
            currentUrl = sourceGetMethod.getUrl();
            System.out.println(currentUrl); //todo: remove
            try {
                SimpleClientHttpRequestFactory clientHttpReq = new SimpleClientHttpRequestFactory();
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyProperty.getIp(), proxyProperty.getPort().intValue()));
                clientHttpReq.setProxy(proxy);
                RestTemplate restTemplate = new RestTemplate(clientHttpReq);
                ExternalJson externalJson = restTemplate.getForObject(currentUrl, ExternalJson.class);
                if (externalJson != null) {
                    System.out.println("Found: " + externalJson.getFound() + " " + proxyProperty.getIp());
                    JobsQty jobsQty = new JobsQty(sourceGetMethod.getName(), externalJson.getFound());
                    jobsQtyRepo.save(jobsQty);
                } else {
                    System.out.println("ALARM! Bad response: " + currentUrl); //todo: e-mail
                }
            } catch (ResourceAccessException e) {
                System.out.println("ALARM! Bad proxy: " + proxyProperty.getIp()); //todo: e-mail
            }
            try {
                Thread.sleep(proxyProperty.getDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}