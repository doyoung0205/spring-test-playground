package me.doyoung.shutdowntest;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static me.doyoung.shutdowntest.MyThreadPoolExecutor.MY_EXECUTOR;

@Service
public class SyncService {

    @Async(MY_EXECUTOR)
    public String getMessageWithAsync(String message) {
        System.out.println("supplyAsync 실행 ~");
        try {
            Thread.sleep(1_000L * 60);
        } catch (InterruptedException exception) {
            System.out.println("InterruptedException 발생  ~ ");
            Thread.currentThread().interrupt();
        }

        System.out.println("message = " + message);
        return message;
    }
}

