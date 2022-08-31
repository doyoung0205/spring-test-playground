package me.doyoung.shutdowntest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;

@RestController
@RequiredArgsConstructor
public class SleepController {

    private final SyncService syncService;

    @GetMapping("hello")
    public String hello(@RequestParam(value = "message", defaultValue = "hello") final String message) {
        return syncService.getMessageWithAsync(message);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Callback triggered - @PreDestroy.");
    }

}
