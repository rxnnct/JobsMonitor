package ru.rxnnct.jobsmonitor.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalJson {
    private long found;

    public long getFound() {
        return found;
    }
}
