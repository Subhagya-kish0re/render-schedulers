package com.scheduler.render_schedulers.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class Scheduler {

    private final WebClient webClient;
    private final ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    public Scheduler(WebClient.Builder webClientBuilder, ThreadPoolTaskScheduler taskScheduler) {
        this.webClient = webClientBuilder.baseUrl("https://shawarmahouse-backend-6ax5.onrender.com/shawarmahouse/v1").build();
        this.taskScheduler = taskScheduler;
    }

    @Scheduled(cron = "0 * * * * *") // Run every 10 minutes
    public void fetchShawarmaData() {
        taskScheduler.execute(() -> {
            try {
                String response = webClient.get()
                        .uri("/test")
                        .retrieve()
                        .bodyToMono(String.class)
                        .onErrorMap(throwable -> new RuntimeException("WebClient Response error"))
                        .block();

                log.info("Data fetched successfully: {}", response);
            } catch (Exception e) {
                log.error("Error fetching webclient response data", e);
            }
        });
    }
}
