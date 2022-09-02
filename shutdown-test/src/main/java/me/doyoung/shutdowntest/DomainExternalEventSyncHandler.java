package me.doyoung.shutdowntest;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class DomainExternalEventSyncHandler {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleEvent(DomainExternalEvnet ignored) {
        System.out.println("SyncHandler 실행 ~");
        try {
            // async something
            Thread.sleep(1_000L * 10);
        } catch (InterruptedException exception) {
            System.out.println("SyncHandler Interrupted 발생  ~ ");
            Thread.currentThread().interrupt();
        }
        System.out.println("SyncHandler 완료 ~");
    }
}

