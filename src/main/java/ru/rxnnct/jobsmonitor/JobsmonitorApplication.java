package ru.rxnnct.jobsmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobsmonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobsmonitorApplication.class, args);
	}

}
