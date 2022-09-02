package me.doyoung.shutdowntest;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.concurrent.CompletableFuture;

import static me.doyoung.shutdowntest.AsyncConfig.MY_EXECUTOR;

@Component
public class DomainExternalEventAsyncHandler {

    @Async(MY_EXECUTOR)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleEvent(DomainExternalEvnet ignored) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("AsyncHandler 실행 ~");
            try {
                // async something
                Thread.sleep(1_000L * 10);
            } catch (InterruptedException exception) {
                System.out.println("AsyncHandler Interrupted 발생  ~ ");
                Thread.currentThread().interrupt();
            }
            System.out.println("AsyncHandler 완료 ~");
            return "return";
        });
    }
}

