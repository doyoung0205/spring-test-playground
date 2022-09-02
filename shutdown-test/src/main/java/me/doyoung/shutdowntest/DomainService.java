package me.doyoung.shutdowntest;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DomainService {
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void doSomeThing() {
        // do domain something
        eventPublisher.publishEvent(new DomainExternalEvnet("source"));
    }
}
