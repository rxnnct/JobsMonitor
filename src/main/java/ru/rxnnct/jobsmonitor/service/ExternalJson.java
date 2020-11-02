package ru.rxnnct.jobsmonitor.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class ExternalJson {
    private long found;

    long getFound() {
        return found;
    }
}