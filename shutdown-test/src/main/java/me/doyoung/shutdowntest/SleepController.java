package me.doyoung.shutdowntest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class SleepController {

    @GetMapping("hello")
    public String hello(@RequestParam(value = "message", defaultValue = "hello") final String message) {
        final CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("supplyAsync 실행 ~");
                Thread.sleep(1_000L * 60);
            } catch (InterruptedException exception) {
                System.out.println("InterruptedException 발생  ~ ");
                Thread.currentThread().interrupt();
            }
            return message;
        });
        return completableFuture.join();
    }

}
