package ru.rxnnct.jobsmonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.rxnnct.jobsmonitor.domain.ProxyProperty;
import ru.rxnnct.jobsmonitor.domain.SourceGetMethod;
import ru.rxnnct.jobsmonitor.repo.ProxyPropertyRepo;
import ru.rxnnct.jobsmonitor.repo.SourceGetMethodRepo;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

@Component
@EnableScheduling
public class HeadhunterJobsQtyGrabber {

    private final int REQUEST_DELAY = 5000; //To prevent potential blocking by an external service
    private final SourceGetMethodRepo sourceGetMethodRepo;
    private final ProxyPropertyRepo proxyPropertyRepo;

    @Autowired
    public HeadhunterJobsQtyGrabber(SourceGetMethodRepo sourceGetMethodRepo, ProxyPropertyRepo proxyPropertyRepo) {
        this.sourceGetMethodRepo = sourceGetMethodRepo;
        this.proxyPropertyRepo = proxyPropertyRepo;
    }

    @Scheduled(cron = "${headhunterJobsQtyGrabberSchedulerCronExpression}")
//    @Transactional
    public void grab() {
        List<SourceGetMethod> sourceGetMethods;
        sourceGetMethods = sourceGetMethodRepo.findAll();
        List<ProxyProperty> proxyProperties;
        proxyProperties = proxyPropertyRepo.findAll();

        SimpleClientHttpRequestFactory clientHttpReq = new SimpleClientHttpRequestFactory();

        sourceGetMethods.forEach(sourceGetMethod -> {
            proxyProperties.forEach(proxyProperty -> {
                try {

                    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyProperty.getIp(), proxyProperty.getPort().intValue()));
                    clientHttpReq.setProxy(proxy);
                    String currentUrl;
                    currentUrl = sourceGetMethod.getUrl();
                    RestTemplate restTemplate = new RestTemplate(clientHttpReq);
                    ExternalJson externalJson = restTemplate.getForObject(currentUrl, ExternalJson.class);
                    System.out.println(currentUrl + " - " + System.currentTimeMillis() + " - *SAVE DATA* Good: " + proxyProperty.getIp());
                    System.out.println("Found: " + externalJson.getFound());
                } catch (ResourceAccessException e) {
                    System.out.println("ALARM! Bad: " + proxyProperty.getIp());
                }
            });
            try {
                Thread.sleep(REQUEST_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}