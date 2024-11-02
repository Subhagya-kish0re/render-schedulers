package com.scheduler.render_schedulers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RenderSchedulersApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenderSchedulersApplication.class, args);
	}

}
