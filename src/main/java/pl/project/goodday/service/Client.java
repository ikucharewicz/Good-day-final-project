package pl.project.goodday.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Client {

    @Scheduled(cron = "")
    public void scheduleGoldenThoughts(){

    }
}
