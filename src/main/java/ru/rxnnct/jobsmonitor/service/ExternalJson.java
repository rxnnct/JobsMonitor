package ru.rxnnct.jobsmonitor.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalJson {
    private int found;

    public int getFound() {
        return found;
    }
}
